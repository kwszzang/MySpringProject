package controller.member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MemberDao;

@Controller
@RequestMapping()
public class MemberIdCheckController {
	
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao ;
	
	public MemberIdCheckController() {
	}
	
	 @PostMapping(value ="idcheck.me",produces = "application/json; charset=utf8")
	 @ResponseBody
	 public HashMap<String, Object> doPost(
	    		@RequestBody String mid) {
	        System.out.println("아이디 중복체크 컨트롤러입니다." +mid);
	        int count = 0;
	        HashMap<String, Object> map = new HashMap<String, Object>();
	        count = mdao.IdCheck(mid);
	        map.put("cnt", count);
	        System.out.println("다오 클래스 실행후 count 값입니다. " + count);
	        return map;
	    }
	 
//	 @RequestMapping(value="/test.do")
//	   public @ResponseBody ModelAndView doPost(
//			   @RequestParam HashMap<String, Object> commandMap){
//	      ModelAndView mav = new ModelAndView();
//	      
//	      int count = 0;
//	      count = mdao.IdCheck(mid);
//	      mav.addObject("cnt", count);
//	      
//	      
//	      mav.setViewName("jsonView");
//	      return mav;
//	      
//	   }
}
