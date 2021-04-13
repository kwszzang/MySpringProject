package controller.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Album;
import dao.AlbumDao;

@Controller
@RequestMapping( produces = "application/json; charset=utf8")
public class AlbumImsiController {

	
	@Autowired
	@Qualifier("adao")
	private AlbumDao adao ;
	
	public AlbumImsiController() {}
	
	@PostMapping(value = "searchalbum.al")
	public @ResponseBody List<Album>  doPost(
			@RequestBody Map<String, Object> map
			)throws Exception{
		String keyword = "%"+(String) map.get("keyword")+"%";
		int alm_type = Integer.parseInt( (String)map.get("alm_type"));
		
		
		System.out.println("키워드 입니다 : "+ keyword);
		System.out.println("타입입니다 : "+ alm_type);
		
		
		Map<String, Object>albumMap = new HashMap<String, Object>();
		albumMap.put("alm_type", alm_type);
		albumMap.put("keyword", keyword);
		
		
		List<Album>list = this.adao.SelectAlbumListByKeyword(albumMap);
		
		return list;
	}
}
