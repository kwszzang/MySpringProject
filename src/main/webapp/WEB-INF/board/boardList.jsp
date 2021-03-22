<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="./../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
		function writeForm(){
				location.href='<%=contextPath%>/insert.bo';
		}
		function search(){
			if( $('#mode').val() == 'all' ){
				alert('검색 목록을 선택해주세요') ;
				//$('#mode').focus();
			}else{
				//alert('하하') ;
			}
			//alert( $('#mode').val() );
		}
		function searchAll(){
			//$('#mode').val('-');
			//$('#keyword').val('');
			location.href='<%=contextPath%>/boardlist.bo';
		}
	</script>
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
					<a style="text-decoration: none;" href = "<%=contextPath%>/login.me">login</a>
					<a href = "#" style="margin-left:10%;text-decoration: none;">menu</a>
				</c:if>
				
				<c:if test="${whologin == 1}">
					<span>${loginfo.name }님</span>
					<a href = "<%=contextPath %>/logout.me" style="margin-left:10%;text-decoration: none;">임시 로그아웃</a>
				</c:if>	
			</div>
		</div>
	</div>
	<div style="text-align: center; width: 100%;height: auto; ">
		<a href = "<%=contextPath%>/main.co" style="text-decoration: none;">
			<h1 style="font-size: 35px;letter-spacing: 0.2px;line-height: 1.5;color: black;">MySpringProject</h1>
		</a>
	</div>
	
	<div style="width: 60%;margin-left: 24%;height: 100%;">
		<span>국내 게시판</span>
		<hr>
		<table border="1" style="width: 100%">
		<tr>
		<td colspan="10" align="center">
						<form class="form-inline" role="form" name="myform" action="<%=contextPath%>/boardlist.bo" method="post">
							<div class="form-group">
								<select class="form-control" name="mode" id="mode">
									<option value="all" selected="selected">-- 선택하세요---------
									<option value="mid" >작성자
									<option value="brd_subject" >제목									
									<option value="brd_ontent" >글 내용									
								</select>
							</div>
							<div class="form-group">
								<input type="text" class="form-control btn-xs" name="keyword"
									id="keyword" placeholder="검색 키워드">
							</div>
							<button class="btn btn-default btn-warning" type="submit" onclick="search();">검색</button>
							<button class="btn btn-default btn-warning" type="button" onclick="searchAll();">전체 검색</button>
							<button class="btn btn-default btn-info" type="button"
								onclick="writeForm();">글 쓰기</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<p class="form-control-static">${requestScope.pagingStatus}</p>
						</form>
					</td>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>글쓴이</td>
				<td>날짜</td>
			</tr>
			<c:forEach var = "brd" items="${lists }">
			<tr>
				<td>${brd.seq_brd }</td>
				<td>${brd.brd_subject }</td>
				<td>${brd.mid }</td>
				<td>${brd.brd_inputdate }</td>
				<td>${bean.remark}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div align="center">
			<footer>${pagingHtml}</footer>			
		</div>		
</body>
</html>