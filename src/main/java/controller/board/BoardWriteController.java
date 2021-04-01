package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
import dao.BoardDao;

@Controller
public class BoardWriteController {
	
	@Autowired
	@Qualifier("bdao")
	BoardDao bdao;
	
	
	@ModelAttribute("board")
	public Board board() {
		return new Board() ;
	}
	
	private ModelAndView mav = null;
	
	public BoardWriteController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "writeboard.bo")
	public ModelAndView doGet(
			@RequestParam(value = "brd_type")int brd_type,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		Member member = (Member)session.getAttribute("loginfo");
		if(member == null){
			out.println("<script>alert('로그인 먼저 해주세요.');location.href = 'login.do';</script>");
			out.flush();
			out.close();
			
		}
		
		this.mav.addObject("brd_type",brd_type);
		this.mav.setViewName("board/boardWrite");
		
		return this.mav;
	}
	
	@PostMapping(value = "writeboard.bo")
	public ModelAndView doPost(
			@ModelAttribute("board") @Valid Board board,
			@RequestParam(value = "file_name",required = false) MultipartFile[] files,
			HttpSession session,
			BindingResult error,
			HttpServletResponse response,
			@RequestParam(value = "brd_type")int brd_type
			) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		Member member = (Member)session.getAttribute("loginfo");
		String mid = member.getMid();
		
		
		board.setMid(mid);
		board.setBrd_type(brd_type);
			
			//에러가 있다고 안 뜸 현재 ...ㅠㅠ 
			//유효성 검사하면 400 에러 
			if(error.hasErrors()) {
				out.write("<script>alert('유효성 검사 문제');history.go(-1);</script>");
				out.flush();
				out.close();
				
				
			}else {
				
				int cnt = -9999;
				cnt = this.bdao.WriteBoard(board);
				
				if (files != null && files.length > 0) {
				}
				
				this.mav.setViewName("redirect:/boardlist.bo");
			}
		
		return this.mav;
	}
}
