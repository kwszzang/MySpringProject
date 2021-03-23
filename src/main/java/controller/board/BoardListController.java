package controller.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import dao.BoardDao;
import utility.FlowParameters;
import utility.Paging;

@Controller
public class BoardListController {
	
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("bdao")
	private BoardDao bdao;
	
	public BoardListController() {
		this.mav= new ModelAndView();
	}
	
	
	@GetMapping(value = "boardlist.bo")
	public ModelAndView doGet(
			@RequestParam(value = "brd_type")String fakebrd_type,
			@RequestParam(value = "pageNumber", required = false) String pageNumber, 
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "keyword", required = false) String keyword,
			HttpServletRequest request) {
		
		int brd_type = Integer.parseInt(fakebrd_type);
		
		List<Board> list = this.bdao.SelectListByType(brd_type);
		
		FlowParameters parameters 
		= new FlowParameters(pageNumber, pageSize, mode, keyword);
	
		// 파라미터 확인을 위한 출력
//		System.out.println(this.getClass() + " : " + parameters.toString());
			
		String contextpath = request.getContextPath() + "/" ;
		String myurl = contextpath +  "boardlist.bo" ;
			
		int totalCount 
		= bdao.SelectTotalCount(
				parameters.getMode(), 
				parameters.getKeyword());
		
			Paging pageInfo 
			= new Paging(
					parameters.getPageNumber(), 
					parameters.getPageSize(), 
					totalCount, 
					myurl, 
					parameters.getMode(), 
					parameters.getKeyword());
		
		List<Board> lists = bdao.SelectDataList(
				pageInfo.getOffset(),
				pageInfo.getLimit(),
				parameters.getMode(), 
				parameters.getKeyword()) ;
				// "%" 문자열은 like 연산자 때문에 넣었습니다.
		
		// 바인딩해야 할 목록들
		mav.addObject("lists", lists); // 게시물 목록
		
		// 페이징 관련 항목들
		mav.addObject("pagingHtml", pageInfo.getPagingHtml());
		mav.addObject("pagingStatus", pageInfo.getPagingStatus());
		
		//System.out.println(pageInfo.getPagingHtml());
		// 검색 필드의 상태 값 저장을 위한 항목들  
		mav.addObject("mode", parameters.getMode());
		mav.addObject("keyword", parameters.getKeyword());
		
		// 상세 보기, 수정, 삭제, 답글 등의 링크에 사용될 parameter list 문자열
		mav.addObject("parameters", parameters.toString());		
		
		this.mav.setViewName("boardList");
		
		return this.mav;
	}
	
	
	@RequestMapping(value = "test.bo", produces = "application/text; charset=UTF-8")
	public @ResponseBody String test(){
		String result = "ajax테스트";	
		
		return result;		
	}
	
}
