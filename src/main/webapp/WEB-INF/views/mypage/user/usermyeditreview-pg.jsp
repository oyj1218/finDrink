<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>user-update review</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/user/usermyeditreview-pg.css">

    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>

</head>

<body>

    <container>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        

        <main>

            <jsp:include page="/WEB-INF/views/common/userSide.jsp"/>

            <section class="section2">
                <div class="col2">
                    <form action="#" name="review-submit-form" id="review-submit-form" method="" onsubmit="submitReviewValidate()">

                        <div class="col2-row2">
                            <input type="text" name="storeName" id="storeName" placeholder="매장 이름을 작성해주세요.">
                            <!-- <span id="store-name">술이남</span> -->
                        </div>


                            <div class="col2-row3">


                                <div class="col2-row3-1">
                                    <div class="upload-image-area">
                                        <div class="store-image">
                                            <label>
                                                <div class="store-image-upload"></div>
                                                <input type="file" class="store-upload-input" name="upload-input" id="upload-input" accept="image/*" required>
                                            </label>
                                        </div>
                                        <br>
                                        <div class="store-preview-image">
                                            <img src="${contextPath}/resources/images/storepreview.png" class="preview-image" id="preview-image">
                                        </div>

                                    </div>

                                    <div class="store-input">

                                        <div class="store-info">

                                            <input type="text" class="reviewTitle" name="reviewTitle" id="reviewTitle" placeholder="제목을 작성해주세요.">
                                            <form method="post">

                                            <textarea name="reviewContent" maxlength="10000" cols="36" rows="10" placeholder="리뷰를 작성해주세요." class="with-border"></textarea>

                                            </form>
                                            
                                        </div>
                                    </div>
                                </div>

                                <section class="section3">
                                    <div class="section3-btn-area">
                                        <a href="${contextPath}/mypage/userReview" class="cancelBtn" onclick="alert('등록을 취소합니다.')">cancel</a>
                                        <span>|</span>
                                        <button type="submit" id="submitBtn">submit</button>
                                        <!-- <a href="#" class="submit-btn">submit</a> -->
                                    </div>
                                </section>
                            </div>

                            <!-- 숨겨진 값(hidden) -->
                            <!-- 동작 구분 -->
                            <!-- <input type="hidden" name="mode" value="${param.mode}"> -->

                            <!-- 게시판 구분 -->
                            <!-- <input type="hidden" name="type" value="${param.type}"> -->

                            <!-- 게시글 번호 -->
                            <!-- <input type="hidden" name="no" value="${param.no}"> -->

                            <!-- 현재 페이지 -->
                            <!-- <input type="hidden" name="cp" value="${param.cp}"> -->

                            <!-- 존재하던 이미지가 제거되었음을 기록하여 전달하는 input -->
                            <!-- value 제거된 이미지의 레벨을 기록 -->
                            <!-- 
                                DELETE FROM BOARD_IMG
                                WHERE BOARD_NO =?
                                AND IMG_LEVEL IN(0,1,2,3,4);
                            -->
                            <!-- <input type="hidden" name="deleteList" value="" id="deleteList"> -->


                    </form>

                </div>


            </section>
        </main>


        <jsp:include page="/WEB-INF/views/common/footer.jsp" />


    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

    <script src="${contextPath}/resources/js/mypage/usermyeditreview-pg.js"></script>

</body>

</html>