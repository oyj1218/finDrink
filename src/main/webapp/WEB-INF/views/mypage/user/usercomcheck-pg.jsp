<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>mycommunity-detail</title>

<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage/user/usercomcheck-pg.css">

</head>

<body>
	<container> <jsp:include
		page="/WEB-INF/views/common/header.jsp" />
	<main>
		<jsp:include page="/WEB-INF/views/common/userSide.jsp" />

		<div class="main-2">
			<a href="#">커뮤니티 관리</a>
			<div class="list">
				<form class="list-form" action="#">
					<p>제목</p>
					<p>등록일</p>
					<p>수정일</p>
					<p>좋아요</p>
					<p></p>
				</form>
			</div>
			<c:if test="${ !empty map.boards }">
				<c:forEach var="board" items="${map.boards}">
					<div class="list-content">
						<p>${board.boardTitle}</p>
						<p>${board.createDate}</p>
						<p>${board.updateDate}</p>
						<p>${board.heartCount}</p>
						<p>
							<button type="button" onclick="updateDetail(${board.boardNo})">보기</button>
						</p>
					</div>
				</c:forEach>
			</c:if>

			<c:if test="${ empty map.boards }">
				<p>게시글을 등록해주세요.</p>
			</c:if>

			<div class="pagination-area">
				<c:if test="${ !empty map.boards }">
					<ul class="pagination">
						<c:set var="pagination" value="${map.pagination}" />
						<c:set var="url"
							value="userCommunity?memberNo${loginMember.memberNo}=1&cp=" />
						<li><a href="${url}1">&lt;&lt;</a></li>
						<li><a href="${url}${pagination.prevPage}">&lt;</a></li>

						<c:forEach var="i" begin="${pagination.startPage}"
							end="${pagination.endPage}">
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

			</div>
			<section class="section3"></section>
		</div>

		

	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" /> </container>

	<script>
        const contextPath = "${contextPath}";
    </script>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/mypage/myPage.js"></script>
	<script src="${contextPath}/resources/js/mypage/usercommunity.js"></script>
</body>

</html>