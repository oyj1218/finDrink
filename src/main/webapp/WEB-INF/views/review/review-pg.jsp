<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review-info</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/review/review-pg.css" />

    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
    

</head>
<body>
    <container>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <main>
            
            <section class="section1">
                <h1 class="section1-title">매장소개 | STORE INFO</h1>
                <div class="store-info">
                    <img class="info-img" src="https://images.pexels.com/photos/17903602/pexels-photo-17903602/free-photo-of-shelf-with-alcohol-in-a-bar.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1">
                    <div class="store-p">
                        <h2>술이남</h2>
                        <p>평점 4.5 / 5.0 ⭐⭐⭐⭐⭐</p>
                        <p>주소 : 서울특별시 관악구 신림동 신림동길 5 2층</p>
                        <div>
                            <form action="${contextPath}/Review/insert">
                                <div class="section1-btn">
                                    <button class="add-btn">add</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>

            <section class="section2">
                <h1 class="section2-title">매장리뷰 | STORE REVIEW</h1>
                <form class="section2-info" action="${contextPath}/Review/sort">
                    <p>보고싶은 타입으로 정렬하세요</p>
                    <div>
                        <select name="review-range" id="review-range">
                            <option value="regist-order" selected>등록순</option>
                            <option value="rating-order">평점순</option>
                        </select>
                        <div class="section1-btn">
                            <button class="select-btn">select</button>
                        </div>
                    </div>
                </form>
            </section>

            <section class="section3">
            	<c:forEach var="item" items="${reviews}">
	                <div class="section3-review">
	                    <div class="section3-writer">
	                        <img src="${contextPath}/resources/images/user.png">
	                        <span>${item.memberNickname}</span>
	                        <div>
	                            <span class="section3-enrolltime">등록일: ${item.createDate} </span>
	                            <button class="report-btn" id="report-icon">
									<i class="fa-solid fa-triangle-exclamation"></i>
								</button>
								<jsp:include page="/WEB-INF/views/common/report.jsp" />

	                        </div>
	                    </div>
	                    <div class="section3-context">
	                    	<c:if test="">
		                        <img class="store-img" src="${item.reviewImage}">
	                    	</c:if>
	                        <p>${item.reviewContent}</p>
	                    </div>
	                    <div class="section3-detail">
	                        <div class="section3-like">
	                            <button class="comment-btn">🗨️10</button>
	                            <button class="like-btn">❤️20</button>
	                        </div>
	                        <c:if test="${ item.memberNo == loginMember.memberNo }">
		                        <div class="section3-btn">
		                            <button class="update-btn">update</button>
		                            <span>|</span>
		                            <button class="delete-btn">delete</button>
		                        </div>	                        
	                        </c:if>
	                    </div>
	                </div>
            	</c:forEach>
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

        const loginMemberNo = "${loginMember.memberNo}";
    </script>
    
</body>
</html>