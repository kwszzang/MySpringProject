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
	input{
	font-size: 17px;
    line-height: 1.23543;
    font-weight: 400;
    letter-spacing: -.022em;
    font-family: SF Pro Text,SF Pro Icons,Helvetica Neue,Helvetica,Arial,sans-serif;
    display: inline-block;
    box-sizing: border-box;
    vertical-align: top;
    width: 460px;
    height:59px;
    margin-bottom: 14px;
    padding-top: 11px;
    padding-left: 16px;
    color: #333;
    text-align: left;
    border: 1px solid #d6d6d6;
    border-radius: 4px;
    background: hsla(0,0%,100%,.8);
    background-clip: padding-box;
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
			<div style="margin-left: 94%;">
					<a style="text-decoration: none;" href = "<%=contextPath%>/login.me">login</a>
					<a href = "#" style="margin-left:10%;text-decoration: none;">menu</a>
			</div>
		</div>
	</div>
	<div style="width: 100%;height: 130px; background-color: grey; text-align: center;margin-top: 1.5%;">
		<div style="padding-top: 1%;">
			<h1>회원 가입 </h1>
		</div>
	</div>
	<div style="text-align: center;font-size: 15;letter-spacing: -1px;line-height: 0.8; font-weight: 400;">
		<p>하나의 ID로 모든 MySpringProject를 이용할 수 있습니다.</p>
		<p>이미 ID를 가지고 계십니까?<a href = "#" style="color: #06c;font-size: 14;">찾아보기></a></p>
	</div>
	<div style="margin-top: 5%; text-align: center;">
		<form action="<%=contextPath %>/signin.me" method="post">
				<p style="margin-left: -20.5%;">계정 정보</p>
				<div style="margin-left: 6%;">
					<input type = "text" name = "id" placeholder="아이디" >
					<input type = "button" value = "중복 검사" style="width: 100;cursor: pointer; background-color:#9c9c9c; color: white;padding-bottom: 8px;">	
				</div>
					<br>
					
				<input type = "password" name = "password" placeholder="비밀번호">
					<br>
				<input type = "password" name = "passwordcheck" placeholder="비밀번호 확인"	>
					<br><br>	
					<hr style="width: 35%;">
				
				<p style="margin-left: -20.5%;">회원 정보</p>
			<input type = "text" name ="name" placeholder="이름">
				<br><br>
				
			<input type = "text" name ="age" placeholder="생일(YYYY-MM-DD)">
				<br><br>
				
			<input type="text" value = "010" disabled="disabled" style="width: 90; padding-left: 1.5%;">
			<input type = "text" placeholder="핸드폰 번호(-없이 숫자만)" name = "phone"style="width: 360;">
				<br><br>
			<div style="margin-left: 6.5%;">
				<input type = "text" placeholder="이메일" name = "email" id = "email">
				<input type = "button" value = "이메일 인증" style="width: 120;cursor: pointer; background-color:#9c9c9c; color: white;padding-bottom: 8px; " id = "email_btn">	
				<br><br>
			</div>
			
			<div style="margin-left: 7.5%;">
				<input type = "text" name = "postcode" placeholder="우편 번호" disabled="disabled" id = "postcode" >
				<input type = "button" value = "우편 번호 검색" style="width: 137;cursor: pointer; background-color:#9c9c9c; color: white;padding-bottom: 8px;"onclick="DaumPostcode()">	
				<br>
			</div>
			<input type="text" name = "address1" placeholder="도로명 주소" disabled="disabled" id = "address1">
				<br>
			<input type="text" name = "address2" placeholder="상세 주소" id = "address2">
				<br><br><br>
				<hr style="width: 35%;">
				<br>
			<input type = "submit" value = "가입하기" style="width: 150px;text-align: center;padding-right: 11px;padding-bottom: 7px;background-color:#9c9c9c; color: white;">	
				<br>
			<span id = guide></span>
			<div style="margin-top: 8%;">
			</div>
		</form>
	</div>
	
<%-- <script src="<%=contextPath %>/resources/signin/signin.js"></script> --%>
<!-- 카카오 지도.api 참고함 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("address1").value = extraAddr;
            
            } else {
                document.getElementById("postcode").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address1").value = addr+extraAddr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address2").focus();
            self.close();
        }
    }).open();
}


/* $("#email").keyup( function(){
	var reciveremail = $('#email').val();
}); */
$('#email_btn').click(function(){
	var str = confirm('입력하신 이메일로 인증 번호를 전송하겠습니까?');
	var reciveremail = $('#email').val();
	console.log(reciveremail);
    if (str == true) {
    	location.href = '${pageContext.request.contextPath}/emailcheck.me?reciveremail=' + reciveremail;
    } else {
        return false;
    }
	
});

</script>
</body>
</html>
