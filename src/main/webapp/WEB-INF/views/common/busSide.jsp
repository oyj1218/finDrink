<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>busSide</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/myside-bus.css" />


    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        
        <main>
            <section class="section1 rellax" data-rellax-speed="-15">
                <img id="section1-logo" class="section1-logo" src="${contextPath}/resources/images/findrink.png" alt="">

                <!-- 프로필 설정 X -->
                <c:if test="${empty loginMember.memberProfileImage}">
                    <img id="section1-profile" class="section1-profile" 
                src="${contextPath}/resources/images/user.png" alt="">
                </c:if>

                <!-- 프로필 설정 O -->
                <c:if test="${!empty loginMember.memberProfileImage}">
                    <img id="section1-profile" class="section1-profile" 
                    src="${contextPath}${loginMember.memberProfileImage}">
                </c:if>
                <ul>
                    <li><a href="${contextPath}/mypage/businessInfo">내 정보</a></li>
                    <li><a href="${contextPath}/mypage/businessReview?memberNo=${loginMember.memberNo}&cp=1">리뷰 조회</a></li>
                    <li><a href="${contextPath}/mypage/businessStore?memberNo=${loginMember.memberNo}">매장 관리</a></li>
                    <li><a href="${contextPath}/member/logout" id="logout-btn">로그아웃</a></li>
                </ul>
            </section>
            <section class="section2 rellax" data-rellax-speed="1">
                <h1 class="section2-h1-bussiness">사업자 ${loginMember.memberName}님, 반갑습니다</h1>
                
            </section>
        </main>
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>


</body>
</html>