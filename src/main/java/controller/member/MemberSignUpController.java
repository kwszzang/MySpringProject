package controller.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import dao.MemberDao;

@Controller
public class MemberSignUpController {

	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	@ModelAttribute("member")
	public Member Member() {
		return new Member() ;
	}
	
	private ModelAndView mav = null;
	
	public MemberSignUpController() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "signup.do")
	public ModelAndView doGet() {
		
		this.mav.setViewName("/member/meSignUpForm");
		
		return this.mav;
	}
	@PostMapping(value = "signup.do")
	public ModelAndView doPost(
			@ModelAttribute("member") @Valid Member xxx,
			BindingResult error
			) {
		  System.out.println("name:"+xxx.getName());
          System.out.println("pass:"+xxx.getPassword());
          
         System.out.println("error:"+error.hasErrors());
		
		if(error.hasErrors()) {
			System.out.println("유효성 검사 문제");
			
			this.mav.setViewName("/member/meSignUpForm");
		}else {
			System.out.println("유효성 검사 통과");
			//인설트dao 할거 
			int cnt = - 9999;
			//cnt = this.mdao.SiginInData(xxx);
			//로그인 화면으로 이동
			//this.mav.setViewName("redirect:/login.do");
			
			this.mav.setViewName("/member/meSignUpForm");
		}
		return this.mav;
		
	}
}
