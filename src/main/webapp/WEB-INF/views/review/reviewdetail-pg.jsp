<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>reviewdetail-pg</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/review/reviewdetail-pg.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>


</head>

<body>
    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <main>
            <section class="section3">
                <div class="section3-review">
                    <div class="section3-writer">
                        <c:if test="${ empty detail.memberProfileImage}">
                            <img src="${contextPath}/resources/images/user.png">
                        </c:if>
                        <c:if test="${ !empty detail.memberProfileImage}">
                            <img src="${contextPath}${detail.memberProfileImage}">
                        </c:if>
                      
                        <span>${ detail.memberNickname }</span>
                        <div class="section3-wirter-div">
                            <span class="section3-enrolltime">${ detail.createDate }</span>
                            <button class="report-btn" id="report-icon">
								<i class="fa-solid fa-triangle-exclamation"></i>
							</button>
							<jsp:include page="/WEB-INF/views/common/report.jsp" />

                        </div>
                    </div>
                    <div class="section3-context">
                        <p class="section3-context-title">${detail.reviewTitle}</p>
                        <p class="section3-context-content">${detail.reviewContent}</p>
                    </div>
                    <div class="section3-imgs">
                        <c:forEach var="img" items="${detail.images}">
                            <img class="store-img" src="${contextPath}${img.imageReName}">
                        </c:forEach>
                    </div>

                    <div class="section3-like">
                        <button class="comment-btn">🗨️${fn:length(detail.replys.reply)}</button>
                        <button class="like-btn" id="heart-icon">❤️${detail.heartCount}</button>
                        <button class="detail-btn" id="go-list">목록으로</button>
                    </div>

                    <c:if test="${loginMember.memberNo == detail.memberNo }">
                        <div class="section3-btn">
                            <button class="delete-btn" id="review-delete-btn" onclick="deleteReview(${detail.boardNo}, 1)">delete</button>
                            <span>|</span>
                            <button class="update-btn" id="review-update-btn" onclick="location.href = 'insertPage?mode=update&type=1&cp=1&no=${detail.boardNo}'">update</button>
                        </div>
                    </c:if>
                </div>
            </section>


            <section class="section4">
            	<c:forEach var="reply" items="${detail.replys.reply}">
	                <div class="section4-review">
	                    <div class="section4-writer">
	                        <div class="section4-user">
	                        	<c:if test="${ empty reply.memberProfileImage}">	                        	
		                            <img src="${contextPath}/resources/images/user.png">
	                        	</c:if>
	                        	
	                        	<c:if test="${ !empty reply.memberProfileImage }">
		                            <img src="${contextPath}${reply.memberProfileImage}">	                        	
	                        	</c:if>
	                        	
	                            <span>${reply.memberNickname}</span>
	                        </div>
	                        <p>${reply.replyContent}</p>
	                        <span class="section4-enrolltime">${reply.createDate }</span>
	                    </div>
	                    <c:if test="${loginMember.memberNo == reply.memberNo }">
		                    <div class="section4-btn">
		                        <button class="delete-btn" onclick="deleteReply(${reply.replyNo}, ${detail.replys.pagination.currentPage })">delete</button>
		                        <span>|</span>
		                        <button class="update-btn" onclick="showUpdateBtn(${reply.replyNo}, ${detail.replys.pagination.currentPage}, this)">update</button>
		                    </div>	                    
	                    </c:if>
	                    
                	</div>
            	</c:forEach>
            </section>

            <c:if test="${ !empty loginMember }">
                <section class="section-reply">
                    <div class="section4-writer">
                        <div class="section4-user">
                            <c:if test="${ empty loginMember.memberProfileImage}">	                        	
                                <img src="${contextPath}/resources/images/user.png">
                            </c:if>
                            <c:if test="${ !empty loginMember.memberProfileImage }">
                                <img src="${contextPath}${loginMember.memberProfileImage}">	                        	
                            </c:if>
                            <span>${loginMember.memberNickname}</span>
                        </div>
                    </div>
                    <div class="reply-insert-area">
                        <textarea name="comment" id="reply-content"></textarea>
                        <button class="insert-btn" id="reply-insert">insert</button>
                    </div>
                </section>
            </c:if>
            

            <section class="pagination-area">
               	<c:set var="pagination" value="${detail.replys.pagination}"/>
            	<c:if test="${ fn:length(detail.replys.reply) > 0 }">    
	                <ul class="pagination">
	                	<c:set var="url" value="detail?reviewNo=${detail.boardNo}&cp="/>
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
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>
    <!-- <script>
            const contextPath = "${contextPath}";
            const boardNo = "${detail.boardNo}";
            const memberNo = "${loginMember.memberNo}";
    </script>  -->
    
    <script>
 
        // 최상위 주소
        const contextPath = "${contextPath}";
    
        // 게시글 번호
        const boardNo = "${detail.boardNo}"; // "500"
		
		// 신고당한 회원 번호
        const memberNo = "${detail.memberNo}";
		
		// 로그인한 회원 번호
		const rtMemberNo = "${loginMember.memberNo}";

        const loginMemberNo = "${loginMember.memberNo}"
    </script>
    
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script src="${contextPath}/resources/js/reply/reply.js"></script>
    <script src="${contextPath}/resources/js/review/reviewDetail.js"></script>
</body>

</html>