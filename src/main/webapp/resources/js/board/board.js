const heart = document.getElementById("heart-icon");

if (heart != null) {
    heart.addEventListener("click", function() {
        $.ajax({
            url: contextPath + "/board/heart",
            type: 'POST',
            data: { 
                "boardNo": boardNo,
                "memberNo": memberNo
            },
            success: function(result) {
                if (result > 0) {
                    if (heart.classList.contains("active")) {
                        heart.classList.remove("active");
                    } else {
                        heart.classList.add("active");
                    }

                    location.reload();
                }
            },
            error: function(req, status, error) {
                console.log(req.responseText);
            }
        });
    });
}
(function(){
    const goToListBtn = document.getElementById("goToListBtn");

    if(goToListBtn != null){ // 목록으로 버튼이 화면에 있을 때만 이벤트 추가

        goToListBtn.addEventListener("click", function(){
            
     
            const pathname = location.pathname; 
       
            let url = pathname.substring(0, pathname.indexOf("/", 1));
    

            url += "/board/list?"; 

      
            
            const params = new URL(location.href).searchParams;

            const type = "type=" + params.get("type");
            let cp;
            
            if(params.get("cp") != ""){
               cp = "cp=" + params.get("cp");
            } else{
                cp = "cp=1";
            }

            
            url += type;
            
            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query;
            }

            
            location.href = url;
        });

    }

})();


(function(){

    const deleteBtn = document.getElementById("deleteBtn"); 

    if(deleteBtn != null){ 
        deleteBtn.addEventListener("click", function(){
            

            let url = "delete";

            const params = new URL(location.href).searchParams;

           
            const no = "?no=" + params.get("no"); 

            const type = "&type=" + params.get("type");

            url += no + type; 

            if(confirm("정말로 삭제하실건가요?")){
                location.href = url;
            }
        })
    }
})();

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
            url:  contextPath + "/board/report",
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