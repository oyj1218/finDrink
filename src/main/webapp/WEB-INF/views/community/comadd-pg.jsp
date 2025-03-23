<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <title>community add</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/community/comadd-pg.css" />
    
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>

    <link rel="stylesheet" href="${contextPath}/resources/css/summernote/summernote-lite.css">
    
    <script src="${contextPath}/resources/js/summernote/summernote-lite.js"></script>
    <script src="${contextPath}/resources/js/summernote/lang/summernote-ko-KR.js"></script>

</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>

            

            <section class="section1">
                <!-- <h1>커뮤니티 | COMMUNITY</h1> -->
                <h1 class="board-name">${boardName}</h1>
            </section>

            <form action="write" enctype="multipart/form-data" method="POST" class="board-write"
            onsubmit="return writeValidate()">

	            <!-- 게시판 구분 -->
	            <section class="section2"> 
	                <select name="boardCategory" value="${param.boardCategory}">
						<option value="술 이야기">술 이야기</option>
	                    <option value="매장 이야기">매장 이야기</option>
	                </select>
	
	                <!-- 제목 -->
	                <input type="text" class="main-input" name="boardTitle" id="main-title" placeholder="제목 | TITLE" value="${detail.boardTitle}">
	                
	                <!-- 내용 -->
	                <!-- <form method="post"> -->
	                <textarea name="boardContent" placeholder="내용을 입력해주세요" class="with-border">${detail.boardContent}</textarea>`
	                <!-- <textarea name="boardContent2" id="summernote" maxlength="10000" cols="30" rows="5" ></textarea> -->
	                <!-- </form> -->
	
	                <!-- 업로드 이미지 -->
					
	                <h5 class="upload-h5">업로드 이미지</h5>
					<%-- imageList에 존재하는 이미지 레벨을 이용하여 변수 생성 --%>
					<c:forEach items="${detail.imageList}" var="boardImage">
					
						<c:choose>
							
							<c:when test="${boardImage.imageLevel == 0}">
								<%-- c:set 변수는 pageScope가 기본값 (조건문이 끝나도 사용 가능) --%>
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

    <script src="${contextPath}/resources/js/board/board.js"></script>
    <script src="${contextPath}/resources/js/board/boardWriteForm.js"></script>
    <script src="${contextPath}/resources/js/community/comadd-pg.js"></script>
    <script src="${contextPath}/resources/js/tool/typeit.js"></script>

</body>
</html>