<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a{
		cursor: pointer;
		text-decoration: none;
		color: #5E5E5E;
		font-weight: 600;
		font-size: 15px;
	 }
	 tr{
	 	border: none;
	 }
</style>
</head>
<body>
	<div style="background-color: #5801FF; height: 120px; padding-top: 3%;">
		<div style="text-align: center;font-family: Myriad;font-size: 35px;font-weight: 700;letter-spacing: 0.2px;line-height: 1.5;color: yellow;">
			<span>이벤트 문구</span><br>
		</div>
	</div>
	<div>
		<div style="float: right; width: 100%; height: auto;">
			<div style="margin-left: 93%;">
				<c:if test="${whologin == 0}">
					<a style="text-decoration: none;" href = "<%=contextPath%>/login.do">login</a>
					<a href = "#" style="margin-left:10%;text-decoration: none;">menu</a>
				</c:if>
				
				<c:if test="${whologin == 1}">
					<span>${loginfo.name }님</span>
					<a href = "<%=contextPath %>/logout.do" style="margin-left:10%;text-decoration: none;">임시 로그아웃</a>
				</c:if>	
				
				<c:if test="${whologin == 2}">
					<span>${kakaoname }님 (카카오 로그인)</span>
					<a href = "<%=contextPath %>/logout.do" style="margin-left:10%;text-decoration: none;">임시 로그아웃</a>
				</c:if>	
			</div>
		</div>
	</div>
	<div style="text-align: center; width: 100%;height: auto; ">
		<a href = "<%=contextPath%>/main.co" style="text-decoration: none;">
			<h1 style="font-size: 35px;letter-spacing: 0.2px;line-height: 1.5;color: black;">MySpringProject</h1>
		</a>
	</div>
	
	<div style="">
		<div style="width: 48%;margin-left: 27.5%;margin-bottom: 3%;">
			<span style="">국내 게시판</span>
			<br><br>
			<hr>
		</div>
		<div>
			<input id = "brdType" type = "hidden" value = "${brd_type }">
			<table style="width: 49%;margin-left: 27%;margin-bottom: -20%;text-align: center;height: 80%;">
	      <thead>
	        <tr>
	          <th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th>
	        </tr>
	      </thead>
		      <tbody>
		       <c:forEach var = "brd" items="${lists }">
				   <tr>
					   <td style="width: 5%;">${brd.seq_brd }</td>
					   <td style="width: 60%;"><a href = "<%=contextPath%>/detailview.bo?seq_brd=${brd.seq_brd}&brd_type=${brd.brd_type}">${brd.brd_subject }</a></td>
				       <td style="width: 10%;">${brd.mid }</td>
					   <td style="width: 10%;">${brd.brd_inputdate }</td>
					   <td style="width: 5%;">${brd.brd_hitnum }</td>
					<tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right;padding-right: 2%;" >
						<button style="width: 90px;height: 40px; cursor: pointer;" 
						onclick="writeboard()">글쓰기</button>
					</td>
				</tr>
		      </tbody>
	    	</table>
		</div>
	</div>	
	
</body>

<script type="text/javascript">
	function writeboard() {
		var brd_type = parseInt($('#brdType').val());
		console.log(brd_type);
		location.href = "writeboard.bo?brd_type="+brd_type;
	}
</script>
</html>