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
            alert("신고 사유를 선택해 주세요.");
            return;
        }

        $.ajax({
            url: "${contextPath}/board/report",
            type: 'POST',
            data: {
                boardNo: "${boardNo}", 
                memberNo: "${memberNo}",
                reportReason: selectedReason
            },
            success: function(response) {
                if (response.success) {
                    alert("신고가 접수되었습니다.");
                    $("#report-modal").hide(); 
                    refreshReportCount(); 
                } else {
                    alert("신고 처리 중 오류가 발생했습니다.");
                }
            },
            error: function(req, status, error) {
                console.log("에러 발생 : ", req.responseText);
            }
        });
    });

    function refreshReportCount() {
        console.log("신고 카운트 새로 고침");
    }
});