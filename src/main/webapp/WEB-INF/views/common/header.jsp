<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/common/header-footer.css">
</head>
<body>
    <header>
            
        <!-- 클릭 시 메인페이지로 이동하는 로고 -->
        <section>
            <a href="${contextPath}" class="header-left">
                <img src="${contextPath}/resources/images/findrink2.png" id="home-logo">
            </a>
    
            <ul class="header-middle">
                <li><a href="${contextPath}/review/list?type=1&cp=1">리뷰</a></li>
                <li><a href="${contextPath}/board/list?type=3">커뮤니티</a></li>
            </ul>
            <div class="header-right">
                <c:choose>
                    <%-- 로그인이 안 되어 있을 때 로그인, 회원가입 창으로 이동 --%>
                    <c:when test="${ empty sessionScope.loginMember }">
                        <fieldset id="id-pw-area">
                            <a href="${contextPath}/member/loginPage" class="login-pg">로그인</a>
                            <a href="${contextPath}/member/signUpPage" class="signup-pg">회원가입</a>
                        </fieldset>
                    </c:when>

                    <%-- 로그인이 되어 있는 경우 마이페이지, 로그아웃 보여주기 --%>
            		<c:otherwise>
                        <fieldset id="mypage-logout-area">
                            <a href="${contextPath}/mypage/${loginMember.memberCategory}" class="mypage-pg">마이페이지</a>
                            <a href="${contextPath}/member/logout" id="logout-btn" class="logout-btn">로그아웃</a>
                        </fieldset>
            		</c:otherwise>
                </c:choose>
            </div>
        </section>
    </header>

</body>
</html>

