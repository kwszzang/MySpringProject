package controller.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;
import dao.CommentsDao;

@Controller
public class BoardDetailVIewController {
	@Autowired
	@Qualifier("bdao")
	private BoardDao bdao;
	
	@Autowired
	@Qualifier("cdao")
	private CommentsDao cdao;
	
	private ModelAndView mav = null;
	
	public BoardDetailVIewController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "detailview.bo")
	public ModelAndView doGet(
			@RequestParam(value = "seq_brd")int seq_brd
			) {
		
		
		//조회수 올리기 
		int cnt = -9999;
		cnt = this.bdao.UpdateHitnum(seq_brd);
		
		//제목, 내용, 댓글수, 날짜, 글쓴이 보이기
		List<Board> boardlist = this.bdao.SelectBoardList(seq_brd);
		
		this.mav.addObject("boardlist", boardlist);
		
		//댓글수 조회용
		int count = 0;
	        
		count = this.cdao.CommentsCnt(seq_brd);
	        
		this.mav.addObject("cnt",count);
		
		//댓글 조회용 
		List<Board> lists = this.bdao.SelectComments(seq_brd);
		
		this.mav.addObject("commentlists", lists);
		
		
		
		this.mav.setViewName("/board/boardDetail");
		
		return this.mav;
	}
}
