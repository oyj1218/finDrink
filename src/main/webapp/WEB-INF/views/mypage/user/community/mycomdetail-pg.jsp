<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mycommunity-detail</title>
    
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/user/mycommunity/mycomdetail-pg.css">

</head>

<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>

            <div class="main-2">
                <a href="#">커뮤니티 관리</a>
                <div class="search">
                    <form action="#" method="get" id="comSearch" onsubmit="return searchValidate()">
                        <fieldset>
                            <p>회원 검색</p>
                            <input type="text" name="query" id="search-query" placeholder="회원 아이디 입력">
                            <button type="submit">검색</button>
                            <div>
                                <form action="#">
                                    <select name="main2-order" id="main2-order">
                                        <option value="enroll-order">등록 순</option>
                                        <option value="comment-order">댓글 수</option>
                                    </select>
                                </form>
                            </div>
                        </fieldset>
                    </form>
                    <div class="list">
                        <form action="#">
                            <p>제목</p>
                            <p>등록일</p>
                            <p>조회 수</p>

                        </form>
                        
                    </div>

                    <div class="list">
                        <form action="#">
                            <p>커뮤니티제목~!~!</p>
                            <p>2022-04-01</p>
                            <p>3</p>
                            <p>
                                <button type="submit">수정</button>
                                <button type="submit">삭제</button>
                            </p>
                        </form>
                    </div>
                    <div class="list">
                        <form action="#">
                            <p>커뮤니티제목~!~!</p>
                            <p>2022-04-01</p>
                            <p>3</p>
                            <p>
                                <button type="submit">수정</button>
                                <button type="submit">삭제</button>
                            </p>
                        </form>
                    </div>
                    <div class="list">
                        <form action="#">
                            <p>커뮤니티제목~!~!</p>
                            <p>2022-04-01</p>
                            <p>3</p>
                            <p>
                                <button type="submit">수정</button>
                                <button type="submit">삭제</button>
                            </p>
                        </form>
                    </div>
                    <div class="list">
                        <form action="#">
                            <p>커뮤니티제목~!~!</p>
                            <p>2022-04-01</p>
                            <p>3</p>
                            <p>
                                <button type="submit">수정</button>
                                <button type="submit">삭제</button>
                            </p>
                        </form>
                    </div>
             
                </div>
                <div class="pagination-area">
                    <ul class="pagination">

                        <li><a href="${url}1">&lt;&lt;</a></li>
                        <li><a href="${url}${pagination.prevPage}">&lt;</a></li>

                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">

                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current" class="section3-page">${i}</a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="${url}${i}" class="section3-page">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                        <li><a href="${url}1">&gt;</a></li>
                        <li><a href="${url}${pagination.prevPage}">&gt;&gt;</a></li>

                    </c:forEach>
                    
                    <!-- <lix><a class="current">1</a></lix>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">6</a></li>
                        <li><a href="#">7</a></li>
                        <li><a href="#">8</a></li>
                        <li><a href="#">9</a></li>
                        <li><a href="#">10</a></li> -->
                    </ul>
                </div>
            </div>

            <section class="section1">
                <form action="" class="section1-context">
                <h1>말머리</h1>
                    <h2>제목</h2>
                    <p>안녕하세요</p>
                </form>
                <div class="section1-btn">
                    <a href="#" class="delete-btn">delete</a>
                    <span>|</span>
                    <a href="#" class="update-btn">update</a>
                </div>
            </section>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/mypage/myPage.js"></script>

</body>

</html>