package controller.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.MemberDao;

@Controller
public class MemberIdCheckController {
	
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao ;
	
	public MemberIdCheckController() {
	}
	
	 @RequestMapping("idcheck.me")
	 @ResponseBody
	 public Map<Object, Object> doPost(
	    		@RequestBody String mid) {
	        System.out.println("아이디 중복체크 컨트롤러입니다." +mid);
	        int count = 0;
	        Map<Object, Object> map = new HashMap<Object, Object>();
	        count = mdao.IdCheck(mid);
	        map.put("cnt", count);
	        System.out.println("다오 클래스 실행후 count 값입니다. " + count);
	        return map;
	    }
}
