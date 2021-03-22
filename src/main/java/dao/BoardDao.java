package dao;

import java.util.List;

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
}
