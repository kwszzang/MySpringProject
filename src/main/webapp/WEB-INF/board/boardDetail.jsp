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
		<div style="width: 60%;margin-left: 24%;margin-top: 5%; ">
			<c:forEach var = "brd" items = "${boardlist }">
			<input id = "brd_mid" type = "hidden" value = "${brd.mid }">
				<div style="line-height: 0.5;">
					<p style="font-weight: bold;color: #0b1b58;">${brd.brd_subject }</p>	
					<div>
						<div style="float: left;">
							${brd.name }(${brd.mid }) l ${brd.brd_inputdate }
						</div>
						<div style="float: left; margin-left: 60%;">
							조회수 : ${brd.brd_hitnum }&nbsp;&nbsp;
						</div>
						<div style="float: left; margin-left: 0%;">
						 	댓글수 : ${cnt }
						 </div>
					</div>
					<br>
					<hr>
				</div>
				<div style = "text-align: left; height:auto; overflow: visible;">
					<div style = "height: 60%; background-color: ;">
					<c:forEach var = "img" items="${boardFile }">
						<img src="<%=contextPath%>/upload/board/${img.file_name}" width="100%" height="100%">
					</c:forEach>
					</div>
					<hr>
					<div style = "">
						${brd.brd_content }
					</div>
					<div style = "height: 10%;">
					<hr>
					<c:forEach var = "file" items="${boardFile }">
						<span>첨부 파일 목록 </span>
						<p><a href = "#">${file.file_name }</a></p>
					</c:forEach>
					<hr>
					<div style="height: 10%;margin-left: 85%;">
						<button id = "boardModify" style="width: 80px;height: 30px; cursor: pointer;">수정</button>
						<button id = "boardDelete" style="width: 80px;height: 30px; cursor: pointer;">삭제</button>
					</div>
					<div style="margin-top: 3.5%;">
						<hr>
					</div>
					</div>
					<div style = "height: 30%;margin-left: 9%;margin-top: 5%;">
						댓글 입력<br>
						
						<input id = "loginId" type = "hidden" value= "${loginfo.mid }">
						<textarea id="comments" rows="12" cols="130"></textarea>
							<div>
								<button onclick="Write()" style="margin-left: 81.5%;width: 9%;height: 16%;background-color: #cecedc;
								border-radius: 9px;font-size: medium;margin-top: 3.5px;font: caption;font-weight: 600; cursor: pointer;">
								 댓글 작성
								</button>
							</div>
					</div>
					<div style = "height: 30%;">
					<hr>
					<br>
						<div id = "commenttest">
						<br>
						</div>
						<c:forEach var = "comt" items="${commentlists }">
							
							<div style="width: 100%; height: auto; overflow: visible;">
								<!-- 새로운 댓글 쓰기 전용 div -->
								<table style="table-layout:fixed; white-space: normal; ">
									<tr class = "comt_tr">
										<td style="border-left: 40px solid white;"><input class = "comt_mid" type = "hidden" value = "${comt.mid }">${comt.mid }</td>
										<td style="border-left: 100px solid white;width: 35%;">${comt.comt_content }</td>
										<td style="border-left: 480px solid white; font-size: 13px;width: 65%;">${comt.comt_inputdate }</td>
									</tr>
								</table>
							</div>
							<br>
						</c:forEach>
						<%-- <c:forEach var = "host" items="hostcommentlists">
							${host.comt_content }<br>
						</c:forEach> --%>
					</div>
				</div>
				<!-- 수정하기 누르면 나올 창  -->
				<div id = "modify_div" style="margin-left: 5%;margin-top: -119%; display: none;">
					<form action="<%=contextPath%>/boardmodify.bo" method="post">
						<input id = "brd_type" type = "hidden" value = "${brd.brd_type }" name = "brd_type">
						<input id = "seq_Brd" type = "hidden" value = "${brd.seq_brd }" name = "seq_brd">
						<div >
							<input  type = "text" name = "Nbrd_subject" style = "width: 30%; height: 4%;" placeholder = "제목을 입력해주세요" value = "${brd.brd_subject }"/><br>
							<textarea  rows="40" cols="130" name = "Nbrd_content">${brd.brd_content }</textarea>
						</div>
						<div style="margin-left: 33%;margin-top: 1%;">
							<button id = "cancel_btn" type = "button" style="cursor: pointer;width: 80px;height: 40px;">취소</button>
							
							<input  type = "submit" value = "수정하기" style="width: 80px;height: 40px;">
						</div>
					</form>
				</div>		
			</c:forEach>
		</div>
			<!-- 배경 회색 만들어줄 애  -->
  		<!-- <div id="backgound_black" style="width:100%; height:241%; z-index:10; background-color:#000; opacity: 0.5; margin-top: -95%; display: none;  "></div> -->
		
</body>
<script type="text/javascript">
	$('#cancel_btn').click(function() {
		//$('#backgound_black').hide();
		$('#modify_div').hide();
	});


	$('#boardModify').click(function() {
		//$('#backgound_black').show();
		$('#modify_div').show();
	});




	$('#boardDelete').click(function() {
		var seq_brd = parseInt($('#seq_Brd').val());
		var brd_type = parseInt($('#brd_type').val());
		
		console.log(brd_type);
		
		console.log(seq_brd);
		console.log(typeof(seq_brd));
		if(confirm('게시글을 정말 삭제 하시겠습니까?')){
			location.href = 'boardDelete.bo?seq_brd='+seq_brd+'&brd_type='+brd_type;	
		}
		
		
	});



	$( document ).ready(function() {
		var brd_mid = $('#brd_mid').val();
		var comt_mid = $('.comt_mid').val();
		var comt_tr = document.getElementsByClassName('comt_tr');
		
		
			for (var i = 0; i < comt_tr.length; i++) {
				console.log(comt_mid+"  몇 번 찍히나 테스트");
				if(brd_mid == comt_mid){
					$('.comt_tr').css('font-weight','900');
					
				}
				
			}
			
		
		});

	function Write() {
		var brd_mid = $('#brd_mid').val();
		var mid = $('#loginId').val();
		var fakeseq_brd = $('#seq_Brd').val();
		var comments = $('#comments').val();
		
		
		console.log(mid);
		console.log('글 번호 타입 확인 '+typeof(fakeseq_brd));
		console.log('댓글수 타입 확인 '+typeof(comments));
		console.log(comments);
		if(mid == ""){
			alert('로그인부터 해주세요.');
			location.href='login.do';
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
						if(brd_mid == mid){
							result +=   "<br>"
								result +=	"<table style='table-layout:fixed; white-space: normal; '>";
								result +=	"<tr style= 'font-weight: 900;'>"
								result +=	"<td style='border-left: 40px solid white;'>"+data[i].mid+"</td>";
								result +=	"<td style='border-left: 100px solid white;width: 35%;'>"+data[i].comt_content+"</td>";
								result +=   "<td style='border-left: 480px solid white; font-size: 13px;width: 65%;'>"+data[i].comt_inputdate+"</td>";
								result +=	"</tr>";
								result +=   "</table>";
								result +=   "<br>";
						}else{
								result +=   "<br>"
								result +=	"<table style='table-layout:fixed; white-space: normal; '>";
								result +=	"<tr>"
								result +=	"<td style='border-left: 40px solid white;'>"+data[i].mid+"</td>";
								result +=	"<td style='border-left: 100px solid white;width: 35%;'>"+data[i].comt_content+"</td>";
								result +=   "<td style='border-left: 480px solid white; font-size: 13px;width: 65%;'>"+data[i].comt_inputdate+"</td>";
								result +=	"</tr>";
								result +=   "</table>";
								result +=   "<br>";
						}
							
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