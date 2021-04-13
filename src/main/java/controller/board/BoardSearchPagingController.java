package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;
import utility.Paging;

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
	public  ModelAndView doPost(
			@RequestParam(value = "keyword")String keyword,
			@RequestParam(value = "mode")int fakemode,
			@RequestParam(value = "brd_type")int brd_type,
			@RequestParam(defaultValue="1") int curPage,
			HttpServletResponse response
			) throws IOException {
		//1= 제목, 2 = 작성자
		String mode = "";
		if(fakemode == 1) {
			mode = "brd_subject";
		}else {
			mode = "mid";
		}
		
		String boardName = "";
		if(brd_type == 1) {
			boardName = "국내 게시판";
		}else {
			boardName = "해외 게시판";
		}
		
		//검색어로 출력될 결과물
		//페이징 처리
		Map<String, Object>map2= new HashMap<String, Object>();
		map2.put("keyword", "%"+keyword+"%");
		map2.put("mode", mode);
		map2.put("brd_type", brd_type);
		int listCnt = this.bdao.SelectListSearchCnt(map2);
		
		Paging paging = new Paging(listCnt, curPage);
		
		int startIndex = paging.getStartIndex();
        int endIndex =startIndex+10;
        
        Map<String, Object>map = new HashMap<String, Object>();
		map.put("keyword", "%"+keyword+"%");
		map.put("mode", mode);
		map.put("brd_type", brd_type);
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
        
		List<Board> boardList = this.bdao.SelectListByKeyword(map);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(boardList.isEmpty()) {
			out.println("<script>alert('검색된 결과가 없습니다.');history.go(-01);</script>");
			out.flush();
			out.close();
		}else {
			this.mav.addObject("boardList",boardList);
			this.mav.addObject("brd_type",brd_type);
			this.mav.addObject("boardName",boardName);
			this.mav.addObject("listCnt", listCnt);
			this.mav.addObject("pagination", paging);
			this.mav.addObject("mode",fakemode);
			this.mav.addObject("keyword",keyword);
			
			this.mav.setViewName("board/boardSearch");
		}
		
		
		return this.mav;
	}
	
	//하 진짜 비효율...
	@GetMapping(value = "search.bo")
	public ModelAndView doGet(
			@RequestParam(value = "keyword")String keyword,
			@RequestParam(value = "mode")int fakemode,
			@RequestParam(value = "brd_type")int brd_type,
			@RequestParam(defaultValue="1") int curPage,
			HttpServletResponse response)throws IOException {
		//1= 제목, 2 = 작성자
		String mode = "";
		if(fakemode == 1) {
			mode = "brd_subject";
		}else {
			mode = "mid";
		}
		
		String boardName = "";
		if(brd_type == 1) {
			boardName = "국내 게시판";
		}else {
			boardName = "해외 게시판";
		}
		
		//검색어로 출력될 결과물
		//페이징 처리
		Map<String, Object>map2= new HashMap<String, Object>();
		map2.put("keyword", "%"+keyword+"%");
		map2.put("mode", mode);
		map2.put("brd_type", brd_type);
		int listCnt = this.bdao.SelectListSearchCnt(map2);
		
		Paging paging = new Paging(listCnt, curPage);
		
		int startIndex = paging.getStartIndex();
        int endIndex =startIndex+10;
        
        Map<String, Object>map = new HashMap<String, Object>();
		map.put("keyword", "%"+keyword+"%");
		map.put("mode", mode);
		map.put("brd_type", brd_type);
		map.put("startIndex", startIndex);
		map.put("endIndex", endIndex);
        
		List<Board> boardList = this.bdao.SelectListByKeyword(map);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(boardList.isEmpty()) {
			out.println("<script>alert('검색된 결과가 없습니다.');history.go(-01);</script>");
			out.flush();
			out.close();
		}else {
			this.mav.addObject("boardList",boardList);
			this.mav.addObject("brd_type",brd_type);
			this.mav.addObject("boardName",boardName);
			this.mav.addObject("listCnt", listCnt);
			this.mav.addObject("pagination", paging);
			
			this.mav.setViewName("board/boardSearch");

		}
		
		return this.mav;
	}
}
