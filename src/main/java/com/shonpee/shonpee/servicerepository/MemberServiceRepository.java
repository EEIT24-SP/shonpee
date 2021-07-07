package com.shonpee.shonpee.servicerepository;

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
        if (!optional.isEmpty()) {// 判斷帳號，存在的話
            MemberBean bean = optional.get();// 獲取帳號的物件 MemberBean(Memberid=3, Useraccount=catcatcat,
                                                // Password=cat8887,...
            if (password != null && password.length() != 0) {
                String pass = bean.getPassword(); // 輸入帳號對應的密碼
                String temp = password; // 使用者輸入的密碼
                // 先做註冊(加密)再來試試看
                if (pass.equals(temp)) {
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
        Optional<MemberBean> optional = MemberDao.findById(account);
        if (!optional.isPresent()) {// 如果帳號沒有重複
            String psword = MemberDao.queryAnnotationParams(password);// 查詢密碼有沒有重複
            if (psword == null) {
                MemberBean bean = new MemberBean();
                bean.setUserAccount(account);
                bean.setPassword(password);
                bean = MemberDao.save(bean);
                return bean;
            }

//          else {
//            model.addAttribute("erro","帳號已被註冊" );
//            System.out.println("帳號重複");
//            return null;
        }        
        return null;
    }
}

