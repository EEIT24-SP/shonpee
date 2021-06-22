package com.shonpee.shonpee.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ProductServiceRepository;
import com.shonpee.shonpee.repository.ProductcategoryRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductServiceRepository productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductcategoryRepository productcategoryRepository;

	@Value("${upload-path}")
	private String uploadpath;

	@GetMapping("/")
	public String mainPage(Model model) {
		// 找出第一層的全部類別，放入頁面
		model.addAttribute("categories", listFirstCategories());
		return "Main";
	}

	@PostMapping("/addProduct")
	public String newproduct(@RequestParam(value = "productphoto", required = false) List<MultipartFile> productphoto,
			String productID, String productName, Integer productPrice, Integer productStock, String productDetail,
			String sellerProductCategory, String sellerID, String productFirstCategoryId, String propertyName,
			String propertyValue, String propertySecondName, String propertySecondValue, String productSecondCategoryId,
			String productThirdCategoryId, ProductBean bean, PropertyBean propertyBean,
			PropertyBeanSecond propertyBeanSecond, BindingResult result, Model model)
			throws IllegalStateException, IOException {
		File path = new File(uploadpath);
		if (!path.exists()) {
			path.mkdir();
		}

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

		bean.setMemberId("anna38");
		bean.setProductFirstCategoryId(1);
		bean.setProductSecondCategoryId(2);
		bean.setProductThirdCategoryId(3);
		ProductBean newbean = productService.insert(bean);
		Integer newid = newbean.getProductid();

		propertyBean.setProductid1(newid);
		propertyBeanSecond.setProductid2(newid);

		productService.insert(propertyBean);
		productService.insert(propertyBeanSecond);

		return "login";
	}

	@GetMapping("/cat/{categoryId}")
	public String showOneCategoryProducts(@PathVariable("categoryId") Integer categoryId, 
											Model model) {
		// 找出第一層的全部類別，放入頁面
		List aaaList = listFirstCategories();
		model.addAttribute("categories", listFirstCategories());
		// 找出
		List<ProductBean> productsOfTheCategory = productRepository.findByProductFirstCategoryId(categoryId);
		model.addAttribute("products", productsOfTheCategory);
		System.out.println("--------");
		System.out.println(model);
		System.out.println("--------");
		return "Main";
	}

	
	// 以下是抽離的重複程式，沒有設定RequestMapping路徑
	public List<Productcategory> listFirstCategories() {
		List<Productcategory> firstProductcategories = productcategoryRepository.findByParentId(0);
		return firstProductcategories;
	}

}
