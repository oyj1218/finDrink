<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>member-mn</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/admin-pg/member-mn.css">
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
   
</head>

<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
        
            <jsp:include page="/WEB-INF/views/common/adminSide.jsp"/>
            <div class="main-2">
                <div><a href="#">회원 관리</a></div>
                <div>
                    <div class="search">
                        <form action="#">
                            <fieldset>
                                <p>회원 검색</p>
                                <input type="text" placeholder="회원 아이디 입력">
                                <button type="submit">검색</button>
                            </fieldset>
                        </form>
                        <div class="list title">
                            <form action="#">
                                <p>회원 번호</p>
                                <p>유저 아이디</p>
                                <p>닉네임</p>
                                <p>등록시간</p>
                                <p></p>
                            </form>
                            
                        </div>

                        <div class="list">
                            <form action="#">
                                <p>10021</p>
                                <p>user@mail.com</p>
                                <p>멍멍이</p>
                                <p>2022-04-01</p>
                                <button type="submit" onclick="secession()">탈퇴</button>
                            </form>
                        </div>
                        <div class="list">
                            <form action="#">
                                <p>10021</p>
                                <p>user@mail.com</p>
                                <p>멍멍이</p>
                                <p>2022-04-01</p>
                                <button type="submit" onclick="secession()">탈퇴</button>
                            </form>
                        </div>
                        <div class="list">
                            <form action="#">
                                <p>10021</p>
                                <p>user@mail.com</p>
                                <p>멍멍이</p>
                                <p>2022-04-01</p>
                                <button type="submit" onclick="secession()">탈퇴</button>
                            </form>
                        </div>
                        <div class="list">
                            <form action="#">
                                <p>10021</p>
                                <p>user@mail.com</p>
                                <p>멍멍이</p>
                                <p>2022-04-01</p>
                                <button type="submit" onclick="secession()">탈퇴</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>
</body>

</html>