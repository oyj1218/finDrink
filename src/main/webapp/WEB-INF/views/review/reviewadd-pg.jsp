<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review-add</title>

	<link rel="stylesheet" href="${contextPath}/resources/css/review/reviewadd-pg.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>

            <section class="section1">
                <!-- <h1>커뮤니티 | COMMUNITY</h1> -->
                <h1 class="store-name">${storeName}</h1>
            </section>

            <form action="insertReview" enctype="multipart/form-data" method="POST" class="board-write" onsubmit="return writeValidate()">

	            <section class="section2"> 
                    <!-- view 등 카테고리 선택 -->

	                <!-- 제목 -->
	                <input type="text" class="main-input" name="boardTitle" id="review-title" placeholder="제목 | TITLE" value="${review.reviewTitle}">
	                
	                <!-- 내용 -->

	                <textarea name="boardContent" placeholder="내용을 입력해주세요" class="with-border" id="review-content">${review.reviewContent}</textarea>
	                <!-- <textarea name="boardContent2" id="summernote" maxlength="10000" cols="30" rows="5" ></textarea> -->
	                <!-- </form> -->
	
	                <!-- 업로드 이미지 -->
					
	                <h5 class="upload-h5">업로드 이미지</h5>
					<c:forEach items="${review.images}" var="boardImage">
						<c:choose>
							<c:when test="${boardImage.imageLevel == 0}">
								<c:set var="img0" value="${contextPath}${boardImage.imageReName}"/>
							</c:when>

							<c:when test="${boardImage.imageLevel == 1}">
								<c:set var="img1" value="${contextPath}${boardImage.imageReName}"/>
							</c:when>

							<c:when test="${boardImage.imageLevel == 2}">
								<c:set var="img2" value="${contextPath}${boardImage.imageReName}"/>
							</c:when>

							<c:when test="${boardImage.imageLevel == 3}">
								<c:set var="img3" value="${contextPath}${boardImage.imageReName}"/>
							</c:when>

							<c:when test="${boardImage.imageLevel == 4}">
								<c:set var="img4" value="${contextPath}${boardImage.imageReName}"/>
							</c:when>
						</c:choose>
					
					</c:forEach>
					
	                <div class="img-box">
						<!-- 업로드 이미지 -->
						<div class="boardImg">
	                        <label for="img0">
	                            <img class="preview" src="${img0}">
	                        </label>
	                        <input type="file" class="inputImage" id="img0" name="0" accept="image/*">
	                        <i class="fa-solid fa-xmark delete-image"></i>
	                    </div>
	                    <div class="boardImg">
	                        <label for="img1">
	                            <img class="preview" src="${img1}">
	                        </label>
	                        <input type="file" class="inputImage" id="img1" name="1" accept="image/*">
	                        <i class="fa-solid fa-xmark delete-image"></i>
	                    </div>
	
	                    <div class="boardImg">
	                        <label for="img2">
	                            <img class="preview" src="${img2}">
	                        </label>
	                        <input type="file" class="inputImage" id="img2" name="2" accept="image/*">
	                        <i class="fa-solid fa-xmark delete-image"></i>
	                    </div>
	
	                    <div class="boardImg">
	                        <label for="img3">
	                            <img class="preview" src="${img3}">
	                        </label>
	                        <input type="file" class="inputImage" id="img3" name="3" accept="image/*">
	                        <i class="fa-solid fa-xmark delete-image"></i>
	                    </div>
	
	                    <div class="boardImg">
	                        <label for="img4">
	                            <img class="preview" src="${img4}">
	                        </label>
	                        <input type="file" class="inputImage" id="img4" name="4" accept="image/*">
	                        <i class="fa-solid fa-xmark delete-image"></i>
	                    </div>
	
	                </div>
	
	            </section>
	          
	            
	            <!-- 버튼 영역 -->
	            <section class="section3">
	                <div class="section3-btn">
	                    <button type="submit" class="submit-btn" id="writeBtn">submit</button>
	                    <!-- insert 모드 -->
						<c:if test="${param.mode == 'insert'}">
							<button type="button" class="cancel-btn" id="goToListBtn">cancel</button>
						</c:if>
						
						<!-- update 모드 -->
	                    <c:if test="${param.mode == 'update'}">
	                        <button type="button" class="cancel-btn" onclick="location.href='${header.referer}'">cancel</button>
	                    </c:if>
	                </div>
	            </section>
	            
	            <!-- 숨겨진 값(hidden) -->
	            <!-- 동작 구분 -->
	            <input type="hidden" name="mode" value="${param.mode}">
	
	            <!-- 게시판 구분 -->
	            <input type="hidden" name="type" value="${param.type}">

				<!-- 게시글 번호 -->
				<input type="hidden" name="no" value="${param.no}">

				<!-- 현재 페이지 -->
				<input type="hidden" name="cp" value="${param.cp}">

				<input type="hidden" name="deleteList" id="deleteList" value="">
            
            
            </form> 
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

	<script src="${contextPath}/resources/js/review/reviewAdd.js"></script>
</html>