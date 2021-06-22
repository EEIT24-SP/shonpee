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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ProductServiceRepository;


@Controller
public class productController {
	
	@Autowired
	private ProductServiceRepository productService;
	
	@Autowired 
	private ProductRepository productRepository;
	
	@Value("${upload-path}")
	private String uploadpath;
	
	
	@RequestMapping("/")
    public String MyCategoriesPage1( ) {
		
		 System.out.println("apple");
        return "NewProduct";
    }
	

	
	 @PostMapping("/addProduct")
	    public String newproduct( @RequestParam(value="productphoto",required=false) List<MultipartFile> productphoto,String productID,String productName,Integer productPrice,Integer productStock,String productDetail,
	    						String sellerProductCategory,String sellerID,String productFirstCategoryId, String propertyName,String propertyValue,String propertySecondName,String propertySecondValue,
	    						String productSecondCategoryId,String productThirdCategoryId,ProductBean bean, PropertyBean propertyBean,PropertyBeanSecond propertyBeanSecond,BindingResult result,Model model)
	    								throws IllegalStateException, IOException {
		 File path = new File(uploadpath);
		 if(!path.exists()) {
			 path.mkdir();
		 }
		 		 
			 List<String> filepathlist = new ArrayList<String>();
			 
			 for(int i=0;i<productphoto.size();i++) {
				 if(productphoto.get(i).getName().equals("productphoto") ) {
				 if( productphoto.get(i).getOriginalFilename().length() == 0) continue;
				 String fileSuffix  = productphoto.get(i).getOriginalFilename().substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
				 String filepath = uploadpath +"/"+(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime()))+ fileSuffix;
				 productphoto.get(i).transferTo(new File(filepath));
				 filepathlist.add(filepath);
				 }
			 }
			 	String filepathstr = "";
			 	for (String fruit : filepathlist) {
					filepathstr += fruit+",";
				}				
			 
			 	bean.setProductPhoto(filepathstr);
			 	
		 
		 bean.setMemberId(1);
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
	
	 
	 
	 
}
