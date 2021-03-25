<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file = "./../common/common.jsp" %>
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
<body style="overflow: auto;">
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
		<div style="width: 60%;margin-left: 24%;margin-top: 5%; ">
			<c:forEach var = "brd" items = "${boardlist }">
				<div style="line-height: 0.5;">
					<p style="font-weight: bold;color: #0b1b58;">(${brd.seq_brd})${brd.brd_subject }</p>	
					<div>
						<div style="float: left;">
							${brd.name }(${brd.mid }) l ${brd.brd_inputdate }
						</div>
						<div style="float: left; margin-left: 60%;">
							조회수 : ${brd.brd_hitnum }&nbsp;&nbsp;
						</div>
						<div style="float: left; margin-left: 1%;">
						 	댓글수 : ${cnt } 
						 </div>
					</div>
					<br>
					<hr>
				</div>
				<div style = "text-align: left; height:auto; overflow: visible;">
					<div style = "height: 60%; background-color: red;">
					<br>
						파일(이미지) 자리
					</div>
					<div style = "background-color: yellow;">
					<br>
						${brd.brd_content }
					</div>
					<div style = "height: 10%;background-color: green;">
					<br>
						첨부파일자리
					</div>
					<div style = "height: 30%;margin-left: 9%;">
						댓글 입력<br>
						<input id = "seq_Brd" type = "text" value = "${brd.seq_brd }">
						<input id = "loginId" type = "text" value= "${loginfo.mid }">
						<textarea id="comments" rows="12" cols="130"></textarea>
							<div>
								<button onclick="Write()" style="margin-left: 81.5%;width: 9%;height: 16%;background-color: #cecedc;
								border-radius: 9px;font-size: medium;margin-top: 3.5px;font: caption;font-weight: 600; cursor: pointer;">
								 댓글 작성
								</button>
							</div>
					</div>
					<div style = "background-color: grey; height: 30%;">
					<br>
							<div id = "commenttest"></div>
						<c:forEach var = "comt" items="${commentlists }">
							<div id = "commenttest">
							<P>댓글 작성자 아이디 : ${comt.mid }</P>
							<P>댓글 작성자 이름 : ${comt.name }</P>
							<P>댓글 내용 : ${comt.comt_content }</P>
							<P>댓글 작성일자 : ${comt.comt_inputdate }</P>
							</div>
						</c:forEach>
					</div>
				</div>
				
				<div>
				</div>
			</c:forEach>
		</div>
		
</body>
<script type="text/javascript">
	function Write() {
		
		var mid = $('#loginId').val();
		var fakeseq_brd = $('#seq_Brd').val();
		var comments = $('#comments').val();
		
		console.log(mid);
		console.log('글 번호 타입 확인 '+typeof(fakeseq_brd));
		console.log(comments);
		
		if(mid == ""){
			alert('로그인부터 해주세요.');
		}else{
	 		 $.ajax({
		            async: true,
		            type : 'POST',
		            data :JSON.stringify  ({ "mid" : mid,
		            		 "fakeseq_brd" : fakeseq_brd
		            	    }),
		            url : "writecomments.bo",
		            dataType : "json",
		            contentType: "application/json; charset=UTF-8",
		            success : function(data) {
						alert('성공');
						var result = '';
						
						result+= '<h1>안녕하세요 이게 어디서 작성되는지 궁금합니다</h1>'
							$('#commenttest').html(result);
		            },
		            error : function(error) {
		                console.log(error);
		                alert("error : " + error);
		            }
		        }); 
			
		} 
		
	}
</script>
</html>