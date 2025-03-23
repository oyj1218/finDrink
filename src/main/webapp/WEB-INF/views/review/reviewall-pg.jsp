<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review-all</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/review/reviewall-pg.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
</head>

<body>
    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <main>
            <section class="section1">
                <h1>매장 리뷰 | STORE REVIEW</h1>
            </section>
            
            <section class="section2">
            
                <c:forEach var="review" items="${items.reviews}">
                    <div class="section2-review">
                        <a href="${contextPath}/review/selectMember?memberNo=${review.memberNo}">
                           <div class="section2-writer">
                               <c:if test="${ empty review.memberProfileImage }">
                                   <img class="section2-profile" src="${contextPath}/resources/images/user.png">
                               </c:if> 
                               <c:if test="${ !empty review.memberProfileImage }">
                                   <img class="section2-profile" src="${contextPath}${review.memberProfileImage}">
                               </c:if>
                               <h2 class="section2-name">${review.memberNickname}</h2>
                           </div>
                        </a>
                   
                        <a href="${contextPath}/review/detail?reviewNo=${review.boardNo}">
                                <img class="section2-store" src="${contextPath}${review.thumbnail}">                   
                                <p class="section2-context" id="review-title">${review.reviewTitle}</p>
                        </a>
                        
                        <c:if test="${ loginMember.memberNo == review.memberNo }">
                            <div class="section2-btn">
                                <button class="update-btn" onclick="location.href='insertPage?mode=update&type=1&cp=${items.pagination.currentPage}&no=${review.boardNo}'">update</button>
                                <span>|</span>
                                <button class="delete-btn" onclick="deleteReview(${review.boardNo}, ${items.pagination.currentPage})">delete</button> 
                            </div>
                        </c:if>
                        
                        <div class="section2-text">
                            <span class="section2-enrolldate">${review.createDate}</span>
                            
                        </div>
                            
                    </div>
                </c:forEach>
                
            </section>

            <section class="pagination-area">
                <c:if test="${fn:length(items.reviews) > 0 }">
                    <ul class="pagination">
                    <c:set var="pagination" value="${items.pagination}"/>
                    <c:set var="url" value="list?type=1&cp="/>
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
                        <li><a href="${url}${paination.maxPage}">&gt;&gt;</a></li>
                    </ul>
                </c:if>
                
                <c:if test="${ !empty loginMember }">
                    <button class="add-btn" id="insertBtn" onclick="location.href='insertPage?mode=insert&type=${param.type}&cp=${param.cp}'">add</button>
                </c:if>
            </section>

        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        
    </container>


    	<script>
 
        // 최상위 주소
        const contextPath = "${contextPath}";
    
        // 게시글 번호
        const boardNo = "${review.boardNo}"; // "500"
		
		// 신고당한 회원 번호
        const memberNo = "${review.memberNo}";
		
		// 로그인한 회원 번호
		const rtMemberNo = "${loginMember.memberNo}";
    </script>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/review/review.js"></script>
</body>

</html>