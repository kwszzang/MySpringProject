package controller.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Comments;
import dao.CommentsDao;

@Controller
@RequestMapping( produces = "application/json; charset=utf8")
public class CommentsWriteController {
	
	
	@Autowired
	@Qualifier("cdao")
	private CommentsDao cdao ;
	
	public CommentsWriteController() {
	}
	
	@PostMapping(value = "writecomments.bo")
	public @ResponseBody List<Comments>  doPost(
			@RequestBody Map<String, Object> map
			)throws Exception{
		
		String mid = (String) map.get("mid");
		String fakeseq_brd = (String) map.get("fakeseq_brd");
		
		int seq_brd = Integer.parseInt(fakeseq_brd);
		
		System.out.println("넘어온 아이디 : "+mid);
		System.out.println("넘어온 글 번호 : "+fakeseq_brd);
		System.out.println("넘어온 글 번호 int로 전환 : "+seq_brd);
		List<Comments> lists = new ArrayList<Comments>();
		
		return lists;
	}

}
