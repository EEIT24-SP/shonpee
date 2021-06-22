package com.shonpee.shonpee;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shonpee.shonpee.Repository.MemberRepository;





@Controller
public class BeeController {
@Autowired
private MemberRepository CR;
	
	@RequestMapping("/main")
		public String test(HttpSession session) {
		return "main";
	}
}
