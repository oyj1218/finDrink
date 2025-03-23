<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>land-pg</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/main-pg/main-style.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>

    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
    
    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    
      <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    

    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>
    
    

</head>

<body>
    <container>
        <!-- 헤더 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <!-- 메인, 지도,검색 -->
        <main>
            <img class="main-logo" src="${contextPath}/resources/images/findrink.png" alt="logo">
            <h1 >We find, We Drink</h1>
            
            <!-- 지도 -->
            <div class="map-div">
                <div id="map" style="width:1040px; height:350px;"></div>
            </div>
            
            <!-- 지도 검색
            <form action="list" method="get" id="boardSearch" onsubmit="searchPlaces(); return false;">
                <input class="main-search" type="text" name="query" id="keyword" placeholder="검색어를 입력해 주세요.">
                <input type="hidden" name="type" value="${param.type}">
                <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
            </form> -->

            <!-- 지도 검색 -->
            <div class="map_wrap">
                <div id="menu_wrap" class="bg_white">
                    <div class="option">
                        <div>
                            <form action="" method="get" id="boardSearch" onsubmit="searchPlaces(); return false;">
                                <input class="main-search" type="text" name="query" id="keyword" placeholder="검색어를 입력해 주세요.">
                                <i type=submit id="submit-btn" class="fa-solid fa-magnifying-glass"></i>
                            </form>
                        </div>
                    </div>
                    <p id="result"></p>
                    <ul id="placesList"></ul>
                    <div id="pagination"></div>
                </div>
                
            </div>
        </main>

        <!-- section2 추천/ 영상, 사진-->
        <section class="section2">

            <!-- 영상,사진 -->
            <div class="section2-left">
                <video autoplay="autoplay" loop="loop" muted="muted">
                    <source src="https://videos.pexels.com/video-files/3264304/3264304-hd_1280_720_30fps.mp4"
                    type="video/mp4"></source>
                </video>
            </div>

            <!-- 찾아보기(추천버튼) -->
            <div class="section2-right">
            	<h2>사람들의 솔직 리뷰를<br> 볼 수 있어요</h2>
                
                <a class="section2-right-a" href="${contextPath}/review/list?type=1&cp=1" data-scroll>이동하기</a>
            </div>
        </section>
        
        <!-- section3 랭킹 슬라이드 -->
        <section class="section3">

            <!-- 이번주의 인기 매장, 알아보기(랭킹) -->
            <div class="section3-left">
                <h2>자유로운 소통 공간에서<br>유익한 정보를 얻어보세요</h2>
                <a class="section3-left-a" href="${contextPath}/board/list?type=3">살펴보기</a>
            </div>

            <!-- 자동슬라이드 -->
            <div dir="ltr" class="swiper-container section3-right">
                <div class="swiper-wrapper">
                    <a class="section3-context swiper-slide" href="#">
                        <div class="section3-text">
                        	<img class="section3-profile" src="${contextPath}/resources/images/oyj.png">
                        	<div class="section3-nameheart">
                        		<h2 class="section3-name">OYJ</h2>
                        		<p class="section3-heart">❤️&nbsp;2</p>
                        	</div>
                        </div>
                        <h3 class="section3-title">finDrink 즐거웠어요</h3>
                        <img class="section3-store-img" src="https://images.pexels.com/photos/9658811/pexels-photo-9658811.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="">
                    </a>
                
                    <a class="section3-context swiper-slide" href="#">
                        <div class="section3-text">
                        	<img class="section3-profile" src="${contextPath}/resources/images/bjh.png">
                        	<div class="section3-nameheart">
                        		<h2 class="section3-name">BJH</h2>
                        		<p class="section3-heart">❤️&nbsp;2</p>
                        	</div>
                        </div>
                        <h3 class="section3-title">finDrink 잘가요</h3>
                        <img class="section3-store-img" src="https://images.pexels.com/photos/4691217/pexels-photo-4691217.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="">
                    </a>

                    <a class="section3-context swiper-slide" href="#">
                        <div class="section3-text">
                        	<img class="section3-profile" src="${contextPath}/resources/images/ykj.png">
                        	<div class="section3-nameheart">
                        		<h2 class="section3-name">YKJ</h2>
                        		<p class="section3-heart">❤️&nbsp;2</p>
                        	</div>
                        </div>
                        <h3 class="section3-title">finDrink 안녕</h3>
                        <img class="section3-store-img" src="https://images.pexels.com/photos/4968338/pexels-photo-4968338.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="">
                    </a>
                </div>
            </div>
        
        </section>

        <!-- footer 푸터 -->
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>


    <script src="${contextPath}/resources/js/index.js"></script>

    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f1c445d8562fe41e54352b69e8bec81d&libraries=services"></script>
    <script src="${contextPath}/resources/js/tool/map.js"></script>
    <script src="${contextPath}/resources/js/tool/swiper.js"></script>
    <script src="${contextPath}/resources/js/tool/scrollout.js"></script>
    

    
</body>

</html>