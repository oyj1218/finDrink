<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>businesscheck</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/mycheck-pg.css" />

    
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
            <jsp:include page="/WEB-INF/views/common/busSide.jsp"/>
            <form action="#">
                <div class="main-3">

                    <p class="main-input">${loginMember.memberId}</p>
                    <p class="main-input">${loginMember.memberName}</p>
                    <p class="main-input">${loginMember.memberTel}</p>
                    <p class="main-input">${loginMember.memberNickname}</p>
                    <p class="main-input">${loginMember.memberEmail}</p>
                    <p class="main-input">${loginMember.memberIntro}</p>
                  
                    <a href="${contextPath}/mypage/passwordCheckPage" class="update-btn">update</a>

                    <a href="${contextPath}/mypage/secessionPage" class="secession-btn">회원탈퇴</a>
                    
                </div>
            </form>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>


</body>
</html>