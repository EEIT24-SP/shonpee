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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shonpee.shonpee.domain.CartBean;
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
import com.shonpee.shonpee.servicerepository.ProductServiceRepository;
import com.shonpee.shonpee.servicerepository.PropertySecondServiceRepository;
import com.shonpee.shonpee.servicerepository.PropertyServiceRepository;

@Controller
public class ProductController {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PropertyRepository propertyRepository;
	@Autowired
	private ProductcategoryRepository productCategoryRepository;
	@Autowired
	private PropertySecondRepository propertySecondRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductServiceRepository productService;
	@Autowired
	private PropertyServiceRepository propertyServiceRepository;
	@Autowired
	private PropertySecondServiceRepository propertySecondServiceRepository;

	@Value("${upload-path}")
	private String uploadpath;


	@Value("${deletePath}")
	private String deletePath;
	
	@PostMapping("/main-page/search")
	public String search(Model model,String searchname) {
		model.addAttribute("categories", listFirstCategories());
		model.addAttribute("searchname", searchname);
		List<ProductBean> searchproducts = productRepository.findProductBeanbyName(searchname);
		List<ProductBean> search= new ArrayList<ProductBean>();
		List<String> photo = new ArrayList<String>();
		for(ProductBean product:searchproducts) {
			if(product.getProductStatus()==null) {
				String[] productPhoto = product.getProductPhoto().split(",");
				photo.add(productPhoto[0]);
				search.add(product);
			}
			
		}
		model.addAttribute("photo",photo);
		model.addAttribute("products", search);
		return "main";
	}

	@GetMapping("/main-page")
	public String mainPage(Model model, HttpSession session) {
		model.addAttribute("categories", listFirstCategories());
		Object Name = session.getAttribute("UserName");
		List<CartBean> list = cartRepository.findAll();
		ArrayList<CartBean> cartcnt = new ArrayList<CartBean>();
		// 搜尋會員,顯示符合當前帳號的購物車資料
		for (CartBean cartBean : list) {
			if (cartBean.getMemberId().equals(Name)) {
				cartcnt.add(cartBean);
				int cartsize = cartcnt.size();
				session.setAttribute("cartsize", cartsize);
			}
		}
		if (cartcnt.size() == 0) {
			session.setAttribute("cartsize", 0);
		}
		return "main";
	}


	// 產品頁
	@GetMapping("/product/{productid}")
	public String bearitem(@PathVariable("productid") Integer productid, Model model, HttpSession session) {
		session.setAttribute("PDid", productid);
		List<ProductBean> list = productRepository.findAll();
		for (ProductBean productBean : list) {
			if (productBean.getProductid().equals(productid)) {
				model.addAttribute("item1", productBean);
				String[] split = productBean.getProductPhoto().split(",");
				model.addAttribute("photolist", split);
			}
		}
		List<PropertyBean> lis2 = propertyRepository.findAll();
		for (PropertyBean propertyBean : lis2) {
			if (propertyBean.getProductBean().getProductid().equals(productid)) {
				String[] valuefirst = propertyBean.getPropertyValue().split(",");
				model.addAttribute("propertyNameFirst", propertyBean.getPropertyName());
				model.addAttribute("valuefirst", valuefirst);
			}
		}
		List<PropertyBeanSecond> list3 = propertySecondRepository.findAll();
		for (PropertyBeanSecond propertyBeanSecond : list3) {
			if (propertyBeanSecond.getProductBean().getProductid().equals(productid)) {
				String[] valuesecond = propertyBeanSecond.getPropertyValue().split(",");
				model.addAttribute("propertyNameSecond", propertyBeanSecond.getPropertyName());
				model.addAttribute("valuesecond", valuesecond);
			}
		}
		return "bear_item";
	}


	// 新增商品
	@PostMapping(value = ("/product/{productid}"))

	public String item(HttpSession session, Model model, CartBean CB, ProductBean PB, String typeValue1,
			String typeValue2) {
		String UserName = String.valueOf(session.getAttribute("UserName"));		
		Object PDid = session.getAttribute("PDid");
		if (UserName == ""||UserName=="null"||UserName.isEmpty()) {

			return "redirect:/login-page";
		}
		List<MemberBean> list = memberRepository.findAll();
		List<ProductBean> list1 = productRepository.findAll();
		List<CartBean> list2 = cartRepository.findAll();
		for (MemberBean memberBean : list) {
			// 會員底下搜尋 如過購物車數量為0,則執行productBean新增置購物車
			for (CartBean cartBean : list2) {
				// 如果有商品,則判別商品ID是否重複,重複則自行遞增
				if (cartBean.getProductBean().getProductid().equals(PDid) && cartBean.getMemberId().equals(UserName)
						&& cartBean.getTypeValue1() ==CB.getTypeValue1() && cartBean.getTypeValue2()==CB.getTypeValue2()) {
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) / Integer.parseInt(cartBean.getQuantity())));
					cartBean.setQuantity(
							Integer.toString(PB.getProductStock() + Integer.parseInt(cartBean.getQuantity())));
					cartBean.setTotalPrice(Integer.toString(
							Integer.parseInt(cartBean.getTotalPrice()) * Integer.parseInt(cartBean.getQuantity())));
					cartRepository.save(cartBean);
					return "redirect:/product/" + PDid;
				}
			}

			for (ProductBean productBean : list1) {
				if (productBean.getProductid().equals(PDid)) {
					CB.setQuantity(Integer.toString(PB.getProductStock()));
					CB.setTotalPrice(Integer.toString(PB.getProductStock() * productBean.getProductPrice()));
					CB.setProductBean(productBean);
					CB.setMemberId((String) UserName);
					CB.setCartPhoto(PB.getProductPhoto());
					cartRepository.save(CB);

					session.getAttribute("cartsize");
					int a = (Integer) session.getAttribute("cartsize");
					int cartsize = a + 1;
					session.setAttribute("cartsize", cartsize);
					return "redirect:/product/" + PDid;
				}
			}
		}
		return "redirect:/product/" + PDid;
	}

	@RequestMapping("/MyCategoriesPage1")
	public String MyCategoriesPage1(Model model, Integer productid, Integer changecategorys) {
		if (productid != null) {
			Optional<ProductBean> bean = productRepository.findById(productid);
			if (bean.isPresent()) {
				ProductBean productBean = bean.get();
				model.addAttribute("productname", productBean.getProductName());
				model.addAttribute("productid", productid);
				model.addAttribute("changecategorys", changecategorys);
			}
		}

		return "MyCategoriesPage1";
	}



	@RequestMapping("/UpdateProduct")
	public String NewProductpage(Model model, String productid, String productname, String category,HttpSession session,
			Integer changecategorys) {
		String UserName = String.valueOf(session.getAttribute("UserName"));
		if(UserName!="null"||UserName!="") {
			// 新增時 顯示分類和名字
			if (productid == null || productid == "") {
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
				List<ProductBean> memberPD = productRepository.findProductBeanbyMember(UserName);
				for (ProductBean product : memberPD) {
					if (product.getProductid() == Pid) {
						if (changecategorys == null) {
							model.addAttribute("name", product.getProductName());
						}
	
						model.addAttribute("ProductDetail", product.getProductDetail());
						model.addAttribute("ProductPrice", product.getProductPrice());
						model.addAttribute("ProductStock", product.getProductStock());
	
						// 照片
						String[] photos = product.getProductPhoto().split(",");
						model.addAttribute("photos", photos);
	
						// 類別
						if (changecategorys != null) {
							String[] categorys = category.split(",");
							model.addAttribute("firstCategory", categorys[0]);
							model.addAttribute("secondCategory", categorys[1]);
							if (categorys.length > 2) {
								model.addAttribute("thirdCategory", categorys[2]);
							}
							model.addAttribute("newname", productname);
						} else {
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
						}
						
						PropertyBeanSecond propertysecond = propertySecondRepository.findPropertyBeanByProdcutID(Pid);
						if (propertysecond != null) {
							model.addAttribute("secondPropertyName", propertysecond.getPropertyName());
							String[] secondPropertyValue = propertysecond.getPropertyValue().split(",");
							model.addAttribute("secondPropertyValue", secondPropertyValue);
						}
					}
	
				}
			}
		}else {
			return "redirect:main-page"; 
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
		String UserName = String.valueOf(session.getAttribute("UserName"));
		if(UserName!="null"||UserName!="") {
			File path = new File(uploadpath);
			if (!path.exists()) {
				path.mkdir();
			}
			// 圖片
			if (productid == null) {
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
	
				Optional<MemberBean> memberbean = memberRepository.findById(UserName);
				
				bean.setMemberBean(memberbean.get());
				model.addAttribute("name", productname);
				model.addAttribute("productid", productid);
				bean.setProductName(productname);
				ProductBean newbean = productService.insert(bean);
				Integer newid = newbean.getProductid();
				ProductBean product = new ProductBean();
				product.setProductid(newid);
				propertyBean.setProductBean(product);
				propertyBean.setPropertyName(propertyName);
				propertyBean.setPropertyValue(propertyValue);
				propertyBeanSecond.setProductBean(product);
				propertyBeanSecond.setPropertyName(propertySecondName);
				propertyBeanSecond.setPropertyValue(propertySecondValue);
	
				productService.insertFirstProperty(propertyBean);
				productService.insertSecondProperty(propertyBeanSecond);
			} else {
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
					String[] oldPhotoArr = oldPhoto.split(",");
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
					for (String delephoto : deleteList) {
						String deletephotoPath = deletePath + delephoto;
						File delefile = new File(deletephotoPath);
						if (delefile.exists()) {
							boolean isdelete = delefile.delete();
						}
					}
					// 排序新舊圖片順序
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
					if (updatePropertyBean != null) {
						updatePropertyBean.setPropertyName(propertyName);
						updatePropertyBean.setPropertyValue(propertyValue);
						propertyServiceRepository.update(updatePropertyBean);
					}
	
					PropertyBeanSecond updatePropertyBeanSecond = propertySecondRepository
							.findPropertyBeanByProdcutID(productid);
					if (updatePropertyBeanSecond != null) {
						updatePropertyBeanSecond.setPropertyName(propertySecondName);
						updatePropertyBeanSecond.setPropertyValue(propertySecondValue);
						propertySecondServiceRepository.update(updatePropertyBeanSecond);
					}
					productService.update(productBean);
	
				}
	
			}
		}else {
			return "redirect:main-page"; 
		}
		return "redirect:MyProduct";
	}

	@GetMapping("/MyProduct")
	public String MyProduct(Model model,HttpSession session) {
		// 搜索所有的資料
		// 將會員資料新增的商品巡訪找出來
		String UserName = String.valueOf(session.getAttribute("UserName"));
		if(UserName!="null"||UserName!="") {
			List<ProductBean> list= new ArrayList<>();
			List<String> allphotos = new ArrayList<String>();
			List<PropertyBean> PropertyFirstList = new ArrayList<PropertyBean>();
			List<PropertyBeanSecond> PropertySecondList = new ArrayList<PropertyBeanSecond>();
			List<ProductBean> MemberProduct = productRepository.findProductBeanbyMember(UserName);
			for (ProductBean Product : MemberProduct) {
				if (Product.getProductStatus()==null) {
					String[] photos = Product.getProductPhoto().split(",");
					allphotos.add(photos[0]);
					Integer PID = Product.getProductid();
					if(propertyRepository.findPropertyBeanByProdcutID(PID)!=null) {
						PropertyBean propertyProduct = propertyRepository.findPropertyBeanByProdcutID(PID);
						PropertyFirstList.add(propertyProduct);
					}
					if(propertySecondRepository.findPropertyBeanByProdcutID(PID)!=null) {
					PropertyBeanSecond propertyProductSecond = propertySecondRepository.findPropertyBeanByProdcutID(PID);
						PropertySecondList.add(propertyProductSecond);
						list.add(Product);
					}
				}
			}
			model.addAttribute("MemberProduct", list);	
			model.addAttribute("allphotos", allphotos);
			model.addAttribute("PropertyFirstList", PropertyFirstList);
			model.addAttribute("PropertySecondList", PropertySecondList);
			
		}else {
			return "redirect:main-page"; 
		}
		
		

		return "MyProduct";
	}

	@RequestMapping("/DeleteProduct")
	@ResponseBody
	public String deleteProduct(@RequestParam("productid") Integer prodcutid) {
		Optional<ProductBean> deleteProductBean = productRepository.findById(prodcutid);
		List<CartBean> cartBeans = cartRepository.findAll();
		if (deleteProductBean.isPresent()) {
			for (CartBean cartBean : cartBeans) {
				if (cartBean.getProductBean().getProductid().equals(prodcutid)) {
					cartRepository.deleteById(cartBean.getCartId());
				}
			}
			ProductBean prbean = deleteProductBean.get();
			prbean.setProductStatus(0);
			productRepository.save(prbean);
		}
		return "success";
	}

	@GetMapping("/main-page/{categoryId}")
	public String showOneCategoryProducts(@PathVariable("categoryId") Integer categoryId, Model model) {
		// 找出第一層的全部類別，放入頁面
		model.addAttribute("categories", listFirstCategories());
		Optional<Productcategory> category=productCategoryRepository.findById(categoryId);
		if(category.isPresent()) {
			model.addAttribute("searchname", category.get().getCategoryName());
		}
		
		// 找出該分類的產品，放入頁面
		List<ProductBean> productsOfTheCategory = productRepository.findByProductFirstCategoryId(categoryId);
		List<ProductBean> onSalesProducts= new ArrayList<ProductBean>();
		List<String> photo = new ArrayList<String>();
		for(ProductBean product:productsOfTheCategory) {
			if(product.getProductStatus()==null) {
				String[] productPhoto = product.getProductPhoto().split(",");
				photo.add(productPhoto[0]);
				onSalesProducts.add(product);
			}
			
		}
		model.addAttribute("photo",photo);
		model.addAttribute("products", onSalesProducts);
		return "main";
	}


	
	
	
	
	// 以下是抽離的重複程式，沒有設定RequestMapping路徑
	public List<Productcategory> listFirstCategories() {
		List<Productcategory> firstProductcategories = productCategoryRepository.findByParentId(0);
		return firstProductcategories;
	}

}
