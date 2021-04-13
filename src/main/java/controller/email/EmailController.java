package controller.email;


import java.util.HashMap;
import java.util.Random;


import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Email;

@Controller
@RequestMapping( produces = "application/json; charset=utf8")
public class EmailController {
	@Autowired private JavaMailSenderImpl mailSender;

	
	@ModelAttribute("email")
	public Email email() {
		return new Email() ;
	}
	
	public EmailController() {
	}
		@PostMapping(value = "emailcheck.do") 
		public @ResponseBody HashMap<String, Object>  SendMail(@RequestBody String mail) {
			
			Random random=new Random();  //난수 생성을 위한 랜덤 클래스
			String key="";  //인증번호 
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mail); //스크립트에서 보낸 메일을 받을 사용자 이메일 주소 
			//입력 키를 위한 코드
			for(int i =0; i<3;i++) {
				int index=random.nextInt(25)+65; //A~Z까지 랜덤 알파벳 생성
				key+=(char)index;
			}
			int numIndex=random.nextInt(9999)+1000; //4자리 랜덤 정수를 생성
			
			key+=numIndex;
			
			message.setSubject("인증번호 입력을 위한 메일 전송");
			
			message.setText("인증 번호 : "+key);
			mailSender.send(message);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			System.out.println("발송 될 인증 번호 : "+key);
			map.put("key", key);
			
			return map;
		}
 
 
}
