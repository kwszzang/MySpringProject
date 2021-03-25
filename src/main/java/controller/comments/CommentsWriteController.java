package controller.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Comments;
import dao.CommentsDao;

@Controller
@RequestMapping( produces = "application/json; charset=utf8")
public class CommentsWriteController {
	
	@Autowired
	@Qualifier("cdao")
	private CommentsDao cdao ;
	
	public CommentsWriteController() {
		
	}
	
	@PostMapping(value = "writecomments.comm")
	public @ResponseBody List<Comments> doPost(){
		
		return null;
	}

}
