<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login-pg</title>
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
    
    <link rel="stylesheet" href="${contextPath}/resources/css/login-signup/login-pg.css">
   
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    

</head>

<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
            <div class="main-1"></div>
            <div class="main-2"></div>
            <div class="main-3">
                <img src="${contextPath}/resources/images/findrink.png" alt="">
                <p class="main-3-p">FINDRINK를 방문해주셔서 감사합니다</p>

				<form action="${contextPath}/member/login" onsubmit="return loginValidate()">
	                <input type="text" class="main-input" name="id" id="id" placeholder="아이디 | ID" value="${cookie.saveId.value}">
	                <div class="password-area1">
	                	<input type="password" class="main-input" name="password" id="password" placeholder="비밀번호 | PASSWORD">
	                	<i class="fa fa-eye-slash fa-lg"></i>
	                </div>
	                <div class="main-4">
                        <a href="javascript:kakaoLogin();"><img src="${contextPath}/resources/images/kakao_login.png" alt=""></a>
	                    
                        <c:if test="${!empty cookie.saveId.value}">
                            <c:set var="chk" value="checked"></c:set>
                        </c:if>
                        <input type="checkbox" name="saveId" ${chk} id="saveId">&nbsp&nbsp 아이디 저장
	                    <button class="login-btn" >login</a>
	                </div>				
				</form>
				
            </div>

            <div class="main-right rellax">
                <p>아직 회원이 아니신가요?</p>
                <a href="${contextPath}/member/signUpPage" class="signup-btn">sign up</a>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>
    
    <script src="http://developers.kakao.com/sdk/js/kakao.js"></script>
    <script src="${contextPath}/resources/js/login-signup/login.js"></script>
    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>
</body>

</html>