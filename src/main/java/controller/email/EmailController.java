package controller.email;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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
	
    @Inject
    EmailService emailService; // 서비스를 호출하기위한 의존성 주입
 
 
    @GetMapping("emailcheck.me") // 확인 (메일발송) 버튼을 누르면 맵핑되는 메소드
    public void send(
    		@ModelAttribute Email email, 
    		@RequestParam String reciveremail) {
    	
    	email.setSenderName(sendername);
    	email.setSenderMail(senderemail);
    	email.setReceiveMail(reciveremail);
    	email.setSubject(subject);
    	email.setMessage("test");
    	
    	
    	System.out.println("입력한 메일 주소 : "+reciveremail);
        try {
 
            //emailService.sendMail(email); // dto (메일관련 정보)를 sendMail에 저장함
            this.mav.addObject("message", "이메일이 발송되었습니다."); // 이메일이 발송되었다는 메시지를 출력시킨다.
 
        } catch (Exception e) {
            e.printStackTrace();
            this.mav.addObject("message", "이메일 발송 실패..."); // 이메일 발송이 실패되었다는 메시지를 출력
        }
    }
}
