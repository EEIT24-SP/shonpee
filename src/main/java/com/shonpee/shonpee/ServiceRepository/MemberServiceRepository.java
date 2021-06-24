package com.shonpee.shonpee.ServiceRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shonpee.shonpee.domain.MemberBean;
import com.shonpee.shonpee.repository.MemberRepository;


@Service
@Transactional
public class MemberServiceRepository {
	@Autowired
	private MemberRepository MemberDao;

	public MemberBean login(String account, String password) {
		Optional<MemberBean> optional = MemberDao.findById(account);
//	System.out.println(optional);
		if (!optional.isEmpty()) {// 判斷帳號，存在的話
			MemberBean bean = optional.get();// 獲取帳號的物件 MemberBean(Memberid=3, Useraccount=catcatcat,
												// Password=cat8887,...
			System.out.println(!optional.isEmpty());
			if (password != null && password.length() != 0) {
				String pass = bean.getPassword(); // 輸入帳號對應的密碼
//				System.out.println(pass);
				String temp = password; // 使用者輸入的密碼
//				System.out.println(temp);
				// 先做註冊(加密)再來試試看
				if (pass.equals(temp)) {
//					System.out.println("okokokokok");
					return bean;
				}
			}

		}
		return null;
	}

	public boolean changePassword() {
		return true;
	}

	public MemberBean registered_member(String account, String password) {
		System.out.println("小夫我近來摟");
		Optional<MemberBean> optional = MemberDao.findById(account);
		System.out.println(optional);
//		MemberBean bean = optional.get();
		System.out.println(optional.isPresent());
		if (!optional.isPresent()) {// 如果帳號沒有重複
//			System.out.println("noooooorepet");
			String psword = MemberDao.QueryAnnotationParams(password);// 查詢密碼有沒有重複
//			System.out.println("password="+psword);
			if (psword == null) {
				MemberBean bean = new MemberBean();
				bean.setUser_Account(account);
				bean.setPassword(password);
				bean = MemberDao.save(bean);
//				System.out.println("申請成功");
//				System.out.println(bean);
				return bean;
			} //else {
//				model.addAttribute("erro","密碼已被註冊" );
//				System.out.println("密碼有人使用");
				//return null;
			//}

//		} else {
//			model.addAttribute("erro","帳號已被註冊" );
//			System.out.println("帳號重複");
//			return null;
		}		
		return null;
	}
}
