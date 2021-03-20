package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.MemberDao;

@Controller
public class MemberSignInController {

	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	private ModelAndView mav = null;
	
	public MemberSignInController() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "signin.me")
	public ModelAndView doGet() {
		
		this.mav.setViewName("meSignInForm");
		
		return this.mav;
	}
	
}
