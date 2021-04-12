package controller.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
import dao.BoardDao;
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
			@RequestParam(value = "brd_type")int brd_type,
			@RequestParam(defaultValue="1") int curPage,
			HttpSession session
			) {
		
		
		String boardName = "";
		if(brd_type == 1) {
			boardName = "국내 게시판";
		}else {
			boardName = "해외 게시판";
		}
		
		this.mav.addObject("boardName",boardName);
		
		//로그인 한 사람 정보 
		List<Member> loginList = new ArrayList<Member>();
		loginList.add((Member) session.getAttribute("loginfo"));
		
		this.mav.addObject("loginList",loginList);
		
		
		this.mav.addObject("brd_type", brd_type);
		//페이징 처리
		int listCnt = this.bdao.SelectListCnt(brd_type);
		
		
		System.out.println("전체 갯수 : "+listCnt);
		
		Paging paging = new Paging(listCnt, curPage);
		
		this.mav.addObject("pagination", paging);
		
		String intStr = "1,2,5,9,X,3,7";
		
		System.out.println(intStr);
		StringBuffer sb = new StringBuffer(intStr);
		sb.delete(8, 10);
		System.out.println(sb);
		
		System.out.println("===================");
		
		String newStr = sb.toString();
		
		System.out.println(newStr);
		
		System.out.println("===============");
		
		String [] a = newStr.split(",");
		List<Object> aList = new ArrayList<Object>();
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
			aList.add(Integer.parseInt(a[i]) );
		}
		System.out.println();
		aList.sort(null);
		System.out.println(aList);
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < aList.size(); i++) {
			
			if(i == aList.size() - 1 ) {
				sb1.append(String.valueOf(aList.get(i)) );
			}else {
				sb1.append(String.valueOf(aList.get(i))+", ");
			}
		}
		System.out.println(sb1.toString());
		
		
		
		
		
		
		
		
		
		
		
		//작성한 글 출력 정보
		
		int startIndex = paging.getStartIndex();
        int endIndex =startIndex+10;
		List<Board> lists = this.bdao.SelectListByType(brd_type,startIndex,endIndex);
		
		this.mav.addObject("lists",lists);
		
		this.mav.addObject("listCnt", listCnt);
		
		this.mav.setViewName("/board/boardList");
		
		return this.mav;
	}
	
}	
