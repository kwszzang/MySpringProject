package controller.member;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import dao.MemberDao;

@Controller
public class MemberLoginOutController {
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;

	private ModelAndView mav = null;
	
	public MemberLoginOutController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "login.do")
	public ModelAndView doGet() {
		this.mav.setViewName("/member/meLoginForm");
		
		return this.mav;
	}
	@PostMapping(value ="login.do")
	public ModelAndView doPost(
			@RequestParam(value = "id")String id,
			@RequestParam(value = "password")String password,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		System.out.println("로그인 컨트롤러 post");
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+password);
		
		Member bean = mdao.SelectById(id,password);
		String message = "";
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(bean == null) {
			message = "아이디나 비밀번호가 잘못되었습니다.";
			
			
			out.println("<script>alert('"+message+"');history.go(-1);</script>");
			out.flush();
			out.close();
//			this.mav.setViewName("redirect:/login.me");
		}else {
			session.setAttribute("loginfo", bean);
			message = "로그인이 완료되었습니다.\n메인화면으로 이동합니다.";
			out.write("<script>alert('"+message+"');</script>");
			
			this.mav.setViewName("redirect:/main.co");
			
		}
		return this.mav;
	}
	
	@GetMapping(value = "logout.do")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		this.mav.setViewName("redirect:/main.co");
		
		return this.mav;
	}
}
