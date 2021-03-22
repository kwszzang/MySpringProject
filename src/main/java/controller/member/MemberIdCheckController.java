package controller.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.MemberDao;

@Controller
@RequestMapping()
public class MemberIdCheckController {
	
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao ;
	
	public MemberIdCheckController() {
	}
	
	 @PostMapping(value ="idcheck.me")
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
}
