package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Song;

@Component("sdao")
public class SongDao {
	private final String namespace = "NsSong.";
	
	@Autowired
	SqlSessionTemplate abcd;


	public List<Song> SelectSongAndDescripList(int seq_song) {
		return this.abcd.selectList(namespace+"SelectSongAndDescripList",seq_song);
	}
}
