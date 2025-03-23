<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>reply</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/reply/reply.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>



</head>
<body>
    <!-- 댓글 -->
    <section id="reply-list" class="section4">
        <c:forEach var="reply" items="${rList}">

            <div class="section4-review">

                <c:if test="${empty reply.memberProfileImage}">
                    <!-- 프로필 이미지가 없을 경우 -->
                    <img src="${contextPath}/resources/images/user.png">
                </c:if>

                <c:if test="${!empty reply.memberProfileImage}">
                    <!-- 프로필 이미지가 있을 경우 -->
                    <img src="${contextPath}${reply.memberProfileImage}">
                </c:if>

                <div class="section4-writer reply-writer">
                    <h2>${reply.memberNickname}</h2>
                    <p class="reply-content">${reply.replyContent}</p>
                    <div class="section4-btn">
                        <span class="section4-enrolltime">${reply.createDate}</span>
                        <button class="report-btn"><i class="fa-solid fa-triangle-exclamation"></i></button>
                    </div>
                </div>



                <!-- 로그인 되어 있는 상태 댓글 수정, 삭제 -->
                <c:if test="${loginMember.memberNo == reply.memberNo}">

                    <%-- cp가 없을 경우에 대한 처리 --%>
                    <c:if test="${empty param.cp}">
                        <!-- 파라미터에 cp가 없을 경우 -->
                        <c:set var="cp" value="1"/>
                    </c:if>

                    <c:if test="${!empty param.cp}">
                        <!-- 파라미터에 cp가 있을 경우 param.cp -->
                        <c:set var="cp" value="${param.cp}"/>
                    </c:if>     

                    <div class="section4-btn">
                        <button class="insert-btn" id="insertBtn">add</button>
                        <span>|</span>
                        <button class="update-btn"id="updateBtn" onclick="showUpdateReply(${reply.replyNo}, this)">update</button>
                        <span>|</span>
                        <button class="delete-btn" id="deleteBtn" onclick="deleteReply(${reply.replyNo})">delete</button>
                    </div>
                    
                </c:if>
            </div>
        </c:forEach>
    
    </section>


    <!-- <section class="section4">
        <div class="section4-review">
            <div class="section4-writer">
                <div class="section4-user">
                    <img src="${contextPath}/resources/images/oyj.png">
                    <span>홍길동</span>
                </div>
                <p>~~~~~~~~~~~~~<br>~~~~~~~~~~~~~~~~~~~
                    ~~~~~~~<br>~~~~~~~~~~~~~
                    ~~~~~~~<br>~~~~~~~~~~~~~
                    ~~~~~~~<br>~~~~~~~~~~~~~
                    ~~~~~~~~~~~<br>~~~~~~~~~~~~~</p>
                <span class="section4-enrolltime">등록일 : 2024-04-11</span>
                <button class="report-btn"><i class="fa-solid fa-triangle-exclamation"></i></button>

            </div>
            
                <div class="section4-btn">
                    <button class="add-btn">add</button>
                    <span>|</span>
                    <button class="delete-btn">delete</button>
                    <span>|</span>
                    <button class="update-btn">update</button>
                </div>
            </div>
        </div>
    </section> -->
</body>
</html>