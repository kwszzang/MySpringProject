package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Board;

@Component("bdao")
public class BoardDao {
	private final String namespace = "NsBoard.";
	
	@Autowired
    SqlSessionTemplate abcd;

	public List<Board> SelectListByType(int brd_type) {
		return this.abcd.selectList(namespace+"SelectListByType",brd_type);
	}

	public int UpdateHitnum(int seq_brd) {
		
		return this.abcd.update(namespace+"UpdateHitnum",seq_brd);
		
	}

	public List<Board> SelectComments(int seq_brd) {
		return this.abcd.selectList(namespace+"SelectComments",seq_brd);
	}

	public List<Board> SelectBoardList(int seq_brd) {
		return this.abcd.selectList(namespace+"SelectBoardList",seq_brd);
	}



}
