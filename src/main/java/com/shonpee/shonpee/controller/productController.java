package com.shonpee.shonpee.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	private PropertySecondRepository propertySecondRepository;
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
	public String MyCategoriesPage1(Model model,Integer productid,Integer changecategorys) {
		if(productid!=null) {
			Optional<ProductBean> bean = productRepository.findById(productid);
			if(bean.isPresent()) {
				ProductBean productBean	= bean.get();
				model.addAttribute("productname",productBean.getProductName());
				model.addAttribute("productid",productid);
				model.addAttribute("changecategorys",changecategorys);
			}			
		}
				
		System.out.println("APPLE");
		return "MyCategoriesPage1";
	}

	@RequestMapping("/UpdateProduct")
	public String NewProductpage(Model model, String productid,String productname, String category,Integer changecategorys) {
		System.out.println("flower");
		System.out.println(productid);
		System.out.println("==========");
		System.out.println("Interestring");
		System.out.println(productname);
		System.out.println("==========");
		System.out.println(category);

		// 新增時 顯示分類和名字
		if (productid == null||productid=="") {
			System.out.println("Hippo===");
			String[] categorys = category.split(",");
			model.addAttribute("firstCategory", categorys[0]);
			model.addAttribute("secondCategory", categorys[1]);
			if (categorys.length > 2) {
				model.addAttribute("thirdCategory", categorys[2]);
			}
			model.addAttribute("newname", productname);
		}

		// 修改
		Integer Pid = null;
		if (productid != null && productid != "") {
			Pid = Integer.parseInt(productid);
		}
		model.addAttribute("productid", Pid);
		System.out.println("productid===" + Pid);
		if (Pid != null) {
			System.out.println("Jam===");
			List<ProductBean> memberPD = productRepository.findProductBeanbyMember("anna38");
			for (ProductBean product : memberPD) {
				if (product.getProductid() == Pid) {
					System.out.println("KOBE===");
					if(changecategorys == null ) {
						model.addAttribute("name", product.getProductName());						
					}
					
					model.addAttribute("ProductDetail", product.getProductDetail());
					model.addAttribute("ProductPrice", product.getProductPrice());
					model.addAttribute("ProductStock", product.getProductStock());
					System.out.println("MOMMY++===");
					System.out.println(product.getProductName());
					System.out.println("==========");

					// 照片
					String[] photos = product.getProductPhoto().split(",");
					model.addAttribute("photos", photos);
					System.out.println(photos[0]);

					// 類別
					if(changecategorys != null) {
						String[] categorys = category.split(",");
						model.addAttribute("firstCategory", categorys[0]);
						model.addAttribute("secondCategory", categorys[1]);
						if (categorys.length > 2) {
							model.addAttribute("thirdCategory", categorys[2]);
						}
						model.addAttribute("newname", productname);
					}else {
						String firstCategoryName = productCategoryRepository
								.findCategoryNameByCategoryID(product.getProductFirstCategoryId());
						String secondCategoryName = productCategoryRepository
								.findCategoryNameByCategoryID(product.getProductSecondCategoryId());
						String thirdCategoryName = productCategoryRepository
								.findCategoryNameByCategoryID(product.getProductThirdCategoryId());
						model.addAttribute("firstCategoryName", firstCategoryName);
						model.addAttribute("secondCategoryName", secondCategoryName);
						model.addAttribute("thirdCategoryName", thirdCategoryName);						
					}
					
					// 規格
					PropertyBean propertyfirst = propertyRepository.findPropertyBeanByProdcutID(Pid);
					if (propertyfirst != null) {
						model.addAttribute("firstPropertyName", propertyfirst.getPropertyName());
						String[] firstPropertyValue = propertyfirst.getPropertyValue().split(",");
						model.addAttribute("firstPropertyValue", firstPropertyValue);

					} else {
						System.out.println("propertyfirst is null");
					}
					PropertyBeanSecond propertysecond = propertySecondRepository.findPropertyBeanByProdcutID(Pid);
					if (propertysecond != null) {
						model.addAttribute("secondPropertyName", propertysecond.getPropertyName());
						String[] secondPropertyValue = propertysecond.getPropertyValue().split(",");
						model.addAttribute("secondPropertyValue", secondPropertyValue);
					} else {
						System.out.println("propertysecond is null");
					}
				}

			}
		}

		return "NewProduct";
	}

	@PostMapping("/addProduct")
	public String newproduct(@RequestParam(value = "productphoto", required = false) List<MultipartFile> productphoto,
			Integer productid, String productname, Integer productPrice, Integer productStock, String productDetail,
			String oldPhoto, String sellerProductCategory, String sellerID, String productFirstCategoryId,
			String propertyName, String propertyValue, String propertySecondName, String propertySecondValue,
			String allcategorys, String productSecondCategoryId, String productThirdCategoryId, HttpSession session,
			ProductBean bean, PropertyBean propertyBean, PropertyBeanSecond propertyBeanSecond, BindingResult result,
			Model model) throws IllegalStateException, IOException {

		System.out.println("dog");
		System.out.println("categorys===" + allcategorys);
		System.out.println("=========96");
		System.out.println(productname);
		System.out.println("=========98");
		System.out.println(productid);
		// 新增
		// 放圖片的資料夾
		File path = new File(uploadpath);
		if (!path.exists()) {
			path.mkdir();
		}
		// 圖片
		if (productid == null) {
			System.out.println("My id is null hahahahahha");
			List<String> filepathlist = new ArrayList<String>();

			for (int i = 0; i < productphoto.size(); i++) {
				if (productphoto.get(i).getName().equals("productphoto")) {
					if (productphoto.get(i).getOriginalFilename().length() == 0)
						continue;

					String fileSuffix = productphoto.get(i).getOriginalFilename()
							.substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
					String srcpath = (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())) + i
							+ fileSuffix;
					// 圖片存放位置
					String filepath = uploadpath + "/" + srcpath;
					productphoto.get(i).transferTo(new File(filepath));

					// 資料庫存放圖片相對地址
					String filepathsql = "/pic/upload/" + srcpath;
					filepathlist.add(filepathsql);
				}
			}
			String filepathstr = "";
			for (String fruit : filepathlist) {
				if (filepathlist.size() == 1) {
					filepathstr = fruit;
				} else {
					filepathstr += fruit + ",";
				}
			}

			bean.setProductPhoto(filepathstr);

			// 類別
			List<ProductCategoryBean> ProductCategoryBeans = productCategoryRepository.findAll();
			String[] category = allcategorys.split(",");
			for (ProductCategoryBean productCategoryBean : ProductCategoryBeans) {
				if (productCategoryBean.getCategoryName().equals(category[0])) {
					bean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
				} else if (productCategoryBean.getCategoryName().equals(category[1])) {
					bean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
				} else if (category.length > 2) {
					if (productCategoryBean.getCategoryName().equals(category[2])) {
						bean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
					}
				}
			}

			bean.setMemberId("anna38");
			model.addAttribute("name", productname);
			model.addAttribute("productid", productid);
			System.out.println("=========151");
			System.out.println(productname);
			System.out.println("=========156");
			System.out.println(productid);
			bean.setProductName(productname);
			ProductBean newbean = productService.insert(bean);
			Integer newid = newbean.getProductid();
			propertyBean.setProductid1(newid);
			propertyBean.setPropertyName(propertyName);
			propertyBean.setPropertyValue(propertyValue);
			propertyBeanSecond.setProductid2(newid);
			propertyBeanSecond.setPropertyName(propertySecondName);
			propertyBeanSecond.setPropertyValue(propertySecondValue);

			productService.insertFirstProperty(propertyBean);
			productService.insertSecondProperty(propertyBeanSecond);
		} else {
			System.out.println("My ID is not null so i am here");
			// 修改
			Optional<ProductBean> ProductBeanOP = productRepository.findById(productid);
			if (ProductBeanOP.isPresent()) {
				ProductBean productBean = ProductBeanOP.get();
				// 有圖片新增
				List<String> filepathlist = new ArrayList<String>();

				for (int i = 0; i < productphoto.size(); i++) {
					if (productphoto.get(i).getName().equals("productphoto")) {
						if (productphoto.get(i).getOriginalFilename().length() == 0)
							continue;

						String fileSuffix = productphoto.get(i).getOriginalFilename()
								.substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
						String srcpath = (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())) + i
								+ fileSuffix;
						// 圖片存放位置
						String filepath = uploadpath + "/" + srcpath;
						productphoto.get(i).transferTo(new File(filepath));

						// 資料庫存放圖片相對地址
						String filepathsql = "/pic/upload/" + srcpath;
						filepathlist.add(filepathsql);
					}
				}
				String filepathstr = "";
				for (String fruit : filepathlist) {
					if (filepathlist.size() == 1) {
						filepathstr = fruit;
					} else {
						filepathstr += fruit + ",";
					}
				}
				// 刪除舊圖檔
				System.out.println("productphoto delete old photo");
				String[] oldPhotoArr = oldPhoto.split(",");
				System.out.println("oldPhoto==="+oldPhoto);
				String[] databasePhoto = productBean.getProductPhoto().split(",");
				String[] newPhoto = filepathstr.split(",");
				List<String> deleteList = new ArrayList<>(Arrays.asList(databasePhoto));
				for (int i = 0; i < databasePhoto.length; i++) {
					for (int j = 0; j < oldPhotoArr.length; j++) {
						if (databasePhoto[i].equals(oldPhotoArr[j])) {
							System.out.println("重複的databasePhoto i=" + i + "====" + databasePhoto[i]
									+ "重複的OldPhotoArr j=" + j + "====" + oldPhotoArr[j]);
							deleteList.remove(databasePhoto[i]);
						} else {

						}
					}
				}
				String frontPath = "C:/eclipseEE/shonpee/src/main/resources/static/";
				for (String delephoto : deleteList) {
					String deletephotoPath = frontPath + delephoto;
					File delefile = new File(deletephotoPath);
					System.out.println("delete,photoPATH=" + deletephotoPath);
					if (delefile.exists()) {
						boolean isdelete = delefile.delete();
						System.out.println("true?===" + isdelete);
					}
				}
				// 排序新舊圖片順序
				for(String arr:oldPhotoArr) {
					System.out.println("306 arr====="+arr);
				}
				for (int i = 0; i < newPhoto.length; i++) {
					for (int j = 0; j < oldPhotoArr.length; j++) {
						if (oldPhotoArr[j].equals("newpic")) {
							oldPhotoArr[j] = newPhoto[i];
							System.out.println("newPhoto[i]=" + newPhoto[i]);
							break;
						}
					}
				}
				String newPhotoPath = "";
				for (String oldpic : oldPhotoArr) {
					if (oldPhotoArr.length != 1) {
						newPhotoPath = newPhotoPath + oldpic + ",";
					} else {
						newPhotoPath = oldpic;
					}
				}
				System.out.println("326 newPhotoPath======" + newPhotoPath);
				
				productBean.setProductPhoto(newPhotoPath);
				productBean.setProductName(productname);
				productBean.setProductPrice(productPrice);
				productBean.setProductStock(productStock);
				productBean.setProductDetail(productDetail);
				System.out.println("allcategorys==="+allcategorys);
				// 類別
				List<ProductCategoryBean> ProductCategoryBeans = productCategoryRepository.findAll();
				String[] category = allcategorys.split(",");

				for (ProductCategoryBean productCategoryBean : ProductCategoryBeans) {
					if (productCategoryBean.getCategoryName().equals(category[0])) {
						productBean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
					} else if (productCategoryBean.getCategoryName().equals(category[1])) {
						productBean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
						productBean.setProductThirdCategoryId(null);
					} else if (category.length > 2) {						
						if (productCategoryBean.getCategoryName().equals(category[2])) {
							productBean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
						}
					}
				}

				PropertyBean updatePropertyBean = propertyRepository.findPropertyBeanByProdcutID(productid);
				System.out.println("349 updateproperty====="+updatePropertyBean);
				if (updatePropertyBean!=null) {
					System.out.println("350 updateproperty====="+updatePropertyBean);
					updatePropertyBean.setPropertyName(propertyName);
					updatePropertyBean.setPropertyValue(propertyValue);
					propertyServiceRepository.update(updatePropertyBean);
				}

				PropertyBeanSecond updatePropertyBeanSecond = propertySecondRepository.findPropertyBeanByProdcutID(productid);
				if (updatePropertyBeanSecond!=null) {
					System.out.println("350 updatepropertysecond====="+updatePropertyBeanSecond);
					updatePropertyBeanSecond.setPropertyName(propertySecondName);
					updatePropertyBeanSecond.setPropertyValue(propertySecondValue);
					propertySecondServiceRepository.update(updatePropertyBeanSecond);
				}
				productService.update(productBean);
				System.out.println("productBean==" + productBean.getMemberId());

			}

		}

		return "redirect:MyProduct";
	}

	@GetMapping("/MyProduct")
	public String MyProduct(Model model) {
		System.out.println("egg");
		// 搜索所有的資料
		// 將會員資料新增的商品巡訪找出來
		List<String> allphotos = new ArrayList<String>();
		List<PropertyBean> PropertyFirstList = new ArrayList<PropertyBean>();
		List<PropertyBeanSecond> PropertySecondList = new ArrayList<PropertyBeanSecond>();
		List<ProductBean> MemberProduct = productRepository.findProductBeanbyMember("anna38");
		for (ProductBean Product : MemberProduct) {
			String[] photos = Product.getProductPhoto().split(",");
			allphotos.add(photos[0]);
			Integer PID = Product.getProductid();
			System.out.println("PID=" + PID);

			PropertyBean PropertyProduct = propertyRepository.findPropertyBeanByProdcutID(PID);
			PropertyFirstList.add(PropertyProduct);
			PropertyBeanSecond propertyProductSecond = propertySecondRepository.findPropertyBeanByProdcutID(PID);
			PropertySecondList.add(propertyProductSecond);
		}

		model.addAttribute("allphotos", allphotos);
		model.addAttribute("MemberProduct", MemberProduct);
		model.addAttribute("PropertyFirstList", PropertyFirstList);
		model.addAttribute("PropertySecondList", PropertySecondList);

		return "MyProduct";
	}

	@RequestMapping("/DeleteProduct")
	@ResponseBody
	public String deleteProduct(@RequestParam("productid")Integer prodcutid) {
		System.out.println("407===="+prodcutid);
		Optional<ProductBean> deleteProductBean=productRepository.findById(prodcutid);
		if(deleteProductBean.isPresent()) {
			 PropertyBean deletePropertyBean= propertyRepository.findPropertyBeanByProdcutID(prodcutid);
		    PropertyBeanSecond deletePropertySecondBean=propertySecondRepository.findPropertyBeanByProdcutID(prodcutid);
       		System.out.println("deletePropertyBean="+deletePropertyBean);

		            boolean first = propertyServiceRepository.delete(deletePropertyBean);
		       		System.out.println("first="+first);

		            boolean second = propertySecondServiceRepository.delete(deletePropertySecondBean);
		            System.out.println("second="+second);
		           if(first&&second) {
		       		System.out.println("delete product");

		        	   ProductBean pbean= deleteProductBean.get();
			           productService.delete(pbean);  
		           }
		          
		}
       
		return "success";
	}
}
