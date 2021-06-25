package com.shonpee.shonpee.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shonpee.shonpee.ServiceRepository.ProductServiceRepository;
import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ProductcategoryRepository;
import com.shonpee.shonpee.repository.PropertyRepository;
import com.shonpee.shonpee.repository.PropertySecondRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductServiceRepository productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private PropertySecondRepository propertySecondRepository;
	
	@Autowired
	private ProductcategoryRepository productcategoryRepository;

	@Value("${upload-path}")
	private String uploadpath;

	@GetMapping("/")
	public String mainPage(Model model) {
		// 找出第一層的全部類別，放入頁面
		model.addAttribute("categories", listFirstCategories());
		return "main";
	}

	@GetMapping("/product/{productid}")
    public String bearitem(@PathVariable("productid") Integer productid, Model model ) {
		
	 Optional<ProductBean> productBeans = productRepository.findById(productid);
	
	 
	 if(productBeans.isPresent()) {
		 //商品
		 ProductBean productBean = productBeans.get();
		 model.addAttribute(productBean);
		 
		 //圖片
		 String[] photos= productBean.getProductPhoto().split(",");
		 model.addAttribute("photos",photos);
		 System.out.println(photos[0]);
		 //規格
		 PropertyBean propertybeanfirst = propertyRepository.findPropertyBeanByName(productid);
		 String[] valuefirst = propertybeanfirst.getPropertyValue().split(",");
		 String propertyNameFirst = propertybeanfirst.getPropertyName();
		 model.addAttribute("propertyNameFirst",propertyNameFirst);
		 model.addAttribute("valuefirst",valuefirst);
		 PropertyBeanSecond propertyBeanSecond = propertySecondRepository.findPropertyBeanByName(productid);
		 String[] valuesecond = propertyBeanSecond.getPropertyValue().split(",");
		 String propertyNameSecond = propertyBeanSecond.getPropertyName();
		 model.addAttribute("propertyNameSecond",propertyNameSecond);
		 model.addAttribute("valuesecond",valuesecond);
	 }else {
		 System.out.println("Product not found.");
	 }
	
	 
        return "bear_item";
    }
	
	
	@RequestMapping("/MyCategoriesPage1")
    public String MyCategoriesPage1( ) {
		
		 System.out.println("APPLE");
        return "MyCategoriesPage1";
    }
	
	@RequestMapping("/NewProduct")
    public String NewProductpage( ) {
		//MyCategoriesPage1進入NewProduct
		 System.out.println("BEE");
        return "NewProduct";
    }
	
	
	@RequestMapping("/NewProduct.page")
	@ResponseBody
    public String Newproduct(String first,String second,String third,String name,HttpSession session ) throws IOException {
		//MyCategoriesPage1帶入的分類
		 session.setAttribute("first",first);
		 session.setAttribute("second",second);
		 session.setAttribute("third",third);
		 session.setAttribute("name",name);
        return "success";
    }
	
	
	
	 @PostMapping("/addProduct")
	    public String newproduct( @RequestParam(value="productphoto",required=false) List<MultipartFile> productphoto,String productID,String productName,Integer productPrice,Integer productStock,String productDetail,
	    						String sellerProductCategory,String sellerID,String productFirstCategoryId, String propertyName,String propertyValue,String propertySecondName,String propertySecondValue,
	    						String productSecondCategoryId,String productThirdCategoryId,HttpSession session,ProductBean bean, PropertyBean propertyBean,PropertyBeanSecond propertyBeanSecond,BindingResult result,Model model)
	    								throws IllegalStateException, IOException {
		 //放圖片的資料夾
		 File path = new File(uploadpath);
		 if(!path.exists()) {
			 path.mkdir();
		 }
		 //圖片
			 List<String> filepathlist = new ArrayList<String>();
			 
			 for(int i=0;i<productphoto.size();i++) {
				 if(productphoto.get(i).getName().equals("productphoto") ) {					 
				 if( productphoto.get(i).getOriginalFilename().length() == 0) continue;
				 
				 String fileSuffix  = productphoto.get(i).getOriginalFilename().substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
				 String srcpath  = (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime()))+ fileSuffix;
				 //圖片存放位置
				 String filepath = uploadpath +"/"+ srcpath;
				 productphoto.get(i).transferTo(new File(filepath));
				 
				 //資料庫存放圖片相對地址
				 String filepathsql = "/pic/upload/"+srcpath;
				 filepathlist.add(filepathsql);
				 }
			 }
			 	String filepathstr = "";
			 	for (String fruit : filepathlist) {
					filepathstr += fruit+",";
				}				
			 	bean.setProductPhoto(filepathstr);
			 	
			 	
			//類別 	
		 List<Productcategory>  ProductCategoryBeans= productcategoryRepository.findAll();
		 	 for(Productcategory productCategoryBean  : ProductCategoryBeans) {
		 		 if(productCategoryBean.getCategoryName().equals(session.getAttribute("first"))) {
		 			bean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
		 		 }else if(productCategoryBean.getCategoryName().equals(session.getAttribute("second"))){
		 			bean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
		 		 }else if(productCategoryBean.getCategoryName().equals(session.getAttribute("third"))) {
		 			bean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
		 		 }
		 		 
		 	 }
	//預設使用者	 	 
		 	MemberBean mb= new MemberBean();
		 	mb.setUserAccount("bsenger123");
		 bean.setMemberBean(mb);
		 
		 ProductBean newbean = productService.insert(bean);
		 Integer newid = newbean.getProductid();
		 
		 propertyBean.setProductid1(newid);
		 propertyBean.setPropertyName(propertyName);
		 propertyBean.setPropertyValue(propertyValue);
		 propertyBeanSecond.setProductid2(newid);
		 propertyBeanSecond.setPropertyName(propertySecondName);
		 propertyBeanSecond.setPropertyValue(propertySecondValue);
		 productRepository.save(bean);
		 productService.insertFirstProperty(propertyBean);
		 productService.insertSecondProperty(propertyBeanSecond);
		 
	    return "redirect:/main-page";
	    }
	
	@GetMapping("/cat/{categoryId}")
	public String showOneCategoryProducts(@PathVariable("categoryId") Integer categoryId, 
											Model model) {
		// 找出第一層的全部類別，放入頁面
		List aaaList = listFirstCategories();
		model.addAttribute("categories", listFirstCategories());
		// 找出該分類的產品，放入頁面
		List<ProductBean> productsOfTheCategory = productRepository.findByProductFirstCategoryId(categoryId);
		model.addAttribute("products", productsOfTheCategory);
		return "main";
	}

	
	// 以下是抽離的重複程式，沒有設定RequestMapping路徑
	public List<Productcategory> listFirstCategories() {
		List<Productcategory> firstProductcategories = productcategoryRepository.findByParentId(0);
		return firstProductcategories;
	}
}
