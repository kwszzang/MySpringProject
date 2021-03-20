package controller.email;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bean.Email;

@Controller // 컨트롤러 어노테이션 선언
@RequestMapping("email/*") // 공통적인 매핑 주소
public class EmailController {
	
	final String sendername = "김원식";
	final String senderemail = "kwsic2089@gmail.com";
	final String subject = "MySpringProject에서 보내드리는 인증 번호입니다.";
	
	@ModelAttribute("email")
	public Email email() {
		return new Email() ;
	}
	
	
    @Inject
    EmailService emailService; // 서비스를 호출하기위한 의존성 주입
 
 
    @GetMapping("emailcheck.me") // 확인 (메일발송) 버튼을 누르면 맵핑되는 메소드
    public String send(
    		@ModelAttribute Email email, 
    		Model model,
    		@RequestParam String reciveremail) {
    	
    	email.setSenderName(sendername);
    	email.setSenderMail(senderemail);
    	email.setReceiveMail(reciveremail);
    	email.setSubject(subject);
    	email.setMessage("test");
    	
    	
    	System.out.println("입력한 메일 주소 : "+reciveremail);
        try {
 
            emailService.sendMail(email); // dto (메일관련 정보)를 sendMail에 저장함
            model.addAttribute("message", "이메일이 발송되었습니다."); // 이메일이 발송되었다는 메시지를 출력시킨다.
 
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "이메일 발송 실패..."); // 이메일 발송이 실패되었다는 메시지를 출력
        }
        return "/email/write"; // 실패했으므로 다시 write jsp 페이지로 이동함
    }
}
