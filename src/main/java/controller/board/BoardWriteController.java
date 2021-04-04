package controller.board;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
import dao.BoardDao;

@Controller
public class BoardWriteController {
	
	private String baseDir = "c:" + File.separator + "upload"+File.separator;
	
	@Autowired
	@Qualifier("bdao")
	BoardDao bdao;
	
	
	@ModelAttribute("board")
	public Board board() {
		return new Board() ;
	}
	
	private ModelAndView mav = null;
	
	public BoardWriteController() {
		this.mav = new ModelAndView();
	}
	
	@GetMapping(value = "writeboard.bo")
	public ModelAndView doGet(
			@RequestParam(value = "brd_type")int brd_type,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		Member member = (Member)session.getAttribute("loginfo");
		if(member == null){
			out.println("<script>alert('로그인 먼저 해주세요.');location.href = 'login.do';</script>");
			out.flush();
			out.close();
			
		}
		
		this.mav.addObject("brd_type",brd_type);
		this.mav.setViewName("board/boardWrite");
		
		return this.mav;
	}
	
	@PostMapping(value = "writeboard.bo")
	public ModelAndView doPost(
			@ModelAttribute("board") @Valid Board board,
			@RequestParam(value = "file_name") MultipartFile[] files,
			HttpSession session,
			BindingResult error,
			HttpServletResponse response,
			@RequestParam(value = "brd_type")int brd_type
			) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		Member member = (Member)session.getAttribute("loginfo");
		String mid = member.getMid();
		
		
		board.setMid(mid);
		board.setBrd_type(brd_type);
			
			//에러가 있다고 안 뜸 현재 ...ㅠㅠ 
			//유효성 검사하면 400 에러 
			if(error.hasErrors()) {
				out.write("<script>alert('유효성 검사 문제');history.go(-2);</script>");
				out.flush();
				out.close();
				
				
			}else {
				System.out.println("아이디 : "+board.getMid());
				System.out.println("게시판 타입 : "+ board.getBrd_type());
				System.out.println("게시판 제목 : "+ board.getBrd_subject());
				System.out.println("게시판 내용 : "+ board.getBrd_content());
				int cnt = -9999;
				cnt = this.bdao.WriteBoard(board);
				
				//방금 넣은 seq_brd 값 구하기
				int seq_brd = this.bdao.SelectcurrVal();
				
				System.out.println("file 테이블에 insert 할 seq : "+seq_brd);
				if (files != null && files.length > 0) {
					String formattedDate = baseDir+ new SimpleDateFormat("yyyy" + File.separator + "MM"+File.separator+"dd" ).format(new Date());
					File f = new File(formattedDate);
					if (!f.exists()) { 
						f.mkdirs(); 
					}

					for (MultipartFile file : files) {
						String type = file.getContentType();
						String file1 = file.getOriginalFilename(); 
						
						long imsi = file.getSize();
						int size = Long.valueOf(imsi).intValue();

						String saveFileName = formattedDate + File.separator + file1; 

						System.out.println("type :" + type);
						System.out.println("originalFilename : " + file1);
						System.out.println("size : " + size);
						System.out.println("saveFileName : " + saveFileName);
						
						Map<String, Object> fileMap = new HashMap<String, Object>();
						fileMap.put("file_name", file1);
						fileMap.put("file_size", size);
						fileMap.put("file_location", saveFileName);
						fileMap.put("seq_brd", seq_brd);
						
						cnt = this.bdao.insertFile(fileMap);	
						
						
						try (InputStream in = file.getInputStream();
							FileOutputStream fos = new FileOutputStream(saveFileName)) {
							int readCount = 0;
							byte[] buffer = new byte[512];
							while ((readCount = in.read(buffer)) != -1) {
								
								fos.write(buffer, 0, readCount);
							}

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} else {
					System.out.println("파일 첨부 안됨");

				}
				
			}
			this.mav.setViewName("redirect:/boardlist.bo");
			return this.mav;
	}
}
