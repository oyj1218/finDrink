<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bus-yes store check</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/yesstorecheck-pg.css">

    <link rel="stylesheet"href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

</head>

<body>

    <container>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <main>
            <jsp:include page="/WEB-INF/views/common/busSide.jsp"/>
        
            <section class="section2">

                <div class="col2">

                    <div class="col2-row3">
                        <!-- 슬라이드 스와이퍼 사용해서 다시 만들기 -->
                        <div class="swiper">
                            <!-- Additional required wrapper -->
                            <div class="swiper-wrapper" id="swiper1">
                                <c:forEach var="store" items="${stores}">
                                    <!-- Slides -->
                                    <div class="swiper-slide"><img class="store-img" src="${contextPath}${store.storeImage}"></div>
                                </c:forEach>
                            </div>
                            <!-- If we need pagination -->
                            <div class="swiper-pagination"></div>
                            
                            <!-- If we need navigation buttons -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                            
                            <!-- If we need scrollbar -->
                            <!-- <div class="swiper-scrollbar"></div> -->
                            </div>
                        </div>
                        
                        <a class="add-btn" href="${contextPath}/store/insertPage">add</a>
                    </div>

                </div>


            </section>


        </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

    </container>

    <script src="${contextPath}/resources/js/mypage/yesstorecheck-pg.js"></script>

</body>

</html>