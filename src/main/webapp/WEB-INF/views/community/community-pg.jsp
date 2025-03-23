<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- map에 저장된 값을 각각 변수에 저장 -->
<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <title>community</title> -->
    <title>커뮤니티 | COMMUNITY</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/community/community.css" />
    
    <script src="https://unpkg.com/typeit@8.7.0/dist/index.umd.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rellax/1.12.1/rellax.min.js"></script>
</head>
<body>
    <container>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        <main>
            <section class="section1">
                <!-- <h1>커뮤니티 | COMMUNITY</h1> -->
                <h1 class="board-name">${boardName}</h1>
            </section>
            <table class="section2">
                <thead class="section2-title">
                    <tr>
                        <th>카테고리</th>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>등록일</th>
                        <th>수정일</th>
                        <th>좋아요</th>
    
                    </tr>
                </thead>
                <tbody class="main-3">
                    <c:choose>
                        <c:when test="${empty boardList}">
                            <!-- 게시글 목록 조회 결과가 비어 있다면 -->
                            <tr>
                                <th colspan="7">게시글이 존재하지 않습니다.</th>
                            </tr>
                        </c:when>

                        <c:otherwise>
                            <!-- 게시글 목록 조회 결과가 비어 있지 않다면 -->

                            <!-- 향상된 for문처럼 사용 -->
                            <c:forEach var="board" items="${boardList}">
                                <tr>
                                    <td>${board.boardCategory}</td>
                                    <td>${board.boardNo}</td>
                                    <td>
                                        <a href="detail?no=${board.boardNo}&cp=${pagination.currentPage}&type=${param.type}">${board.boardTitle}</a>
                                    </td>
                                    <td>
                                        <a href="#">${board.memberNickname}</a>
                                    </td>
                                    <td>${board.createDate}</td>
                                    <td>${board.updateDate}</td>
                                    <td>${board.heartCount}</td>
                                </tr>
                            </c:forEach>

                        </c:otherwise>

                    </c:choose>

                    <!-- <tr>
                        <td>공지</td>
                        <td>1</td>
                        <td>제목~</td>
                        <td>홍길동</td>
                        <td>2024-04-11</td>
                        <td>12121</td>
                        <td>22</td>
                    </tr> -->
                    
                </tbody>

            </table>
                
            </div>
            <section class="section3">
                <!-- 페이지네이션 a태그에 사용될 공통 주소를 저장한 변수 선언 -->
                <c:set var="url" value="list?type=${param.type}&cp="/>

                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1" class="section3-page">&lt;&lt;</a></li>

                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}" class="section3-page">&lt;</a></li>

                    <!-- 범위가 정해진 일반 for문을 사용 -->
                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">

                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current" class="section3-page">${i}</a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="${url}${i}" class="section3-page">${i}</a></li>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>

                    <!-- 다음 목록 시작 번호로 이동 -->
                    <li><a href="${url}${pagination.nextPage}" class="section3-page">&gt;</a></li>
                    
                    <!-- 끝 페이지로 이동 -->
                    <li><a href="${url}${pagination.maxPage}" class="section3-page">&gt;&gt;</a></li>
                </ul>
                <c:if test="${!empty loginMember}">
                    <!-- <button class="add-btn">add</button> -->
                    <button class="add-btn" id="insertBtn" onclick="location.href='write?mode=insert&type=${param.type}&cp=${param.cp}'">add</button>
                </c:if>
                
            </section>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    </container>

    <script src="${contextPath}/resources/js/tool/typeit.js"></script>
    <script src="${contextPath}/resources/js/tool/rellax.js"></script>

</body>
</html>