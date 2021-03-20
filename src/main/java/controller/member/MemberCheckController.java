package controller.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Email;

@Controller
public class MemberCheckController {
	
	
private ModelAndView mav = null;
	
	public MemberCheckController() {
		this.mav = new ModelAndView();
	}
	@ModelAttribute("email")
	public Email email() {
		return new Email() ;
	}
	
	@PostMapping(value = "emailcheck.me")
	public void doPost(
			@ModelAttribute("email") @Valid Email email,
			@RequestParam(value = "reciveremail")String reciveremail) {
		
	}
	
	
	
}
