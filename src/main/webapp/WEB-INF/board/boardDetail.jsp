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
					<p style="font-weight: bold;color: #0b1b58;">${brd.brd_subject }</p>	
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
					<div style = "height: 30%;">
					<br>
						<div id = "commenttest">
						<br>
						</div>
						<c:forEach var = "comt" items="${commentlists }">
							<div style="width: 100%; height: auto; overflow: visible;">
								<!-- 새로운 댓글 쓰기 전용 div -->
								<table style="table-layout:fixed; white-space: normal; ">
									<tr>
										<td style="border-left: 40px solid white;">${comt.mid }</td>
										<td style="border-left: 100px solid white;width: 35%;">${comt.comt_content }</td>
										<td style="border-left: 480px solid white; font-size: 13px;width: 65%;">${comt.comt_inputdate }</td>
									</tr>
								</table>
							</div>
							<br>
						</c:forEach>
					</div>
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
		console.log('댓글수 타입 확인 '+typeof(comments));
		console.log(comments);
		if(mid == ""){
			alert('로그인부터 해주세요.');
		}else{
	 		 $.ajax({
		            async: true,
		            type : 'POST',
		            data :JSON.stringify  ({ "mid" : mid,
		            		 "fakeseq_brd" : fakeseq_brd,
		            		 "comments" : comments
		            	    }),
		            url : "writecomments.bo",
		            dataType : "json",
		            contentType: "application/json; charset=UTF-8",
		            success : function(data) {
						alert('성공');
						
						
						var result = '';
						for(var i in data) {
						result +=	"<table style='table-layout:fixed; white-space: normal; '>";
						result +=	"<tr>"
						result +=	"<td style='border-left: 40px solid white;'>"+data[i].mid+"</td>";
						result +=	"<td style='border-left: 100px solid white;width: 35%;'>"+data[i].comt_content+"</td>";
						result +=   "<td style='border-left: 480px solid white; font-size: 13px;width: 65%;'>"+data[i].comt_inputdate+"</td>";
						result +=	"</tr>";
						result +=   "</table>";
						result += "<br>";
							
							
						}
						
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