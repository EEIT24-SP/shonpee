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
import javax.websocket.Session;
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
import com.shonpee.shonpee.domain.CartBean;
import com.shonpee.shonpee.ServiceRepository.PropertySecondServiceRepository;
import com.shonpee.shonpee.ServiceRepository.PropertyServiceRepository;
import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.domain.ProductBean;
import com.shonpee.shonpee.domain.Productcategory;
import com.shonpee.shonpee.domain.PropertyBean;
import com.shonpee.shonpee.domain.PropertyBeanSecond;
import com.shonpee.shonpee.repository.CartRepository;
import com.shonpee.shonpee.repository.MemberRepository;
import com.shonpee.shonpee.repository.ProductRepository;
import com.shonpee.shonpee.repository.ProductcategoryRepository;
import com.shonpee.shonpee.repository.PropertyRepository;
import com.shonpee.shonpee.repository.PropertySecondRepository;


@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertySecondRepository propertySecondRepository;
	@Autowired
	private CartRepository CR;
	@Autowired
	private ProductServiceRepository productService;
	@Autowired
	private PropertyServiceRepository propertyServiceRepository;
	@Autowired
	private PropertySecondServiceRepository propertySecondServiceRepository;
	@Autowired
	private ProductcategoryRepository productCategoryRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Value("${upload-path}")
	private String uploadpath;

	@GetMapping("/main-page")
	public String mainPage(Model model,HttpSession session) {
		model.addAttribute("categories", listFirstCategories());
		Object Name = session.getAttribute("UserName");
		List<CartBean> list = CR.findAll();
        ArrayList<CartBean> cartcnt = new ArrayList<CartBean>();
		//搜尋會員,顯示符合當前帳號的購物車資料
		for (CartBean cartBean : list) { 
			if (cartBean.getMemberId().equals(Name)) {
		        cartcnt.add(cartBean);
		        int cartsize = cartcnt.size();
		        session.setAttribute("cartsize",cartsize);
			}
			System.out.println("選擇成功arr size"+cartcnt.size());
		}
		if(cartcnt.size()==0) {
	        session.setAttribute("cartsize",0);
		}
		return "main";
	}
	
	@GetMapping("/product/{productid}")
	public String bearitem(@PathVariable("productid") Integer productid, Model model, HttpSession session) {
		System.out.println("我是" + productid);
		session.setAttribute("PDid", productid);
		List<ProductBean> list = productRepository.findAll();
		for (ProductBean productBean : list) {
			if (productBean.getProductid().equals(productid)) {
				model.addAttribute("item1", productBean);
				String[] split = productBean.getProductPhoto().split(",");
				model.addAttribute("photolist", split);
				for (int i = 0; i < split.length; i++) {
					System.out.println("我是SP" + split.length);
				}
			}
		}
		List<PropertyBean> lis2 = propertyRepository.findAll();
		for (PropertyBean propertyBean : lis2) {
			if (propertyBean.getProductBean().getProductid().equals(productid)) {
				String[] valuefirst = propertyBean.getPropertyValue().split(",");
				System.out.println(propertyBean.getPropertyName());
				model.addAttribute("propertyNameFirst", propertyBean.getPropertyName());
				model.addAttribute("valuefirst", valuefirst);
				System.out.println(propertyBean.getPropertyValue());
			}
		}
		List<PropertyBeanSecond> list3 = propertySecondRepository.findAll();
		for (PropertyBeanSecond propertyBeanSecond : list3) {
			if (propertyBeanSecond.getProductBean().getProductid().equals(productid)) {
				String[] valuesecond = propertyBeanSecond.getPropertyValue().split(",");
				model.addAttribute("propertyNameSecond", propertyBeanSecond.getPropertyName());
				model.addAttribute("valuesecond", valuesecond);
				System.out.println(propertyBeanSecond.getPropertyValue());

			}
		}
		return "bear_item";
	}
	
	
	
	

	@PostMapping(value = ("/product/{productid}"))
	public String item(HttpSession session, Model model, CartBean CB, ProductBean PB, String typeValue1,String typeValue2) {
		Object Name = session.getAttribute("UserName");
		Object PDid = session.getAttribute("PDid");
		System.out.println("我是PbSRC"+PB.getProductPhoto());
		if(Name == null) { 
			return "redirect:/login-page";
		}
		List<MemberBean> list = memberRepository.findAll();
		List<ProductBean> list1 = productRepository.findAll();
		List<CartBean> list2 = CR.findAll(); 
		System.out.println("post");
		for (MemberBean memberBean : list) {
			// 會員底下搜尋 如過購物車數量為0,則執行productBean新增置購物車
			System.out.println("post1");
			for (CartBean cartBean : list2) {
				System.out.println("post2");
				// 如果有商品,則判別商品ID是否重複,重複則自行遞增
				if (cartBean.getProductBean().getProductid().equals(PDid) && cartBean.getMemberId().equals(Name)) {
					System.out.println("post3");
					System.out.println(cartBean.getMemberId());
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) / Integer.parseInt(cartBean.getQuantity())));
					cartBean.setQuantity(
							Integer.toString(PB.getProductStock() + Integer.parseInt(cartBean.getQuantity())));
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) * Integer.parseInt(cartBean.getQuantity())));
					CR.save(cartBean);
					return "redirect:/product/" + PDid;
				}
			} 
			System.out.println("post4");

			for (ProductBean productBean : list1) {
				if (productBean.getProductid().equals(PDid)) {
//					 System.out.println(productBean.getMemberBean().getUser_Account().equals("bee567"));
					System.out.println("lissssssssssssss ");
					CB.setQuantity(Integer.toString(PB.getProductStock()));
					CB.setTotalPrice(Integer.toString(PB.getProductStock() * productBean.getProductPrice()));
					CB.setProductBean(productBean);
					CB.setMemberId((String) Name);
					CB.setCartPhoto(PB.getProductPhoto());
					System.out.println("我是OBJ" + (String) Name);
					CR.save(CB);
					session.getAttribute("cartsize");
					int a = (Integer)session.getAttribute("cartsize");
					int cartsize =a+1;
					session.setAttribute("cartsize",cartsize);
					return "redirect:/product/" + PDid;
				}
			}
		}
		return "redirect:/product/" + PDid;
	}
	
	


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

 

	@RequestMapping("/NewProduct")
	public String NewProductpage() {
		// MyCategoriesPage1進入NewProduct
		System.out.println("BEE");
		return "NewProduct";
	}

	@RequestMapping("/NewProduct.page")
	@ResponseBody
	public String Newproduct(String first, String second, String third, String name, HttpSession session)
			throws IOException {
		// MyCategoriesPage1帶入的分類
		session.setAttribute("first", first);
		session.setAttribute("second", second);
		session.setAttribute("third", third);
		session.setAttribute("name", name);
		return "success";
	}

//	@PostMapping("/addProduct")
//	public String newproduct(@RequestParam(value = "productphoto", required = false) List<MultipartFile> productphoto,
//			String productID, String productName, Integer productPrice, Integer productStock, String productDetail,
//			String sellerProductCategory, String sellerID, String productFirstCategoryId, String propertyName,
//			String propertyValue, String propertySecondName, String propertySecondValue, String productSecondCategoryId,
//			String productThirdCategoryId, HttpSession session, ProductBean bean, PropertyBean propertyBean,
//			PropertyBeanSecond propertyBeanSecond, BindingResult result, Model model)
//			throws IllegalStateException, IOException {
//		// 放圖片的資料夾
//		File path = new File(uploadpath);
//		if (!path.exists()) {
//			path.mkdir();
//		}
//		// 圖片
//		List<String> filepathlist = new ArrayList<String>();
//
//		for (int i = 0; i < productphoto.size(); i++) {
//			if (productphoto.get(i).getName().equals("productphoto")) {
//				if (productphoto.get(i).getOriginalFilename().length() == 0)
//					continue;
//
//				String fileSuffix = productphoto.get(i).getOriginalFilename()
//						.substring(productphoto.get(i).getOriginalFilename().lastIndexOf("."));
//				String srcpath = (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())) + fileSuffix;
//				// 圖片存放位置
//				String filepath = uploadpath + "/" + srcpath;
//				productphoto.get(i).transferTo(new File(filepath));
//
//				// 資料庫存放圖片相對地址
//				String filepathsql = "/pic/upload/" + srcpath;
//				filepathlist.add(filepathsql);
//			}
//		}
//		String filepathstr = "";
//		for (String fruit : filepathlist) {
//			filepathstr += fruit + ",";
//		}
//		bean.setProductPhoto(filepathstr);
//
//		// 類別
//		List<Productcategory> ProductCategoryBeans = productCategoryRepository.findAll();
//		for (Productcategory productCategoryBean : ProductCategoryBeans) {
//			if (productCategoryBean.getCategoryName().equals(session.getAttribute("first"))) {
//				bean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
//			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("second"))) {
//				bean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
//			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("third"))) {
//				bean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
//			}
//
//		}
//		// 預設使用者
//		MemberBean mb = new MemberBean();
//		mb.setUserAccount("bsenger123");
//		bean.setMemberBean(mb);
//
//		ProductBean newbean = productService.insert(bean);
//		Integer newid = newbean.getProductid();
//
//		ProductBean pb = new ProductBean();
//		pb.setProductid(newid);
//		propertyBean.setProductBean(pb);
//		propertyBean.setPropertyName(propertyName);
//		propertyBean.setPropertyValue(propertyValue);
//
//		propertyBeanSecond.setProductBean(pb);
//		propertyBeanSecond.setPropertyName(propertySecondName);
//		propertyBeanSecond.setPropertyValue(propertySecondValue);
//		productRepository.save(bean);
//		productService.insertFirstProperty(propertyBean);
//		productService.insertSecondProperty(propertyBeanSecond);
//
//		return "redirect:/main-page";
//	}

	@RequestMapping("/UpdateProduct")
	public String NewProductpage(Model model, String productid,String productname, String category,Integer changecategorys) {

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
		if (Pid != null) {
			List<ProductBean> memberPD = productRepository.findProductBeanbyMember("anna38");
			for (ProductBean product : memberPD) {
				if (product.getProductid() == Pid) {
					if(changecategorys == null ) {
						model.addAttribute("name", product.getProductName());						
					}
					
					model.addAttribute("ProductDetail", product.getProductDetail());
					model.addAttribute("ProductPrice", product.getProductPrice());
					model.addAttribute("ProductStock", product.getProductStock());


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
			List<Productcategory> ProductCategoryBeans = productCategoryRepository.findAll();
			String[] category = allcategorys.split(",");
			for (Productcategory productCategoryBean : ProductCategoryBeans) {
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
			
			Optional<MemberBean> memberbean= memberRepository.findById("anna38");
			
			bean.setMemberBean(memberbean.get());
			model.addAttribute("name", productname);
			model.addAttribute("productid", productid);
			bean.setProductName(productname);
			ProductBean newbean = productService.insert(bean);
			Integer newid = newbean.getProductid();
			ProductBean pb = new ProductBean();
			pb.setProductid(newid);
			propertyBean.setProductBean(pb);
			propertyBean.setPropertyName(propertyName);
			propertyBean.setPropertyValue(propertyValue);
			propertyBeanSecond.setProductBean(pb);
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
							deleteList.remove(databasePhoto[i]);
						} else {

						}
					}
				}
				String frontPath = "C:/eclipseEE/shonpee/src/main/resources/static/";
				for (String delephoto : deleteList) {
					String deletephotoPath = frontPath + delephoto;
					File delefile = new File(deletephotoPath);
					if (delefile.exists()) {
						boolean isdelete = delefile.delete();
					}
				}
				// 排序新舊圖片順序
				for(String arr:oldPhotoArr) {
				}
				for (int i = 0; i < newPhoto.length; i++) {
					for (int j = 0; j < oldPhotoArr.length; j++) {
						if (oldPhotoArr[j].equals("newpic")) {
							oldPhotoArr[j] = newPhoto[i];
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
				
				productBean.setProductPhoto(newPhotoPath);
				productBean.setProductName(productname);
				productBean.setProductPrice(productPrice);
				productBean.setProductStock(productStock);
				productBean.setProductDetail(productDetail);
				// 類別
				List<Productcategory> ProductCategoryBeans = productCategoryRepository.findAll();
				String[] category = allcategorys.split(",");

				for (Productcategory productCategoryBean : ProductCategoryBeans) {
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
				if (updatePropertyBean!=null) {
					updatePropertyBean.setPropertyName(propertyName);
					updatePropertyBean.setPropertyValue(propertyValue);
					propertyServiceRepository.update(updatePropertyBean);
				}

				PropertyBeanSecond updatePropertyBeanSecond = propertySecondRepository.findPropertyBeanByProdcutID(productid);
				if (updatePropertyBeanSecond!=null) {
					updatePropertyBeanSecond.setPropertyName(propertySecondName);
					updatePropertyBeanSecond.setPropertyValue(propertySecondValue);
					propertySecondServiceRepository.update(updatePropertyBeanSecond);
				}
				productService.update(productBean);
			

			}

		}

		return "redirect:MyProduct";
	}

	@GetMapping("/MyProduct")
	public String MyProduct(Model model) {
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
		        	   ProductBean pbean= deleteProductBean.get();
			           productService.delete(pbean);  
		           }
		          
		}
       
		return "success";
	}

	
	@GetMapping("/main-page/{categoryId}")
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
		List<Productcategory> firstProductcategories = productCategoryRepository.findByParentId(0);
		return firstProductcategories;
	}

}
