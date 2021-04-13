package dao;

import java.util.List;

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

	
	
}
