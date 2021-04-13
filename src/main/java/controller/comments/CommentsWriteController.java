package controller.comments;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Board;
import bean.Comments;
import bean.Member;
import dao.BoardDao;
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
		String comt_content = (String) map.get("comments");
		
		int seq_brd = Integer.parseInt(fakeseq_brd);
		
		System.out.println("넘어온 아이디 : "+mid);
		System.out.println("넘어온 글 번호 : "+fakeseq_brd);
		System.out.println("넘어온 글 번호 int로 전환 : "+seq_brd);
		System.out.println("넘어온 댓글 내용 : "+ comt_content);
		
		int cnt = - 9999;
		//댓글 테이블에 넣기
		cnt = this.cdao.WriteComments(mid,seq_brd,comt_content);
		//방금 넣은 댓글 시퀀스 값 가져오기 
		int seq_comt = this.cdao.SelectComments();
		System.out.println("확인하기 댓글테이블에 넣은 시퀀스 "+seq_comt);
		
		//
		
		//방금 넣은 댓글 전체 조회하기
		List<Comments> lists =this.cdao.SelectRealComments(seq_comt);
		

		return lists;
	}


}
