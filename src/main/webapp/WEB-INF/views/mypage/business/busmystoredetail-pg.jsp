<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>bus-store detail</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/business/busmystoredetail-pg.css">
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
                        <form action="#" name="submit-form" id="submit-form">
                            <div class="col2-row2-1">
                                <div id="store-upload">
                                    <img src="${contextPath}/resources/images/storeimg1.png" alt="">
                                </div>
                                <div class="store-input">
                                    <div class="store-info">
                                        <label for=""><input type="text" id="storeName"
                                               placeholder="롱타임노씨신사점" value=""></label>
                                    </div>


                                    <div class="store-info">
                                        <label for=""><input type="text" id="storeAddress"
                                                placeholder="매장 주소 수정" value=""></label>
                                    </div>

                                    <div class="store-info">
                                        <label for=""><input type="text" id="storeTel" maxlength="13"
                                                placeholder="매장 전화번호 수정" value=""></label>
                                    </div>

                                    <div class="store-info">
                                        <label for=""><input type="text" id="storeIntro" maxlength="15"
                                                placeholder="매장 한 줄 소개 수정" value=""></label>
                                    </div>

                                </div>
                            </div>

                            <section class="section3">
                                <div class="section3-btn-area">
                                    <a href="#" class="update-btn">update</a>
                                    <a href="#" class="delete-btn">delete</a>
                                </div>
                            </section>
                            
                        </form>
                    </div>
                </div>


            </section>

        </main>


        <jsp:include page="/WEB-INF/views/common/footer.jsp" />


    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

</body>

</html>