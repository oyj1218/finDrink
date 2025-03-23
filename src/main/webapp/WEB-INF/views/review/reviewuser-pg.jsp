<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review-user</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/review/reviewuser-pg.css" />

    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>

</head>
<body>
    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        
        <main>
            <section class="section1" >
				<c:if test="${empty reviewer.member.memberProfileImage}">
					<img id="section1-profile" class="section1-profile" 
					src="${contextPath}/resources/images/user.png" alt="">
				</c:if>
				<c:if test="${!empty reviewer.member.memberProfileImage}">
					<img id="section1-profile" class="section1-profile"  
					src="${contextPath}${reviewer.member.memberProfileImage}" alt="">
				</c:if>
                <div class="section1-user">
                    <h1 class="section1-h1">사용자 ${reviewer.member.memberNickname}님의 리뷰페이지 입니다.</h1>
                    <p class="section1-p">${reviewer.member.memberIntro}</p>
                </div>
            </section>
            <section class="section2">
                <h2>리뷰: ${fn:length(reviewer.reviews)}개</h2>
            </section>
           	<c:forEach var="review" items="${reviewer.reviews}">
	            <section class="section3">
	            	<c:if test="${ empty review.thumbnail}">
		            	<img class="store-img" src="https://images.pexels.com/photos/17903602/pexels-photo-17903602/free-photo-of-shelf-with-alcohol-in-a-bar.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">	            	
	            	</c:if>
	            	<c:if test="${ !empty review.thumbnail }">
		            	<img class="store-img" src="${contextPath}${review.thumbnail}">	            	
	            	</c:if>
	            	
	                <div class="section3-contain">
	                    <div class="section3-writer">
	                        <span>${review.storeName}</span>
	                    </div>
	                    <div>
	                        <span class="section3-enrolltime">등록일 ${review.createDate}</span>
	                    </div>
	
	                    <div class="section3-context">
	                        <p>${review.reviewContent }</p>
	                    </div>
	                    <div class="section3-like">
	                            <button class="comment-btn">🗨️${review.replyCount }</button>
	                            <button class="like-btn">❤️${review.heartCount }</button>
	                    </div>
	                    
	                </div>
	            </section>
           	</c:forEach>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
        
    </container>
</body>
</html>