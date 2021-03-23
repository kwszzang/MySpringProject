package controller.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;
import utility.FlowParameters;
import utility.Paging;

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
			@RequestParam(value = "brd_type")String fakebrd_type
			) {
		
		int brd_type = Integer.parseInt(fakebrd_type);
		System.out.println("게시판 타입 확인 : "+brd_type);
		
		List<Board> lists = this.bdao.SelectListByType(brd_type);
		
		if(lists.isEmpty()) {
			System.out.println("리스트 비였음 ");
		}else {
			System.out.println("리스트 안 비였음");
		}
		
		this.mav.addObject("lists",lists);
		
		
		this.mav.setViewName("/board/boardList");
		
		return this.mav;
	}
	
}	
