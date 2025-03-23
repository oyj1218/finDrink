<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bus-myPage</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/myside-bus.css">

    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
            <main>
                <jsp:include page="/WEB-INF/views/common/busSide.jsp"/>
                <section clsss="section3" id="section3">
                    <p>${loginMember.memberIntro}</p>
                    <img src="https://images.pexels.com/photos/7493997/pexels-photo-7493997.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="">
                </section>
            </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>


    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

</body>
</html>