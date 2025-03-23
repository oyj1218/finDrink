<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mypage-reviewcheck</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/myreviewcheck-pg.css" />

    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
    
    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <main>
            <jsp:include page="/WEB-INF/views/common/userSide.jsp"/>
            <div class="main-3">
                <section class="main-1">
                    <h1 class="section2-title">매장리뷰 | STORE REVIEW</h1>
                    <form class="section2-info" action="#">
                        <!-- <p>보고싶은 타입으로 정렬하세요</p> -->
                        <div>
                            <!-- <select name="review-range" id="review-range">
                                <option value="regist-order" selected>등록순</option>
                                <option value="rating-order">평점순</option>
                            </select>
                            <div class="section1-btn">
                                <button class="select-btn">select</button>
                            </div> -->
                        </div>
                    </form>
                </section>

                <section class="main-2">
                    <c:if test="${ fn:length(map.reviews) > 0 }">
                        <c:forEach var="review" items="${map.reviews}">
                            <div class="section3-review">
                                <div class="section3-writer">
                                    <img src="${contextPath}/resources/images/user.png">
                                    <span>${review.memberNickname} </span>
                                </div>
                                <div class="section3-context">
                                    <p>${review.reviewContent}</p>
                                        <!-- <img class="store-img" src="${item.reviewImage}"> -->
                                    <img class="store-img" src="${contextPath}${review.thumbnail}">
                                </div>
                                <div class="section3-detail">
                                    <div class="section3-like">
                                        <div class="comment-btn">🗨️${review.replyCount}</div>
                                        <div class="like-btn">❤️${review.heartCount}</div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${ empty map.reviews }">
                        <p>리뷰를 등록해주세요.</p>
                    </c:if>
                </section>

                <section class="pagination-area">
                    <c:set var="pagination" value="${map.pagination}"/>
                    <c:if test="${ fn:length(map.reviews) > 0 }">    
                        <ul class="pagination">
                            <c:set var="url" value="userReview?memberNo=${loginMember.memberNo}&cp="/>
                            <li><a href="${url}1">&lt;&lt;</a></li>
                            <li><a href="${url}${pagination.prevPage}">&lt;</a></li>
     
                            <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
                                <c:choose>
                                    <c:when test="${pagination.currentPage == i}">
                                        <li><a class="current">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${url}${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                         
                            <li><a href="${url}${pagination.nextPage}">&gt;</a></li>
                            <li><a href="${url}${pagination.maxPage}">&gt;&gt;</a></li>
                        </ul>
                    </c:if>
                </section>
            </div>
        </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script>
        const contextPath = "${contextPath}";
        const boardNo = "${detail.boardNo}";
        // const memberNo = "${loginMember.memberNo}";
    </script>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/mypage/myPage.js"></script>
    
</body>
</html>