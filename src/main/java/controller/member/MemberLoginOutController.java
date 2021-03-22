package controller.member;


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
	
	@GetMapping(value = "login.me")
	public ModelAndView doGet() {
		this.mav.setViewName("meLoginForm");
		
		return this.mav;
	}
	@PostMapping(value ="login.me")
	public ModelAndView doPost(
			@RequestParam(value = "id")String id,
			@RequestParam(value = "password")String password,
			HttpSession session) {
		System.out.println("로그인 컨트롤러 post");
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+password);
		
		Member bean = mdao.SelectById(id,password);
		String message = "";
		if(bean == null) {
			message = "아이디나 비밀번호가 잘못되었습니다.";
			
			this.mav.addObject("message", message);
			this.mav.setViewName("redirect:/login.me");
		}else {
			message = "로그인이 완료되었습니다.\n메인화면으로 이동합니다.";
			
			this.mav.addObject("message", message);
			session.setAttribute("loginfo", bean);
			this.mav.setViewName("redirect:/main.co");
		}
		return this.mav;
	}
	
	@GetMapping(value = "logout.me")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		this.mav.setViewName("redirect:/main.co");
		
		return this.mav;
	}
}
