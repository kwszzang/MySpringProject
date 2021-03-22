package controller.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;

@Controller
public class BoardListController {
	
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("bdao")
	private BoardDao bdao;
	
	public BoardListController() {
		this.mav= new ModelAndView();
	}
	
	
	@GetMapping(value = "boardlist.bo")
	public ModelAndView doGet(
			@RequestParam(value = "brd_type")String fakebrd_type) {
		
		int brd_type = Integer.parseInt(fakebrd_type);
		
		List<Board> list = this.bdao.SelectListByType(brd_type);
		
		this.mav.setViewName("boardList");
		return this.mav;
	}
	
	@RequestMapping("test.bo")
	public @ResponseBody String test(){
		String result = "ajax테스트";	
		
		return result;		
	}
	
}
