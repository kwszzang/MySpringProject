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
	input{
		border: 1px solid #d6d6d6;
		border-radius: 9px 9px 9px 9px;
		width: 400px;
		height: 50px;
		padding-left: 15px;
	}
</style>
</head>
<body >
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
			</div>
		</div>
	</div>
	<div style="text-align: center; width: 100%;height: auto; ">
		<a href = "<%=contextPath%>/main.co" style="text-decoration: none;">
			<h1 style="font-size: 35px;letter-spacing: 0.2px;line-height: 1.5;color: black;">MySpringProject</h1>
		</a>
	</div>
	
	<div style="margin-left: 40%;margin-top: 4%;">
		<h2 style="text-align: left;margin-left: 26%;margin-top: 4%;font-size: 35px;">로그인하십시오.</h2>
		<form name = "form" action="<%=contextPath %>/login.do" method="post">
			<div style="float:left;margin-right: -18%;margin-left: -33%; background-color: yellow; width: 40%; height: 33%;">
				<img alt="" src="">
				<h2>이미지 자리</h2>
			</div>
				<div style="float:left; margin-left: 26.2%;">
					<p>
						<input type="text" name = "id" placeholder="ID"><br><br>
						<input type="password" name = "password" placeholder="암호">
					</p>
					
					<h5 >ID는 MySpringProject에 로그인할 때 사용하는 ID입니다.</h5><br><br>
					<input id = "login" type = "submit" value = "로그인" style="cursor: pointer;"><br><br>
					<a href ="#" style="font-weight: 600;">ID또는 암호를 잊으셨습니까?</a><br><br>
					<a href ="<%=contextPath %>/signup.do" style="font-weight: 600;">ID가 없으신가요? 지금 생성.</a>
				</div>
			</form>
		</div>
		
		
</body>
<script type="text/javascript">
/* var message = "";

$(function () {
	message = $('#message').val();
	
});

$('#login').click(function () {
	if(message != ""){
		alert(message);
	}
}); */
</script>
</html>