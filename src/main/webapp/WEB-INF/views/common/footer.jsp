<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/common/header-footer.css">
</head>
<body>
    <footer>
        <img class="footer-1" src="${contextPath}/resources/images/findrink.png" alt="">
        <article class="footer-2">
            <a href="#">이용약관</a>
            <a href="#">개인정보처리방침</a>
            <a href="#">위치정보이용약관</a>
        </article>
        <a href="#" class="footer-3">팀 소개</a>
        <p class="footer-4">© 2024 finDrink. All Rights Reserved</p>
    </footer>
    
    <%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
    
    <c:if test="${ !empty sessionScope.message }">
    
        <script>
            alert("${message}");
    
            // EL 작성 시 scope를 지정하지 않으면
            // page -> request -> session -> application 순서로 검색하여
            // 일치하는 속성이 있으면 출력
        </script>
    
        <%-- message 1회 출력 후 session에서 제거 --%>
        <c:remove var="message" scope="session"/>
    
    </c:if>

</body>
</html>


