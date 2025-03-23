<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>recommend-pg</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/recommend/recommend-pg.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
    
   
</head>

<body>

    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        
        <main>
        <div class="main-1">매장 추천 | STORE RECOMMEND</div>
        <div class="main-2">
            <div>
                <input type="range" min="0" max="5" step="1" value="1" list="tickmarks">
                <datalist id="tickmarks">
                    <option value="0" label="0만원 이상"></option>
                    <option value="1" label="1만원 이상"></option>
                    <option value="2" label="2만원 이상"></option>
                    <option value="3" label="5만원 이상"></option>
                    <option value="4" label="10만원 이상"></option>
                    <option value="5" label="20만원 이상"></option>
                </datalist>
            </div>
            <div>
                <input type="checkbox" data-name="VIEW" class="keyWord">
                <input type="checkbox" data-name="VARIETY" class="keyWord">
                <input type="checkbox" data-name="VALUE" class="keyWord">
                <input type="checkbox" data-name="MOOD" class="keyWord">
                <input type="checkbox" data-name="SERVICE" class="keyWord">
            </div>
            <div>
                <button type="button" id="recommend-btn">추천</button>
            </div>
        </div>
        <div class="main-3">
            <div class="list-btn">
                <select name="" id="">
                    <option value="">등록 순</option>
                    <option value="">리뷰 순</option>
                    <option value="">평점 순</option>
                </select>
            </div>
            <div class="list-img">
                <div class="list-content">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTJfMzMg%2FMDAxNjIwNzk5Mzk4MTc3.nAAc44PYRZf2RZSXJA63cPUgQoe8-mWg6gmoG_JNnJkg.scxUY3IKb7nVF0IMBnLa8iQmXv5qpU9wQVWrQX7R1_4g.JPEG.rjdebqud220%2F5.JPG&type=sc960_832">
                    <p>롱타임노씨 신사점</p>
                    <p>서울 강남구 강남대로152길 15 2층</p>
                </div>
                <div class="list-content">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTJfMzMg%2FMDAxNjIwNzk5Mzk4MTc3.nAAc44PYRZf2RZSXJA63cPUgQoe8-mWg6gmoG_JNnJkg.scxUY3IKb7nVF0IMBnLa8iQmXv5qpU9wQVWrQX7R1_4g.JPEG.rjdebqud220%2F5.JPG&type=sc960_832">
                    <p>롱타임노씨 신사점</p>
                    <p>서울 강남구 강남대로152길 15 2층</p>
                </div>
                <div class="list-content">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTJfMzMg%2FMDAxNjIwNzk5Mzk4MTc3.nAAc44PYRZf2RZSXJA63cPUgQoe8-mWg6gmoG_JNnJkg.scxUY3IKb7nVF0IMBnLa8iQmXv5qpU9wQVWrQX7R1_4g.JPEG.rjdebqud220%2F5.JPG&type=sc960_832">
                    <p>롱타임노씨 신사점</p>
                    <p>서울 강남구 강남대로152길 15 2층</p>
                </div>
                <div class="list-content">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTJfMzMg%2FMDAxNjIwNzk5Mzk4MTc3.nAAc44PYRZf2RZSXJA63cPUgQoe8-mWg6gmoG_JNnJkg.scxUY3IKb7nVF0IMBnLa8iQmXv5qpU9wQVWrQX7R1_4g.JPEG.rjdebqud220%2F5.JPG&type=sc960_832">
                    <p>롱타임노씨 신사점</p>
                    <p>서울 강남구 강남대로152길 15 2층</p>
                </div>
                <div class="list-content">
                    <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA1MTJfMzMg%2FMDAxNjIwNzk5Mzk4MTc3.nAAc44PYRZf2RZSXJA63cPUgQoe8-mWg6gmoG_JNnJkg.scxUY3IKb7nVF0IMBnLa8iQmXv5qpU9wQVWrQX7R1_4g.JPEG.rjdebqud220%2F5.JPG&type=sc960_832">
                    <p>롱타임노씨 신사점</p>
                    <p>서울 강남구 강남대로152길 15 2층</p>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        
    </container>
    <script src="${contextPath}/resources/js/recommend/recommend-pg.js"></script>
</body>

</html>