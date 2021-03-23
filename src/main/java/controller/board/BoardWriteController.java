package controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardWriteController {

	private ModelAndView mav = null;
	
	public BoardWriteController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "writeboard.bo")
	public ModelAndView doGet() {
		
		this.mav.setViewName("board/boardWrite");
		
		return this.mav;
	}
}
