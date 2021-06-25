package com.shonpee.shonpee.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.ProductCategoryBean;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.ProductCategoryRepository;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.PropertyRepository;
import com.shonpee.shonpee.repository.PropertySecondRepository;
import com.shonpee.shonpee.repositoryservice.ProductServiceRepository;
import com.shonpee.shonpee.repositoryservice.PropertySecondServiceRepository;
import com.shonpee.shonpee.repositoryservice.PropertyServiceRepository;

@Controller
public class productController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertySecondRepository  propertySecondRepository;
 	@Autowired
	private ProductServiceRepository productService;
	@Autowired
	private PropertyServiceRepository propertyServiceRepository;
	@Autowired
	private PropertySecondServiceRepository propertySecondServiceRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Value("${upload-path}")
	private String uploadpath;

	@RequestMapping("/MyCategoriesPage1")
	public String MyCategoriesPage1() {

		System.out.println("APPLE");
		return "MyCategoriesPage1";
	}

//	@RequestMapping("/NewProduct")
//	public String NewProductpage() {
//
//		System.out.println("BEE");
//		return "NewProduct";
//	}

	@RequestMapping("/NewProduct.page")
	@ResponseBody
	public String Newproduct(String first, String second, String third, String name, HttpSession session)
			throws IOException {
		session.setAttribute("first", first);
		session.setAttribute("second", second);
		session.setAttribute("third", third);
		session.setAttribute("name", name);
		return "success";
	}

	@PostMapping("/addProduct")
	public String newproduct(@RequestParam(value = "productphoto", required = false) List<MultipartFile> productphoto,
			String productID, String productName, Integer productPrice, Integer productStock, String productDetail,
			String sellerProductCategory, String sellerID, String productFirstCategoryId, String propertyName,
			String propertyValue, String propertySecondName, String propertySecondValue, String productSecondCategoryId,
			String productThirdCategoryId, HttpSession session, ProductBean bean, PropertyBean propertyBean,
			PropertyBeanSecond propertyBeanSecond, BindingResult result, Model model)
			throws IllegalStateException, IOException {
		// 放圖片的資料夾
		File path = new File(uploadpath);
		if (!path.exists()) {
			path.mkdir();
		}
		// 圖片
		List<String> filepathlist = new ArrayList<String>();

		for (int i = 0; i < productphoto.size(); i++) {
			if (productphoto.get(i).getName().equals("productphoto")) {
				if (productphoto.get(i).getOriginalFilename().length() == 0)
					continue;
				String fileSuffix = productphoto.get(i).getOriginalFilename()
						.substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
				String filepath = uploadpath + "/"
						+ (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())) + fileSuffix;
				productphoto.get(i).transferTo(new File(filepath));
				filepathlist.add(filepath);
			}
		}
		String filepathstr = "";
		for (String fruit : filepathlist) {
			filepathstr += fruit + ",";
		}

		bean.setProductPhoto(filepathstr);

		// 類別
		List<ProductCategoryBean> ProductCategoryBeans = productCategoryRepository.findAll();
		for (ProductCategoryBean productCategoryBean : ProductCategoryBeans) {
			if (productCategoryBean.getCategoryName().equals(session.getAttribute("first"))) {
				bean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("second"))) {
				bean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("third"))) {
				bean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
			}

		}

		bean.setMemberId("anna38");
		ProductBean newbean = productService.insert(bean);
		Integer newid = newbean.getProductid();

		propertyBean.setProductid1(newid);
		propertyBeanSecond.setProductid2(newid);

		productService.insert(propertyBean);
		productService.insert(propertyBeanSecond);


		return "redirect:/MyProduct";

	}
	
	@RequestMapping("/NewProduct")
    public String NewProductpage(HttpSession session,Model model) {
        
        if(true) {
            
            List<ProductBean> memberPD = productRepository.findProductBeanbyMember("anna38");
            System.out.println(memberPD);

            for(ProductBean product :memberPD) {
                System.out.println("=============="+ product.getProductid()+"=============");

                if(product.getProductid()==5) {        

                    model.addAttribute("name", product.getProductName());
                    model.addAttribute("ProductDetail", product.getProductDetail());
                    model.addAttribute("ProductPrice", product.getProductPrice());
                    model.addAttribute("ProductStock", product.getProductStock());
               
                    //照片
                    String[] photos = product.getProductPhoto().split(",");
                    model.addAttribute(photos);
                    System.out.println(photos[2]);
                    
                    //類別
                    String firstCategoryName = productCategoryRepository.findCategoryNameByCategoryID(product.getProductFirstCategoryId());
                    String secondCategoryName = productCategoryRepository.findCategoryNameByCategoryID(product.getProductSecondCategoryId());
                    String thirdCategoryName = productCategoryRepository.findCategoryNameByCategoryID(product.getProductThirdCategoryId());
                    model.addAttribute("firstCategoryName",firstCategoryName);
                    model.addAttribute("secondCategoryName",secondCategoryName);
                    model.addAttribute("thirdCategoryName",thirdCategoryName);
                        
                    //規格
                    PropertyBean propertyfirst= propertyRepository.findPropertyBeanByProdcutID(5);
                    System.out.println(propertyfirst);
                    if(propertyfirst != null) {
                        model.addAttribute("firstPropertyName", propertyfirst.getPropertyName());
                        String[] firstPropertyValue = propertyfirst.getPropertyValue().split(",");
                        model.addAttribute("firstPropertyValue", firstPropertyValue);
                        
                    }else {
                        System.out.println("propertyfirst is null");
                    }
                    PropertyBeanSecond propertysecond= propertySecondRepository.findPropertyBeanByProdcutID(5);
                    if(propertysecond != null) {
                        model.addAttribute("secondPropertyName", propertysecond.getPropertyName());
                        String[] secondPropertyValue = propertysecond.getPropertyValue().split(",");                    
                        model.addAttribute("secondPropertyValue", secondPropertyValue);
                    }else {
                        System.out.println("propertysecond is null");
                    }                    
                }
                
            }
        }
                
        return "NewProduct";
    }
}
