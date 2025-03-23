<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bus-update store</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/busmyupdatestore-pg.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/common/myside.css">
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>

</head>

<body>

    <container>

        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <main>

            <jsp:include page="/WEB-INF/views/common/busSide.jsp" />

            <section class="section2">

                <div class="col2">
                    
                    <div class="col2-row2">
                        <form action="${contextPath}/store/insert" enctype="multipart/form-data" method="POST" name="submit-form" id="submit-form" onsubmit="return storeAddValidate()">
                            <div class="col2-row2-1">

                                <div class="upload-image-area">

                                    <div class="store-preview-image">
                                        <img src="${contextPath}/resources/images/storepreview.png" class="preview-image" id="preview-image">
                                    </div>
                                    <label class="store-image">
                                    	<div class="img-upload">프로필 사진 업로드<br> PROFILE IMAGE UPLOAD</div>
                                    	<input type="file" class="store-upload-input" name="upload-input" id="upload-input" accept="image/*" required>
                                    </label>

                                </div>


                                <!-- <div id="store-upload"></div> -->


                                <div class="store-input">
                                    <div class="store-info">
                                        <label for=""><input type="text" name="storeName" id="store-name" placeholder="매장 이름" value=""></label>
                                    </div>
                                    
                                    <div class="store-info">
                                        <label for=""><input type="text" name="storeAddress" id="store-address" placeholder="매장 주소" value=""></label>
                                    </div>
                                    
                                    <div class="store-info">
                                        <label for=""><input type="text" name="storeTel" id="store-tel" maxlength="13" placeholder="매장 전화번호" value=""></label>
                                    </div>
                                    
                                    <div class="store-info">
                                        <label for=""><input type="text" name="storeIntro" id="store-intro" maxlength="15" placeholder="매장 한 줄 소개" value=""></label>
                                    </div>
                                    
                                </div>
                            </div>
                       
                            <section class="section3">
                                <div class="section3-btn-area">
                                    <button class="submit-btn">submit</button>
                                </div>
                            </section>

                            <input type="hidden" name="memberNo" value="${loginMember.memberNo}">
                        </form>
                    </div>
                </div>
            </section>
        </main>


        <jsp:include page="/WEB-INF/views/common/footer.jsp" />


    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

    <script src="${contextPath}/resources/js/mypage/busmyupdatestore-pg.js"></script>

</body>

</html>