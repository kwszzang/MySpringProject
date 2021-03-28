package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import dao.MemberDao;

@Controller
public class MemberSignUpController {

	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	@ModelAttribute("member")
	public Member Member() {
		return new Member() ;
	}
	
	private ModelAndView mav = null;
	
	public MemberSignUpController() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "signup.do")
	public ModelAndView doGet() {
		this.mav.setViewName("/member/meSignUpForm");
		
		return this.mav;
	}
	@PostMapping(value = "signup.do")
	public ModelAndView doPost(
			@ModelAttribute("member") @Valid Member xxx,
			BindingResult error,
			HttpServletResponse response
			) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(error.hasErrors()) {
			System.out.println("유효성 검사 문제");
			
			out.println("<script>alert('유효성 검사 문제');</script>");
			out.flush();
			out.close();
			
			
			this.mav.setViewName("/member/meSignUpForm");
		}else {
			System.out.println("유효성 검사 통과");
			
			out.println("<script>alert('유효성 검사 통과');</script>");
			out.flush();
			out.close();
			
			
			int cnt = - 9999;
			cnt = this.mdao.SiginInData(xxx);
			//로그인 화면으로 이동
			this.mav.setViewName("redirect:/login.do");
			
		}
		return this.mav;
		
	}
}
