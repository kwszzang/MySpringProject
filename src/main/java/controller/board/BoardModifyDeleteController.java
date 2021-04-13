package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
import dao.BoardDao;

@Controller
public class BoardModifyDeleteController {
	
	@Autowired
	@Qualifier("bdao")
	BoardDao bdao;
	
	private ModelAndView mav = null;
	
	public BoardModifyDeleteController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "boardDelete.bo")
	public ModelAndView doGet(
			@RequestParam(value = "seq_brd")int seq_brd,
			@RequestParam(value = "brd_type")int brd_type,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//로그인 한 아이디 구하기
		Member member = (Member)session.getAttribute("loginfo");
		if(member != null) {
		String loginmid = member.getMid();
		
		
		//게시글 작성한 id 구하기 
		List<Board> brdList = this.bdao.SelectBoardList(seq_brd);
		String brd_mid = brdList.get(0).getMid();
		
		
		int cnt = -99999;
			
			if(loginmid.equalsIgnoreCase(brd_mid)) {
				cnt = this.bdao.DeleteBoard(seq_brd);
				System.out.println("삭제 컨트롤러에 넘어온 타입 확인 : "+brd_type);
				this.mav.setViewName("redirect:/boardlist.bo");
			}else {
				out.println("<script>alert('권한이 없습니다.');history.go(-1);</script>");
				out.flush();
				out.close();
			}
			
			
		}else {
			out.println("<script>alert('로그인 먼저 해주세요.');location.href = 'login.do';</script>");
			out.flush();
			out.close();
		}
		this.mav.addObject("brd_type", brd_type);
		return this.mav;
	}
	
	
	@PostMapping(value = "boardmodify.bo")
	public ModelAndView doPost(
			HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response
			) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("설마 컨트롤러단 돌아가나????");
		int seq_brd = Integer.parseInt(request.getParameter("seq_brd")) ;
		int brd_type = Integer.parseInt(request.getParameter("brd_type")) ;
		
		String brd_subject = request.getParameter("Nbrd_subject");
		String brd_content = request.getParameter("Nbrd_content");
		
	
		
		//로그인 한 아이디 구하기
		Member member = (Member)session.getAttribute("loginfo");
		if(member != null) {
			String mid = member.getMid();
			
			
			//게시글 작성한 id 구하기 
			List<Board> brdList = this.bdao.SelectBoardList(seq_brd);
			String brd_mid = brdList.get(0).getMid();
			
			
			int cnt = -99999;
				
				if(mid.equalsIgnoreCase(brd_mid)) {
					//수정 dao 작업이 이루어질 곳 
					
					HashMap<String, Object> modifyMap = new HashMap<String, Object>();
					modifyMap.put("seq_brd", seq_brd);
					modifyMap.put("brd_subject", brd_subject);
					modifyMap.put("brd_content", brd_content);
					
					cnt = this.bdao.ModifyBoard(modifyMap);
					
					this.mav.setViewName("redirect:/boardlist.bo");
				}else {
					out.println("<script>alert('권한이 없습니다.');history.go(-1);</script>");
					out.flush();
					out.close();
				}
				
			}else {
				out.println("<script>alert('로그인 먼저 해주세요.');location.href = 'login.do';</script>");
				out.flush();
				out.close();
			}
		
		this.mav.addObject("brd_type", brd_type);
		return this.mav;
	}
}
