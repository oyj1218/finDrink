const heart = document.getElementById("heart-icon");
if( heart != null ){
    heart.addEventListener("click", function(){
        $.ajax({
            url : contextPath + "/review/heart",
            data : { 
                "boardNo" : boardNo,
                "memberNo" : loginMemberNo
            },
            success : function(result){
                if( result > 0 ){
                    location.reload();
                }
            },
            error : function(req, status, error){
                console.log(req.responseText);
            }
        });
    });
}

(function(){
    const goToList = document.getElementById("go-list");
    if( goToList != null ){
        
        goToList.addEventListener("click", function(){
            const pathname = location.pathname;

            let url = pathname.substring(0, pathname.indexOf("/", 1));
    
            url += "/review/list?";
    
            const params = new URL(location.href).searchParams;
            let cp;
            if( params.get("cp") != null ){
                cp = "cp=" + params.get("cp");
            } else {
                cp = "cp=1";
            }
    
            url += cp;
    
            location.href = url;
        })
    }
})();

function deleteReview(reviewNo){
    if( confirm("정말로 삭제하시겠습니까?")){
        $.ajax({
            url : contextPath + "/review/deleteDetail",
            data : { "reviewNo" : reviewNo },
            success : function( res ){
                if( res > 0 ){
                    const pathname = location.pathname;

                    let url = pathname.substring(0, pathname.indexOf("/", 1));
    
                    url += "/review/list?";
    
                    const params = new URL(location.href).searchParams;
                    let cp;
                    if( params.get("cp") != null ){
                        cp = "cp=" + params.get("cp");
                    } else {
                        cp = "cp=1";
                    }
    
                    url += cp;
                    console.log(url);
                    location.href = url;
                }
            },
            error : function(req, status, error){
                console.log(req.responseText);
            }
        })
    }
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