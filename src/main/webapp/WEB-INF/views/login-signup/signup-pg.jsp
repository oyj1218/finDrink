<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>signup-pg</title>
    
	<script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${contextPath}/resources/css/login-signup/signup-pg.css">
    
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script> -->
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
                <form action="${contextPath}/member/signUp" method="post" onsubmit="return signUpValidate()">
	                <input type="text" class="main-input" name="id" id="id" placeholder="아이디 | ID">
                    <span id="idMessage" class="validate">아이디를 입력해주세요.(영어/숫자 2~10글자 사이)</span>
	                <div class="password-area1">
	                	<input type="password" class="main-input" name="password" id="password" placeholder="비밀번호 | PASSWORD">
	                	<i class="fa fa-eye-slash fa-lg"></i>
	                </div>
	                <div class="password-area2">
                    	<input type="password" class="main-input" name="passwordConfirm" id="passwordConfirm" placeholder="비밀번호 확인 | PASSWORD CONFIRM">
	                	<i class="fa fa-eye-slash fa-lg"></i>
	                </div>
                    <span id="passwordMessage" class="validate">영어, 숫자, 특수문자(!,@,#,-,_) 8~30글자 사이로 작성해주세요</span>
	                <input type="text" class="main-input" name="name" id="name" placeholder="이름 | NAME">
	                <input type="text" class="main-input" name="phone" id="phone" placeholder="전화번호 | PHONE">
	                <input type="text" class="main-input" name="nickname" id="nickname" placeholder="닉네임 | NICKNAME">
                    <span id="nicknameMessage" class="validate">닉네임을 입력해주세요.(영어/숫자/한글 2~10글자 사이)</span>
	                <input type="text" class="main-input" name="email" id="email" placeholder="이메일 | EMAIL">
	                <input type="text" class="main-input" name="intro" id="intro" placeholder="한 줄 소개 | INTRO">
	                <div class="main-4">
	                    <div>
                            <label>
                                <input type="radio" name="member" id="" value="user"> 
                                <span>사용자</span>
                            </label>
	                    </div>
	                    <div>
                            <label>
                                <input type="radio" name="member" id="" value="business">
                                <span>사업자</span>
                            </label>
	                    </div>
	                </div>
	                <div class="main-5">
	                    <button class="signup-btn">sign up</button>
	                </div>
                </form>
            </div>
            

        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>
    <script src="${contextPath}/resources/js/login-signup/signUp.js"></script>
</body>

</html>