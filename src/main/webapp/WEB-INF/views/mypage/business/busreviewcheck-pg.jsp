<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bus-reviewcheck</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/busreviewcheck-pg.css">
    
    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
    
</head>

<body>

    <container>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <main>
            <jsp:include page="/WEB-INF/views/common/busSide.jsp" />

            <c:if test="${ !empty map.reviews }">
                <section class="section4" id="section4">
                    <c:forEach var="item" items="${map.reviews}">
                        <div class="section4-review">
                            <div class="section4-writer">
                                <img src="${contextPath}/resources/images/oyj.png">
                                <span>${item.memberNickname}</span>
                                <div>
                                    <span class="section4-enrolltime">등록일 : ${item.createDate} </span>
                                    <button class="report-btn"><i class="fa-solid fa-triangle-exclamation"></i></button>
            
                                </div>
                            </div>

                            <div class="section4-context">
                                <c:if test="">
                                    <img class="store-img" src="${item.reviewImage}">
                                </c:if>
                                <p>${item.reviewContent}</p>
                            </div>

                            <div class="section4-detail">
                                <div class="section4-like">
                                    <button class="comment-btn">🗨️${item.replyCount}</button>
                                    <button class="like-btn">❤️${item.heartCount}</button>
                                </div>
                                <c:if test="${ item.memberNo == loginMember.memberNo }">
                                    <div class="section2-btn">
                                        <button class="update-btn">update</button>
                                        <span>|</span>
                                        <button class="delete-btn">delete</button>
                                    </div>	
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                    <section class="pagination-area">
                    <c:set var="pagination" value="${map.pagination}"/>
                    <c:if test="${ !empty map.reviews }">    
                        <ul class="pagination">
                            <c:set var="url" value="businessReview?memberNo=${loginMember.memberNo}&cp="/>
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
                </section> 

                         
            </c:if>

            <c:if test="${ empty map.reviews }">
                <p class="notreivew-p">작성한 리뷰가 없습니다.</p>
            </c:if>

        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>


</body>

</html>