<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<title>${boardTitle}</title>

<link rel="stylesheet"
	href="${contextPath}/resources/css/community/comdetail-pg.css" />

<link rel="stylesheet"
	href="${contextPath}/resources/css/reply/reply.css" />


<script src="https://kit.fontawesome.com/f8b69bd1ba.js"
	crossorigin="anonymous"></script>


<script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
<script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>


</head>
<body>
	<container> <jsp:include
		page="/WEB-INF/views/common/header.jsp" />
	<main>
		<section class="section1">
			<!-- <h1>커뮤니티 | COMMUNITY</h1> -->
			<h1 class="board-name">${boardName}</h1>

		</section>
		<section class="section2">
			<div class="section2-context">${detail.boardCategory}</div>
			<!-- <p class="main-title" name="main-title" id="main-title">제목 | TITLE</p> -->
			<p class="main-title" name="main-title" id="main-title">${detail.boardTitle}</p>

			<form method="post" class="section2-context">
				${detail.boardContent}</form>

			<!-- 이미지가 있을 경우 -->
			<c:if test="${!empty detail.imageList}">
				<c:set var="start" value="0" />

				<!-- 업로드 이미지가 있는 경우 -->
				<c:if test="${fn:length(detail.imageList) > start}">
					<!-- 업로드 이미지 영역 -->
					<h5 class="upload-h5">업로드 이미지</h5>
					<div class="img-box">

						<c:forEach var="i" begin="${start}"
							end="${fn:length(detail.imageList) -1}">

							<div class="boardImg">
								<img src="${contextPath}${detail.imageList[i].imageReName}">
								<a href="${contextPath}${detail.imageList[i].imageReName}"
									download="${detail.imageList[i].imageOriginal}">다운로드</a>
							</div>

						</c:forEach>

					</div>
				</c:if>
			</c:if>



			<div class="section2-btn">

				<c:if test="${loginMember.memberNo == detail.memberNo}">
					<%-- cp가 없을 경우에 대한 처리 --%>
					<c:if test="${empty param.cp}">
						<!-- 파라미터에 cp가 없을 경우 -->
						<c:set var="cp" value="1" />
					</c:if>

					<c:if test="${!empty param.cp}">
						<!-- 파라미터에 cp가 있을 경우 param.cp -->
						<c:set var="cp" value="${param.cp}" />
					</c:if>
					<button class="update-btn"
						onclick="location.href='write?mode=update&type=${param.type}&cp=${cp}&no=${detail.boardNo}'">update</button>
					<span>|</span>
					<button class="delete-btn" id="deleteBtn">delete</button>
				</c:if>

				<!-- <span class="section2-enrolldate">등록일 : 2024-04-11</span> -->
				<p>
					<span class="section2-enrolldate">등록일 : </span>
					${detail.createDate}
				</p>
				<c:if test="${!empty detail.updateDate}">
					<p>
						<span class="section2-enrolldate">최신 등록일 : </span>
						${detail.updateDate}
					</p>
				</c:if>
				<button class="report-btn" id="report-icon">
					<i class="fa-solid fa-triangle-exclamation"></i>
				</button>
				<jsp:include page="/WEB-INF/views/common/report.jsp" />

				<div class="section3-like">
					<button class="like-btn" id="heart-icon">❤️${detail.heartCount}</button>

				</div>
			</div>
		</section>

		<section class="section10">
			<div class="section4-btn">
				<button class="return-btn" id="goToListBtn">return</button>
			</div>
		</section>

		<!-- 댓글 -->
		<c:if test="${ !empty loginMember }">
			<section class="section-reply">
				<div class="section4-writer">
					<div class="section4-user">
						<c:if test="${ empty loginMember.memberProfileImage}">
							<img src="${contextPath}/resources/images/oyj.png">
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
						<button class="report-btn"><i class="fa-solid fa-triangle-exclamation"></i></button>
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
	<jsp:include page="/WEB-INF/views/common/footer.jsp" /> </container>


	<!-- jQuery 추가 -->
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"
		integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
		crossorigin="anonymous">
		
	</script>


	<script>
		// 최상위 주소
		const contextPath = "${contextPath}";

		// 게시글 번호
		const boardNo = "${detail.boardNo}"; // "500"

		// 신고당한 회원 번호
		const memberNo = "${detail.memberNo}";

		// 로그인한 회원 번호
		const rtMemberNo = "${loginMember.memberNo}";

		const loginMemberNo = "${loginMember.memberNo}";
	</script>

	<script src="${contextPath}/resources/js/reply/reply.js"></script>

	<script src="${contextPath}/resources/js/board/board.js"></script>

	<script src="${contextPath}/resources/js/tool/typeit.js"></script>

</body>
</html>
