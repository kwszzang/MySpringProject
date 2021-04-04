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
	 	height: 45px;
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
	
	<div style="height: auto;">
		<div style="width: 48%;margin-left: 27.5%;margin-bottom: 3%;">
			<span style="">${boardName }</span>
			<br><br>
			<hr>
		</div>
		<div>
			<input id = "brd_type" type = "hidden" value = "${brd_type }">
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
					<td colspan="1">
						<button id = "search_btn" style="width: 50px;height: 30px; cursor: pointer;margin-bottom: 9%;">
							검색
						</button>
					</td>
					<td colspan="5" style="text-align: right;padding-right: 2%;" >
						<button style="width: 90px;height: 40px; cursor: pointer;" 
						onclick="writeboard()">글쓰기</button>
					</td>
					
				</tr>
		      </tbody>
	    	</table>
		</div>
	</div>	
	<div style="float: left;margin-left: 42%; margin-top: 20%;">
    	<!-- 페이징 처리할 곳 -->
    	<div>
              <c:if test="${pagination.curRange ne 1 }">
                  <a href="#" onClick="fn_paging(1)">[처음]</a> 
              </c:if>
              <c:if test="${pagination.curPage ne 1}">
                  <a href="#" onClick="fn_paging('${pagination.prevPage }')">[이전]</a> 
              </c:if>
              <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
                  <c:choose>
                      <c:when test="${pageNum eq  pagination.curPage}">
                          <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
                      </c:when>
                      <c:otherwise>
                          <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
                      </c:otherwise>
                  </c:choose>
              </c:forEach>
              <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
                  <a href="#" onClick="fn_paging('${pagination.nextPage }')">[다음]</a> 
              </c:if>
              <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
                  <a href="#" onClick="fn_paging('${pagination.pageCnt }')">[끝]</a> 
              </c:if>
          </div>
                
          <div>
              총 게시글 수 : ${pagination.listCnt } /    총 페이지 수 : ${pagination.pageCnt } / 현재 페이지 : ${pagination.curPage } / 현재 블럭 : ${pagination.curRange } / 총 블럭 수 : ${pagination.rangeCnt }
          </div>

   	</div>
   	
   	<!-- 검색 누르면 나올 창 -->
   	
   	<div id = "search_div" style="width: 20%;height: 29%;position: absolute; background-color: white; left: 39%;z-index: 20;top: 87%; display: none;">
   		<div style="width: 100%;  height: 19%;">
	   		<div style="float: left;font-size: 15px;font-weight: bold;line-height: 2rem;margin-left: 3%;">
	   			검색
	   		</div>
	   		<div style="float: left; margin-left: 83%; margin-top: 1%;">
	   			<button id = "close_btn" style="cursor: pointer;">x</button>
	   		</div>
   		</div>
   		<div style="width: 100%;  height: 81%; ">
   			<form action="<%=contextPath%>/search.bo" method="post">
   				<input  name = "brd_type" type = "hidden" value = "${brd_type }">
	   			<select name = "mode" style="margin-left: 5%;width: 90%;margin-top: 5%;height: 27%;font-size: 22px;font-weight: 500;padding-left: 3%;">
	   				<option value = "1">제목</option>
	   				<option value = "2">작성자</option>
	   			</select>
	   			<div style="margin-top: 5%;margin-left: 5%;">
	   				<input name = "keyword" type = "text" placeholder="검색어를 입력해주세요." style="width: 95%; height: 27%;padding-left: 4%;font-size: 22px;font-weight: 500;">
	   			</div>
	   			<div>
	   				<input type = "submit" value = "검색하기" style="margin-top: 4%;width: 29%;height: 15%;margin-left: 34%;">
	   			</div>
   			</form>
   		</div>
   	</div>
   	
   	<!-- 배경 회색 만들어줄 애  -->
   	<div id="backgound_black" style="width:100%; height:135%; z-index:10; background-color:#000; opacity: 0.5; margin-top: -59.4%; display: none; "></div>
   	
   	
</body>
</html>
<script type="text/javascript">
	function writeboard() {
		var brd_type = parseInt($('#brd_type').val());
		console.log(brd_type);
		location.href = "writeboard.bo?brd_type="+brd_type;
	}
	
	$('#search_btn').click(function() {
		$('#search_div').show();
		$('#backgound_black').show();
	});
	
	$('#close_btn').click(function() {
		$('#search_div').hide();
		$('#backgound_black').hide();
	});
	
	function fn_paging(curPage) {
		var brd_type = parseInt($('#brd_type').val());
		console.log(brd_type);
		location.href = "boardlist.bo?curPage=" + curPage+"&brd_type="+brd_type;
		
		}
</script>