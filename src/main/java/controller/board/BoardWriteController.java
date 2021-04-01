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
			
			//this.mav.setViewName("redirect:/login.do");
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
			@RequestParam(value = "brd_type")int fakebrd_type
			) throws IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		
		System.out.println("글쓰기 컨트롤러 포스트 여기까지 오긴 오냐? ");
		int brd_type = fakebrd_type;
		System.out.println("게시판 타입 : " +board.getBrd_type());
		Member member = (Member)session.getAttribute("loginfo");
		String mid = member.getMid();
		
		
		board.setMid(mid);
		board.setBrd_type(brd_type);
			@SuppressWarnings("unchecked")
			List<Board> aList = (List<Board>) session.getAttribute("loginfo");
			
			
			System.out.println("바인딩 리절트 : "+error.getClass());
			for (int i = 0; i < aList.size(); i++) {
				System.out.println("테스트 : "+aList.get(i));
			}
			
			
			if(error.hasErrors()) {
				
				
//				out.println("<script>alert('유효성 검사 문제');</script>");
//				out.flush();
//				out.close();
				
				
				System.out.println("유효성 문제 있음 ");
				this.mav.setViewName("redirect:/writeboard.bo");
			}else {
				
//				out.println("<script>alert('유효성 검사 통과');</script>");
//				out.flush();
//				out.close();
				System.out.println("유효성에 문제 없음");
//				int cnt = -9999;
//				cnt = this.bdao.WriteBoard(board);
				
				if (files != null && files.length > 0) {
					System.out.println("첨부 파일이 들어왔네요");
				}
				
				this.mav.setViewName("redirect:/boardlist.bo");
			}
			

		
		return this.mav;
	}
}
