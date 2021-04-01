package controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
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
			@RequestParam(value = "brd_type")int fakebrd_type,
			HttpSession session
			) {
		
		int brd_type = fakebrd_type;
		
		//로그인 한 사람 정보 
		List<Member> loginList = new ArrayList<Member>();
		loginList.add((Member) session.getAttribute("loginfo"));
		
		this.mav.addObject("loginList",loginList);
		
		//작성한 사람 정보
		List<Board> lists = this.bdao.SelectListByType(brd_type);
		
		this.mav.addObject("lists",lists);
		
		
		this.mav.addObject("brd_type", brd_type);
		
		
		this.mav.setViewName("/board/boardList");
		
		return this.mav;
	}
	
}	
