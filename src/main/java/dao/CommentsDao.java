package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Comments;

@Component("cdao")
public class CommentsDao {
	 private final String namespace = "NsComments.";

	    @Autowired
	    SqlSessionTemplate abcd;
	    
	public int CommentsCnt(int seq_brd) {
		
		return this.abcd.selectOne(namespace+"CommentsCnt",seq_brd);
	}

	public int WriteComments(String mid, int seq_brd, String comt_content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seq_brd", seq_brd);
		map.put("mid", mid);
		map.put("comt_content", comt_content);
		return this.abcd.insert(namespace+"WriteComments",map);
	}

	public int SelectComments() {
		return this.abcd.selectOne(namespace+"SelectComments");
	}

	public List<Comments> SelectRealComments(int seq_comt) {
		return this.abcd.selectList(namespace+"SelectRealComments",seq_comt);
	}

}
