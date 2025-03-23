<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${contextPath}/resources/css/report.css">
</head>
<body>
    <!-- 신고 모달창 -->
    <div id="report-modal" class="modal" style="display:none;">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>신고하기</h2>
            <hr>
            <form id="report-form">
                <p>* 신고하려는 이유를 선택해주세요.</p>
                <label>
                    <input type="radio" name="reportReason" value="spam">
                    스팸 홍보 및 도배
                </label><br>
                <label>
                    <input type="radio" name="reportReason" value="porno">
                    음란물 작성
                </label><br>
                <label>
                    <input type="radio" name="reportReason" value="curse">
                    욕설 및 혐오 표현 작성
                </label><br>
                <label>
                    <input type="radio" name="reportReason" value="slander">
                    회원 비방
                </label><br>
                <button class="submit-btn" type="submit">submit</button>
            </form>
        </div>
    </div>
</body>
</html>