package dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Album;
@Component("adao")
public class AlbumDao {
	private final String namespace = "NsAlbum.";
	
	@Autowired
	SqlSessionTemplate abcd;

	public List<Album> SelectAlbumListByType(int alm_type) {
		return this.abcd.selectList(namespace+"SelectAlbumListByType",alm_type);
	}

	public List<Album> SelectAlbumListByKeyword(Map<String,Object> albumMap) {
		return this.abcd.selectList(namespace+"SelectAlbumListByKeyword",albumMap);
	}

	public List<Album> SelectAlbumListBySeq(int seq_alm) {
		return this.abcd.selectList(namespace+"SelectAlbumListBySeq",seq_alm);
	}

	
	
}
