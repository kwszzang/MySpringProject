package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Board;

@Component("bdao")
public class BoardDao {
	private final String namespace = "NsBoard.";
	
	@Autowired
    SqlSessionTemplate abcd;

	public List<Board> SelectListByType(int brd_type, int startIndex, int endIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		RowBounds rowBounds = new RowBounds(startIndex, endIndex);
		map.put("brd_type",brd_type);
		return this.abcd.selectList(namespace+"SelectListByType",map,rowBounds);
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


	public int WriteBoard(HashMap<String, Object> mapBoard) {
		return this.abcd.insert(namespace+"WriteBoard",mapBoard);
	}

	public List<Board> SelectListByKeyword(Map<String,Object> map) {
		int startIndex = (Integer) map.get("startIndex");
		int endIndex = (Integer) map.get("endIndex");
		RowBounds rowBounds = new RowBounds(startIndex, endIndex);
		return this.abcd.selectList(namespace+"SelectListByKeyword",map,rowBounds);
	}

	public int SelectListCnt(int brd_type) {
		return this.abcd.selectOne(namespace+"SelectListCnt",brd_type);
	}

	public int SelectListSearchCnt(Map<String, Object> map2) {
		return this.abcd.selectOne(namespace+"SelectListSearchCnt",map2);
	}

	public int SelectcurrVal() {
		return this.abcd.selectOne(namespace+"SelectcurrVal");
	}

	public int insertFile(Map<String, Object> fileMap) {
		return this.abcd.insert(namespace+"insertFile",fileMap);
	}

	public List<Board> SelectFileList(int seq_brd) {
		return this.abcd.selectList(namespace+"SelectFileList",seq_brd);
	}

	public int DeleteBoard(int seq_brd) {
		return this.abcd.delete(namespace+"DeleteBoard",seq_brd);
	}

//	public List<Board> SelectHostComments(int seq_brd, String mid) {
//		HashMap<String, Object>map = new HashMap<String, Object>();
//		map.put("mid", map);
//		map.put("seq_brd", seq_brd);
//		return this.abcd.selectList(namespace+"SelectHostComments",map);
//	}



}
