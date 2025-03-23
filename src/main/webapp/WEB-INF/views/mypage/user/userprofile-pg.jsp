<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>usermy-profile</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/myside.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/user/userprofile-pg.css">
    
    
    <script src="https://unpkg.com/scroll-out/dist/scroll-out.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.11.3/gsap.min.js"></script>
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
            <main>
                <jsp:include page="/WEB-INF/views/common/userSide.jsp"/>


                <section class="section2">
                
                    <form action="#" method="post" name="profile-form" enctype="multipart/form-data">

                        <div class="profile-image-area">
 
                            <c:if test="${empty loginMember.memberProfileImage}">
                                <img src="https://static.vecteezy.com/system/resources/thumbnails/019/879/186/small_2x/user-icon-on-transparent-background-free-png.png" alt="">
                            </c:if>

                            <c:if test="${!empty loginMember.memberProfileImage}">
                                <img src="${contextPath}${loginMember.memberImg}" id="profile-image">
                            </c:if>

                            <span id="delete-image">X</span>
                            
                        </div>
                        
                        <div class="profile-btn-area">
                            <label for="input-image">이미지 선택</label>
                            <input type="file" name="profileImage" id="input-image" accept="image/*">
                            <button>변경하기</button>
                        </div>
                    
                    </form>
                
                </section>
                    


            </main>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

    <!-- <script>
        const contextPath = "${contextPath}";
    </script> -->

    <script src="${contextPath}/resources/js/myPage.js"></script>

</body>
</html>