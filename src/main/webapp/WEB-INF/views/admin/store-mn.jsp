<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>store-mn</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/admin-pg/store-mn.css">
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
   
</head>

<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
            <jsp:include page="/WEB-INF/views/common/adminSide.jsp"/>
            <div class="main-2">
                <div><a href="#">매장 관리</a></div>
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
                                <p>매장 번호</p>
                                <p>유저 아이디</p>
                                <p>매장 이름</p>
                                <p>매장 주소</p>
                                <p>매장 전화번호</p>
                                <p></p>
                            </form>
                            
                        </div>

                        <div class="list">
                            <form action="#">
                                <p>3</p>
                                <p>user@mail.com</p>
                                <p>매장</p>
                                <p>서울시 역삼동</p>
                                <p>01012341234</p>
                                <p>
                                    <button type="submit">등록</button>
                                    <button type="submit">삭제</button>
                                </p>
                            </form>
                        </div>

                        <div class="list">
                            <form action="#">
                                <p>3</p>
                                <p>user@mail.com</p>
                                <p>매장</p>
                                <p>서울시 역삼동</p>
                                <p>01012341234</p>
                                <p>
                                    <button type="submit">등록</button>
                                    <button type="submit">삭제</button>
                                </p>
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