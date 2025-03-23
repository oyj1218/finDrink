<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
    <title>command-add</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/mypage/user/usercomupdate-pg.css" />
    
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
                <h1>커뮤니티 | COMMUNITY</h1>
            </section>
            <section class="section2"> 
                <select>
                    <option>매장 추천</option>
                    <option>술 추천</option>
                </select>
                <input type="text" class="main-input" name="main-title" id="main-title" placeholder="제목 | TITLE">
                <form method="post">
                    <textarea name="summernote" id="summernote" maxlength="10000" cols="30" rows="5" placeholder="내용을 입력해주세요" class="with-border">
                    </textarea>
                </form>

            </section>
            <section class="section3">
                <div class="section3-btn">
                    <a href="#" class="cancel-btn">cancel</a>
                    <a href="#" class="submit-btn">submit</a>
                </div>
            </section>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/community/comadd-pg.js"></script>
    <script src="${contextPath}/resources/js/tool/typeit.js"></script>

</body>
</html>