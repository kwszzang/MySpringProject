package controller.member;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import dao.MemberDao;

@Controller
public class MemberSignInController {

	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	@ModelAttribute("member")
	public Member member() {
		return new Member() ;
	}
	
	private ModelAndView mav = null;
	
	public MemberSignInController() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "signin.me")
	public ModelAndView doGet() {
		
		this.mav.setViewName("meSignInForm");
		
		return this.mav;
	}
	
	public ModelAndView doPost(
			@ModelAttribute("member") @Valid Member xxx,
			BindingResult error) {
		
		return this.mav;
		
	}
}
