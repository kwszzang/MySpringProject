package controller.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;

@Controller
public class BoardSearchPagingController {
	@Autowired
	@Qualifier("bdao")
	BoardDao bdao;
	
	
	
	private ModelAndView mav = null;
	
	public BoardSearchPagingController() {
		this.mav = new ModelAndView();
	}
	
	@PostMapping(value = "search.bo")
	public ModelAndView doPost(
			@RequestParam(value = "keyword")String keyword,
			@RequestParam(value = "mode")int fakemode,
			@RequestParam(value = "brd_type")String fakebrd_type) {
		//1= 제목, 2 = 작성자
		System.out.println("모드  : "+fakemode);
		String mode = "";
		if(fakemode == 1) {
			mode = "brd_subject";
		}else {
			mode = "mid";
		}
		
		//내가 입력한 검색어
		System.out.println("검색어 : "+keyword);
		int brd_type = Integer.parseInt(fakebrd_type);
		
		System.out.println("게시판 타입 : "+brd_type);
		
		
		String boardName = "";
		if(brd_type == 1) {
			boardName = "국내 게시판";
		}else {
			boardName = "해외 게시판";
		}
		
		this.mav.addObject("boardName",boardName);
		
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("keyword", "%"+keyword+"%");
		map.put("mode", mode);
		map.put("brd_type", brd_type);
		//검색어로 출력될 결과물
		List<Board> boardList = this.bdao.SelectListByKeyword(map);
		
		if(boardList.isEmpty()) {
			
			System.out.println("검색된게 없어요~~~ 왜이러냐~~");
		}else {
			 for(Board bean : boardList) {
				 System.out.println(bean.getBrd_subject());
				 System.out.println(bean.getMid());
			 }
		}
		
		this.mav.addObject("boardList",boardList);
		this.mav.setViewName("board/boardSearch");
		
		return this.mav;
	}
}
