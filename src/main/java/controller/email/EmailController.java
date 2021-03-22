package controller.email;


import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.Email;

@Controller
public class EmailController {
	
	final String sendername = "김원식";
	final String senderemail = "kwsic2089@gmail.com";
	final String subject = "MySpringProject에서 보내드리는 인증 번호입니다.";
	
	private ModelAndView mav = null;
	
	@ModelAttribute("email")
	public Email email() {
		return new Email() ;
	}
	
	public EmailController() {
		this.mav= new ModelAndView();
	}
//		@PostMapping("CheckMail") // AJAX와 URL을 매핑시켜줌 
//		@ResponseBody  //AJAX후 다시 응답을 보내는게 아니기 때문에 적어줌, 안 적으면 이메일이 가도 개발자 도구에서 404오류가 뜸
//		public void SendMail(String mail) {
//			Random random=new Random();  //난수 생성을 위한 랜덤 클래스
//			String key="";  //인증번호 
//
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo(mail); //스크립트에서 보낸 메일을 받을 사용자 이메일 주소 
//			//입력 키를 위한 코드
//			for(int i =0; i<3;i++) {
//				int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
//				key+=(char)index;
//			}
//			int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
//			key+=numIndex;
//			message.setSubject("인증번호 입력을 위한 메일 전송");
//			message.setText("인증 번호 : "+key);
//			javaMailSender.send(message);
//		}
 
 
}
