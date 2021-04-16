package controller.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.Album;
import bean.Album_Description;
import bean.Song;
import dao.AlbumDao;

@Controller
@RequestMapping( produces = "application/json; charset=utf8")
public class AlbumImsiController {
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("adao")
	private AlbumDao adao ;
	
	public AlbumImsiController() {
		this.mav = new ModelAndView();
	}
	
	@PostMapping(value = "searchalbum.al")
	public @ResponseBody List<Album>  doPost(
			@RequestBody Map<String, Object> map
			)throws Exception{
		String keyword = "%"+(String) map.get("keyword")+"%";
		int alm_type = Integer.parseInt( (String)map.get("alm_type"));
		
		
		System.out.println("키워드 입니다 : "+ keyword);
		
		
		Map<String, Object>albumMap = new HashMap<String, Object>();
		albumMap.put("alm_type", alm_type);
		albumMap.put("keyword", keyword);
		
		
		List<Album>list = this.adao.SelectAlbumListByKeyword(albumMap);
		
		return list;
	}
	
	@GetMapping(value = "albumdetail.al")
	public ModelAndView doGet(
			@RequestParam(value = "seq_alm")String fakeseq) {
		
		int seq_alm = Integer.parseInt(fakeseq);
		List<Album> list = this.adao.SelectAlbumListBySeq(seq_alm);
		
		List<Song> songlist = this.adao.SelectSongListBySeq(seq_alm);
		
		List<Album_Description> desList = this.adao.SelectDesListBySeq(seq_alm);
		if(desList.isEmpty()) {
			System.out.println("비였네용 ㅋㅋ");
		}else {
			System.out.println("안비였네용 ㅋㅋ");
		}
		System.out.println("============================");
		System.out.println("앨범 시퀀스 확인 : "+ seq_alm);
		
		for (int i = 0; i < desList.size(); i++) {
			System.out.println(desList.get(i).getAlde_content1());
			System.out.println(desList.get(i).getAlde_content2());
		}
		
		this.mav.addObject("desList",desList);
		this.mav.addObject("album",list);
		this.mav.addObject("songlist",songlist);
		this.mav.setViewName("album/albumDetail");
		return this.mav;
	}
}
