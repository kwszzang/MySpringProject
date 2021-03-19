package controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private ModelAndView mav = null;
	
	public MainController() {
		this.mav  = new ModelAndView();
	}
	
	@GetMapping(value = "main.co")
	public ModelAndView doGet() {
		this.mav.setViewName("/main");
		
		return this.mav;
	}

}
