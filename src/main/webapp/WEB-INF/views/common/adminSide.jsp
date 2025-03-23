<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>adminSide</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/common/adminside.css" />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <main>
            <section class="section1 rellax" data-rellax-speed="-5">
                <img id="section1-logo" class="section1-logo" src="${contextPath}/resources/images/findrink.png" alt="">
                <ul>
                    <li>회원 관리</li>
                    <li>리뷰 관리</li>
                    <li>댓글 관리</li>
                    <li>매장 관리</li>
                    <li>커뮤니티 관리</li>
                    <li>로그아웃</li>
                </ul>
            </section>
        </main>   
    </container>

    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

</body>
</html>