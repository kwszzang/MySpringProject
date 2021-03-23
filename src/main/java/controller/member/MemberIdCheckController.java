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
@RequestMapping( produces = "application/json; charset=utf8")
public class MemberIdCheckController {
	
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao ;
	
	public MemberIdCheckController() {
	}
	
	 @PostMapping(value ="idcheck.do")
	 public @ResponseBody HashMap<String, Object> doPost(
	    		@RequestBody String mid
	    		) {
	 	
        System.out.println("아이디 확인 : " + mid);
        
        int count = 0;
        
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        count = mdao.IdCheck(mid);
        
        map.put("cnt", count);
        
        
        return map;
	    }
	 
}