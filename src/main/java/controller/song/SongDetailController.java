package controller.song;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Song;
import dao.SongDao;

@Controller
public class SongDetailController {
	
	@Autowired
	@Qualifier("sdao")
	private SongDao sdao ;
	
	private ModelAndView mav = null;
	
	public SongDetailController() {
		this.mav= new ModelAndView();
	}
	
	@GetMapping(value = "songdetail.so")
	public ModelAndView doGet(
			@RequestParam(value = "seq_song" )int seq_song) {
		
		List<Song> songList =  this.sdao.SelectSongAndDescripList(seq_song);
		
		this.mav.addObject("songList",songList);
		this.mav.setViewName("song/songDetail");
		return this.mav;
	}
	
}
