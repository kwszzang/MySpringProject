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
	
	<input type = "hidden" value = "${alm_type }" id = "alm_type">
	
	<div>
		<div style="margin-left: 41%;">
			<div style="font-size: 24px; font-weight: bold;">
				<span>원하는 앨범명을 검색해보세요</span>
			</div>
			<div style=" margin-top: 6%;">
				<input id = "search" type = "text" name = "search" style="width: 300px;height: 40px;border-radius: 10px; outline: none;"><br><br><br>
			</div>
			<div style="margin-left: 7.5%;">
				<button id = "search_btn" style="cursor: pointer; width: 120px; height: 50px;">눌르셈</button>
			</div>
			<br><br>
			<div id = "result">
				값이 나올 곳 
			</div>
		</div>	
	</div>

</body>
<script type="text/javascript">
	$('#search_btn').click(function() {
		
		var keyword = $('#search').val();
		var alm_type = $('#alm_type').val();
		
		
		$.ajax({
	            async: true,
	            type : 'POST',
	            data :JSON.stringify  ({ "keyword" : keyword,
	            						 "alm_type" : alm_type 
	            		 
	            	    }),
	            url : "searchalbum.al",
	            dataType : "json",
	            contentType: "application/json; charset=UTF-8",
	            success : function(data) {
					alert('성공');
					var result = '';
					for(var i in data){
						result += data[i].alm_name;
						console.log(data[i].alm_name);
					}
					if(data.length == 0){
						result += '검색 결과가 없습니다.';
					}
					
					$('#result').html(result);
	            },
	            error : function(error) {
	                console.log(error);
	                alert("error : " + error);
	            }
       		 }); 
	});
</script>
</html>