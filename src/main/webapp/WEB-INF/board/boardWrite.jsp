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
	.err{
    color: red;
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
	
	<form  action="<%=contextPath %>/writeboard.bo" method="POST" enctype="multipart/form-data">
		
		<div style="margin-left: 26%;margin-top: 5%;">
			<input  type = "text" name = "brd_subject" style = "width: 30%; height: 4%;" placeholder = "제목을 입력해주세요"/><br>
			<input type = "file" name = "file" multiple="multiple"/><br>
			<textarea  rows="40" cols="130" name = "brd_content"></textarea>
		</div>
		<div style="margin-left: 66.5%;margin-top: 1%;">
			<button id = "cancel_btn" type = "button" style="cursor: pointer;width: 80px;height: 40px;">취소</button>
			<input  type = "submit" value = "글쓰기" style="width: 80px;height: 40px;">
		</div>
		
		<input id = "brd_type" type = "hidden" value = "${brd_type }" name = "brd_type"> 
	</form>
</body>
<script type="text/javascript">

	$('#cancel_btn').click(function() {
		if(confirm('작성을 취소하고 이전 페이지로 이동하겠습니까?')){
			console.log('오류 왜 나요 갑자기');
			history.go(-1);
		}else{
			console.log('왜 오류뜨지;');
		}
	});
</script>
</html>