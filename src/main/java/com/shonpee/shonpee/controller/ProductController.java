package com.shonpee.shonpee.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private ProductServiceRepository productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private PropertySecondRepository propertySecondRepository;

	@Autowired
	private ProductcategoryRepository productcategoryRepository;
	@Autowired
	private MemberRepository MR;
	@Autowired
	private CartRepository CR;

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
		List<MemberBean> list = MR.findAll();
		List<ProductBean> list1 = productRepository.findAll();
		List<CartBean> list2 = CR.findAll();
		List<PropertyBean> list3 = propertyRepository.findAll();
		List<PropertyBeanSecond> list4 = propertySecondRepository.findAll();
        ArrayList<CartBean> cartcnt = new ArrayList<CartBean>();

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
	public String MyCategoriesPage1() {

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
				String srcpath = (new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())) + fileSuffix;
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
			filepathstr += fruit + ",";
		}
		bean.setProductPhoto(filepathstr);

		// 類別
		List<Productcategory> ProductCategoryBeans = productcategoryRepository.findAll();
		for (Productcategory productCategoryBean : ProductCategoryBeans) {
			if (productCategoryBean.getCategoryName().equals(session.getAttribute("first"))) {
				bean.setProductFirstCategoryId(productCategoryBean.getCategoryId());
			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("second"))) {
				bean.setProductSecondCategoryId(productCategoryBean.getCategoryId());
			} else if (productCategoryBean.getCategoryName().equals(session.getAttribute("third"))) {
				bean.setProductThirdCategoryId(productCategoryBean.getCategoryId());
			}

		}
		// 預設使用者
		MemberBean mb = new MemberBean();
		mb.setUserAccount("bsenger123");
		bean.setMemberBean(mb);

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
		productRepository.save(bean);
		productService.insertFirstProperty(propertyBean);
		productService.insertSecondProperty(propertyBeanSecond);

		return "redirect:/main-page";
	}

	@GetMapping("/main-page/{categoryId}")
	public String showOneCategoryProducts(@PathVariable("categoryId") Integer categoryId, Model model) {
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
