package dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("cdao")
public class CommentsDao {
	 private final String namespace = "NsComments.";

	    @Autowired
	    SqlSessionTemplate abcd;
	    
	public int CommentsCnt(int seq_brd) {
		
		return this.abcd.selectOne(namespace+"CommentsCnt",seq_brd);
	}

}
