<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>secession</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/common/secession.css"/>
   

    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>

</head>

<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
        
            <c:choose>
                <c:when test="${loginMember.memberCategory == 'user'}">
                    <jsp:include page="/WEB-INF/views/common/userSide.jsp"/>
                </c:when>

                <c:otherwise>
                    <jsp:include page="/WEB-INF/views/common/busSide.jsp"/>
                </c:otherwise>

            </c:choose>

                <form action="${contextPath}/member/secession" method="post" name="secession-form" onsubmit="return secessionValidate()">
                    <div class="main-3">
                        <input type="text" class="main-input" name="password" id="password" placeholder="비밀번호 | PASSWORD">
                        <input type="text" class="main-input" name="password" id="passwordCheck" placeholder="비밀번호 재입력 | PASSWORD RE-ENTER">
                        
                       
                        <button class="secession-btn">탈퇴하기</button>
                    </div>
                </form>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

    <script src="${contextPath}/resources/js/mypage/myPage.js"></script>

</body>
</html>