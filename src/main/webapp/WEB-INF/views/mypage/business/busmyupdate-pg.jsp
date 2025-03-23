<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>busupdate</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/busmyupdate-pg.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/common/myside.css">


    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
            <jsp:include page="/WEB-INF/views/common/busSide.jsp"/>
                <form action="${contextPath}/mypage/memberInfoUpdate" method="post" enctype="multipart/form-data" onsubmit="return updateValidate()">
                    <div class="main-3">
                        <input type="text" class="main-input" name="password" id="password" placeholder="비밀번호 수정 | PASSWORD">
                        <input type="text" class="main-input" name="passwordConfirm" id="passwordConfirm" placeholder="비밀번호 확인 | PASSWORD RE-ENTER">
                        <span id="passwordMessage" class="validate"></span>
                        <input type="text" class="main-input" name="phone" id="phone" placeholder="전화번호 수정 | PHONE" value="${loginMember.memberTel}">
                        <input type="text" class="main-input" name="nickname" id="nickname" placeholder="닉네임 수정 | NICKNAME" value="${loginMember.memberNickname}">
                        <input type="text" class="main-input" name="email" id="email" placeholder="이메일 수정 | EMAIL" value="${loginMember.memberEmail}">
                        <input type="text" class="main-input" name="intro" id="intro" placeholder="한 줄 소개 수정 | INTRO" value="${loginMember.memberIntro}">
                        <label>
							<div class="img-upload">프로필 사진 업로드 | PROFILE IMAGE UPLOAD</div>
							<input type="file" class="main-input" name="upload-btn" id="upload-btn" value="" accept="image/*" required>
						</label>
							
						<div class="profile-submit-area">
							<img src="${contextPath}/resources/images/user.png" id="profile-image" class="profile-image">
							<button class="submit-btn">submit</button>
						</div>
                    </div>

                </form>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>


	<script src="${contextPath}/resources/js/mypage/myPage.js"></script>
    <script src="${contextPath}/resources/js/mypage/updateValidateBus.js"></script>

</body>
</html>