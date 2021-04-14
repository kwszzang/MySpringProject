<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="./../common/common.jsp" %>   
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
	
	<!-- 메뉴  -->
	<div style="margin-left: 38%; margin-top: 4%;">
		<div style="float:left; margin-left:5%: ">
			<a href = "#" onclick="slidedown('commuity');">커뮤니티</a>
		</div>
		<div style="float:left; margin-left:5%">
			<a href = "#" onclick="slidedown('song');">노래 찾기</a>
		</div>
		<div style="float:left; margin-left:5%">
			<a href = "#" onclick="slidedown('album');">앨범 찾기</a>
		</div>
		<div style="float:left; margin-left:5%">
			<a href = "#" onclick="slidedown('video');">동영상</a>
		</div>
		<div style="float:left; margin-left:5%">
			<a href = "#" onclick="slidedown('search');">검색</a>
		</div>
	</div>
	
		<!-- 커뮤니티 메뉴  -->
		<div id="commuity_menu" style="position: absolute;top: 38.9%;left: 35.5%;z-index: 100;width: 150px;background-color: grey;height: 90px;border-radius: 0px 0px 5px 5px;display: none; ">
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "<%=contextPath%>/boardlist.bo?brd_type=1">국내 게시판</a><br>
				</div>
			</div>
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "<%=contextPath%>/boardlist.bo?brd_type=2">해외 게시판</a><br>
				</div>
			</div>
		</div>
		
		<!-- 노래 메뉴  -->
		<div id="song_menu" style="position: absolute;top: 38.5%;left: 40.5%;z-index: 100;width: 240px;background-color: grey;height: 200px;border-radius: 0px 0px 5px 5px;display: none; ">
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "#">노래 메뉴</a><br>
				</div>
			</div>
		</div>
		
		<!-- 앨범 메뉴  -->
		<div id="album_menu" style="position: absolute;top: 38.9%;left: 46.5%;z-index: 100;width: 240px;background-color: grey;height: 200px;border-radius: 0px 0px 5px 5px;display: none; ">
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "<%=contextPath%>/albumList.al?alm_type=1">국내 앨범</a><br>
				</div>
			</div>
			
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "<%=contextPath%>/albumList.al?alm_type=2">해외 앨범</a><br>
				</div>
			</div>
		</div>
		
		<!-- 동영상 메뉴  -->
		<div id="video_menu" style="position: absolute;top: 38.9%;left: 52.5%;z-index: 100;width: 240px;background-color: grey;height: 200px;border-radius: 0px 0px 5px 5px;display: none; ">
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "#">동영상 메뉴</a><br>
				</div>
			</div>
		</div>
		
		<!-- 검색 메뉴  -->
		<div id="search_menu" style="position: absolute;top: 38.9%;left: 58.5%;z-index: 100;width: 240px;background-color: grey;height: 200px;border-radius: 0px 0px 5px 5px;display: none; ">
			<div style="height:33px; border-bottom: 1px solid #dadada; width: 85%; margin: auto; cursor:pointer;"> 
				<div style="float:left;margin-top: 5px;margin-left: 5px;color: #06c;font-size: 0.9em;">
					<a href = "#">검색 메뉴</a><br>
				</div>
			</div>
		</div>
		
		
		<div class="swiper-container" style="float:left; width:100%; height:892px; background-color: white; margin-top: 5%; margin-left: 12%;">
			<div class="swiper-wrapper">
				<div class="swiper-slide" style="width:100%; height:892px;">
					 <video src="<%=contextPath %>/resources/img/videoplayback.mp4" autoplay muted loop width="80%;"></video> 
				</div>
				
				<div class="swiper-slide" style="width:100%; height:892px;">
					<video src="<%=contextPath %>/resources/img/videoplayback2.mp4" autoplay muted loop width="80%;"></video>
				</div>
				
		    </div>
	    </div>
	    
	    <script>
			var swiper = new Swiper('.swiper-container', {
				autoplay: {
					delay: 25000,
					disableOnInteraction: false,
				}
			});
		</script>
		<div style="float:left; width:100%; height:143px; background-color:#F2F2F2; text-align: center;">
		
		<p style="font-weight: bold; color: #afafaf;">Spring Project</p>
		<p style="color: #868686; font-weight: bold;">Created by Kim Wonsik</p>
	</div>
	
	
</body>

<script type="text/javascript">

var slide_state =0;

function slidedown(value) {
	if(slide_state == 0){
        $('#'+value+'_menu').slideDown(300);
        slide_state = 1;
    }
    else{
        $('#'+value+'_menu').slideUp(300);
        slide_state = 0;
    }
}


/* $('#ajaxtest').click(function() {
	$.ajax({
		url : "test.bo",
		success : function(data){
			alert(data.msg);
			console.log(data);
			console.log('데이터 : '+data.msg);
		},
		error : function(error){
			console.log('에러 : '+error);
			alert(error);
		}
	}); 
}); */
</script>
</html>