<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>review</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/review/review.css">
    
    <script src="https://kit.fontawesome.com/f8b69bd1ba.js" crossorigin="anonymous"></script>


</head>
<body>
    <section class="section3">
        <c:forEach var="item" items="${reviews}">
            <div class="section3-review">
                <div class="section3-writer">
                    <img src="${contextPath}/resources/images/user.png">
                    <span>${item.memberNickname}</span>
                    <div>
                        <span class="section3-enrolltime">등록일: ${item.createDate} </span>
                        <button class="report-btn"><i class="fa-solid fa-triangle-exclamation"></i></button>

                    </div>
                </div>
                <div class="section3-context">
                    <c:if test="">
                        <img class="store-img" src="${item.reviewImage}">
                    </c:if>
                    <p>${item.reviewContent}</p>
                </div>
                <div class="section3-detail">
                    <div class="section3-like">
                        <button class="comment-btn">🗨️${item.replyCount }</button>
                        <button class="like-btn">❤️${item.heartCount }</button>
                    </div>
                    <c:if test="${ item.memberNo == loginMember.memberNo }">
                        <div class="section3-btn">
                            <button class="update-btn">update</button>
                            <span>|</span>
                            <button class="delete-btn">delete</button>
                        </div>	                        
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </section>
</body>
</html>