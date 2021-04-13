package controller.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Album;
import dao.AlbumDao;

@Controller
public class AlbumListCotroller {
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("adao")
	private AlbumDao adao;
	
	public AlbumListCotroller() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "albumList.al")
	public ModelAndView doGet(
			@RequestParam(value = "alm_type") String faketype) {
		
		int alm_type = Integer.parseInt(faketype);
		List<Album> albumList =  this.adao.SelectAlbumListByType(alm_type);
		
		this.mav.addObject("albumList",albumList);
		this.mav.addObject("alm_type",alm_type);
		this.mav.setViewName("album/albumList");
		return this.mav;
	}
}
