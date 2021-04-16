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
			<div style="margin-left: 90%;">
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
	<c:forEach var = "album" items="${album }">
		<div>
			<div style="height: 50px;background-color: #f3f3f3;padding-left: 16%;padding-top: 1%; font-weight: 900;font-size: 21px;">
				<div>${album.alm_name }</div>
			</div>
			
		</div>
		<div style="margin-left: 16%;">
			<div style="float: left;width: 30%;">
				<!--  300 x 299 이미지 규격  -->
				<img  src="<%=contextPath %>/resources/img/album/${album.alm_image}" width="300" height="299"> 
			</div>
			<div style="float: left;width: 70%;">
				<p>가수 : ${album.alm_songwriter }</p>
				<p>장르 : ${album.alm_gerne }</p>
				<p>발매년도 : ${album.alm_releasedate }</p>
				<hr>
				<p>수록곡 목록</p>
				<hr>
				<c:forEach var = "song" items="${songlist }">
					<p><a href = "#">${song.song_name } </a> </p>
				</c:forEach>
				<!-- 재생 시간 : ${song.song_mit } 분 ${song.song_sec } 초 -->
			</div>
		</div>
		<div>
			<hr>
			<div>
				<c:forEach var = "desc" items = "${desList }">
						<p>${desc.alde_content1 }</p>
						<p>${desc.alde_content2 }</p>
						<p>${desc.alde_content3 }</p>				
				</c:forEach>
			</div>
		</div>
		
		
		
	</c:forEach>
</body>
</html>