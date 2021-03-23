<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>게시판 상세 페이지 입니다 </h1>
		<p>댓글수 : ${cnt }</p>
		
		<h1>작성글 보기</h1>
		<c:forEach var = "brd" items = "${boardlist }">
			<P>글 번호 : ${brd.seq_brd }</P>
			<P>글 제목 : ${brd.brd_subject }</P>
			<P>글 내용 : ${brd.brd_content }</P>
			<P>글 조회수 : ${brd.brd_hitnum }</P>
			<P>글 작성일자 : ${brd.brd_inputdate }</P>
			<p>글 작성자 : ${brd.mid }</p>
			<p>글 작성자 이름 : ${brd.name }</p>
		</c:forEach>
		
		<c:forEach var = "comt" items="${commentlists }">
			<P>댓글 작성자 아이디 : ${comt.mid }</P>
			<P>댓글 작성자 이름 : ${comt.name }</P>
			<P>댓글 내용 : ${comt.comt_content }</P>
			<P>댓글 작성일자 : ${comt.comt_inputdate }</P>
		</c:forEach>
</body>
</html>