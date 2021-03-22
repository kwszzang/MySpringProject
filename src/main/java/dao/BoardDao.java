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

	public List<Board> SelectListByType(int brd_type) {
		return this.abcd.selectList(namespace+"SelectListByType",brd_type);
	}

	public int SelectTotalCount(String mode, String keyword) {
		// 해당 모드와 키워드를 이용하여 조건에 맞는 데이터의 건수를 구해줍니다.
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("mode", mode) ;
		map.put("keyword", "%" + keyword + "%") ;		
		return this.abcd.selectOne(namespace + "SelectTotalCount", map);		
	}

	public List<Board> SelectDataList(int offset, int limit, String mode, String keyword) {
		// 랭킹을 이용하여 해당 페이지의 데이터를 컬렉션으로 반환합니다.
		RowBounds rowBounds = new RowBounds(offset, limit);
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("mode", mode) ;
		map.put("keyword", "%" + keyword + "%") ;	
		return this.abcd.selectList(namespace + "SelectDataList", map, rowBounds);
	}
}
