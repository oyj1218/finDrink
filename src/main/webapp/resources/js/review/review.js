function deleteReview(reviewNo, cp){
    if( confirm("정말로 삭제하시겠습니까?") ){
        $.ajax({
            url : contextPath + "/review/deleteList",
            data : { "reviewNo" : reviewNo},
            type : "POST",
            success : function(res){
                if( res > 0 ){
                    refreshReviews(cp);
                }
            },
            error : function(req, status, error){
                console.log(req.responseText);
            }
        })
    }
}

function refreshReviews(cp){
    $.ajax({
        url : contextPath + "/review/refreshReviews",
        data : { "currPage" : cp },
        type : "POST",
        dataType : "JSON",
        success : function(map){
            const section = document.getElementsByClassName("section2")[0];
            section.innerHTML = "";
            // reviews
            for( let review of map.reviews ){
                const reviewDiv = document.createElement("div");
                reviewDiv.classList.add("section2-review");
                section.append(reviewDiv);

                const anchor = document.createElement("a");
                anchor.setAttribute("href", contextPath +"/review/selectMember?memberNo=" + review.memberNo);

                const writerDiv = document.createElement("div");
                writerDiv.classList.add("section2-writer");
                anchor.append(writerDiv);

                const img = document.createElement("img");
                img.classList.add("section2-profile");
                if( review.memberProfileImage != null ){
                    img.setAttribute("src". contextPath + review.memberProfileImage);
                } else {
                    img.setAttribute("src", contextPath + "/resources/images/oyj.png");
                }

                const name = document.createElement("h2");
                name.classList.add("section2-name");
                name.innerText = review.memberNickname;

                writerDiv.append(img, name);

                const detail = document.createElement("a");
                detail.setAttribute("href", contextPath + "/review/detail?reviewNo=" + review.boardNo);

                const thumbnail = document.createElement("img");
                thumbnail.classList.add("section2-store");
                thumbnail.setAttribute("src", contextPath + review.thumbnail);

                const title = document.createElement("p");
                title.classList.add("section2-context");
                title.innerText = review.reviewTitle;

                detail.append(thumbnail, title);

                reviewDiv.append(anchor, detail);

                if( loginMemberNo == review.memberNo ){
                    const buttonDiv = document.createElement("div");
                    buttonDiv.classList.add("section2-btn");

                    const updateBtn = document.createElement("button");
                    updateBtn.classList.add("update-btn");
                    updateBtn.innerText = "update";

                    const seperate = document.createElement("span");
                    seperate.innerText = "|";

                    const deleteBtn = document.createElement("button");
                    deleteBtn.classList.add("delete-btn");
                    deleteBtn.innerText = "delete"
                    deleteBtn.setAttribute("onclick", "deleteReview(" + review.boardNo + "," + map.pagination.currentPage + ")");

                    buttonDiv.append(updateBtn, seperate, deleteBtn);
                    reviewDiv.append(buttonDiv);
                }

                const textDiv = document.createElement("div");
                textDiv.classList.add("section2-text");

                const createDate = document.createElement("span");
                createDate.classList.add("section2-enrolldate");
                createDate.innerText = review.createDate;

                const report = document.createElement("button");
                report.classList.add("report-btn");
                report.innerHTML = "<i class='fa-solid fa-triangle-exclamation'></i>";

                textDiv.append(createDate, report);
                reviewDiv.append(textDiv);

                section.append(reviewDiv);
            }

             // pagination 
            
            const paginationArea = document.getElementsByClassName("pagination")[0];
            if( paginationArea != null ){
                paginationArea.innerText = "";
            }
        
            if( map.reviews.length > 0 ){
                const pagination = map.pagination;
         
                const first = document.createElement("li");
                const fa = document.createElement("a");
                 
                paginationArea.append(first);
                first.append(fa);
                 
                fa.setAttribute("href", "list?&cp=1");
                fa.innerHTML = '&lt;&lt;';
                 
                const prev = document.createElement("li");
                const prevA = document.createElement("a");
                 
                paginationArea.append(prev);
                prev.append(prevA);
                 
                prevA.setAttribute("href", "list?&cp=" + pagination.prevPage);
                prevA.innerHTML = "&lt;";
                             
                for( let i = pagination.startPage; i <= pagination.endPage; i++ ){
                    const li = document.createElement("li");
                    const a = document.createElement("a");
                     
                    paginationArea.append(li);
                    li.append(a);
                     
                    if( pagination.currentPage == i ){
                        a.classList.add("current");
                        a.innerText = i;
                    } else {
                        a.setAttribute("href", "list?&cp=" + i);
                        a.innerText = i;
                    }
                }
                 
                const next = document.createElement("li");
                const nextA = document.createElement("a");
                 
                paginationArea.append(next);
                next.append(nextA);
                 
                nextA.setAttribute("href", "list?&cp=" + pagination.nextPage);
                nextA.innerHTML = "&gt;";
                 
                const max = document.createElement("li");
                const maxA = document.createElement("a");
                 
                paginationArea.append(max);
                max.append(maxA);
                                 
                maxA.setAttribute("href", "list?&cp=" + pagination.maxPage);
                maxA.innerHTML = "&gt;&gt;";
            }
             
        },
        error : function(req, status, error){
            console.log(req.responseText);
        }
    })
}



$(document).ready(function() {
    $("#report-icon").on("click", function() {
        $("#report-modal").show();
    });

    // 모달창의 닫기 버튼 클릭 시 모달창 숨기기
    $('.close').on('click', function() {
        $('#report-modal').hide();
    });

        // 모달창 외부 클릭 시 모달창 숨기기
    $(window).on('click', function(event) {
        if ($(event.target).is('#report-modal')) {
            $('#report-modal').hide();
        }
    });
        
    $("#report-form").on("submit", function(event) {
        event.preventDefault(); 

        const selectedReason = $("input[name='reportReason']:checked").val();
        if (!selectedReason) {
            alert("신고 사유가 선택되지 않았습니다. 먼저 선택해 주세요.");
            return;
        }

        $.ajax({
            url:  contextPath + "/review/report",
            type: 'POST',
            data: {
                "boardNo": boardNo, 
                "memberNo": memberNo,
                "rtMemberNo" : rtMemberNo,
                reportReason: selectedReason
            },
            success: function(response) {
                if (response > 0 ) {
                    alert("신고에 성공했습니다.");
                    $("#report-modal").hide(); 
                    refreshReportCount(); 
                } else {
                    alert("신고에 실패했습니다.");
                }
            },
            error: function(req, status, error) {
                console.log("에러 발생 : ", req.responseText);
            }
        });
    });

    function refreshReportCount() {
        console.log("신고 새로 고침");
    }
});