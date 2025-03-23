// 프로필
const inputImage = document.getElementById("upload-btn");

if(inputImage != null){ 

    inputImage.addEventListener("change", function(){
        if(this.files[0] != undefined){
            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e){
                const profileImage = document.getElementById("profile-image");
                // const section1Image= document.getElementById("section1-profile");
                profileImage.setAttribute("src", e.target.result);
                // section1Image.setAttribute("src", e.target.result);
            }
        }

    })
}

// 이미지 선택 확인
function profileValidate(){

    if(inputImage.value == "" && del.value == 0) {
        // 파일 선택 X == 빈 문자열  / del의 값이 0 == x를 누르지도 않음
        // --> 아무것도 안하고 변경버튼을 클릭한 경우

        alert("이미지를 선택한 후 update버튼을 눌러주세요.");
        
        return false;
    }
    
    return true;
}

// 회원 탈퇴
function secessionValidate(){

    const password = document.getElementById("password");
    const passwordCheck = document.getElementById("passwordCheck");

    if(password.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        password.focus();
        return false;
    }

    if(passwordCheck.value.trim().length == 0){
        alert("비밀번호를 재입력해주세요.");
        passwordCheck.focus();
        return false;
    }

    if(!confirm("정말 탈퇴하시겠습니까?")){
        return false;
    }

    return true;
        
}


// 즉시 실행 함수 : 리뷰 삭제 버튼(사용자-리뷰관리)
(function(){
    
    const deleteBtn = document.getElementById("delete-btn");

    if(deleteBtn != null){ // 삭제 버튼이 화면에 존재할 때
        deleteBtn.addEventListener("click", function(){
      
            let url = "delete"; 

            const params =  new URL(location.href).searchParams;
            
            const no = "?no=" + params.get("no"); 

            const type = "&type=" + params.get("type"); 

           
            url += no + type;

            if(confirm("정말로 삭제하시겠습니까?")){
                location.href = url; 
            }


        })
    }


})();

// 리뷰 삭제
function deleteReview(boardNo){

    if(confirm("삭제하시겠습니까?")){

        $.ajax({
            url  : contextPath + "/review/delete",
            data : {"boardNo" : boardNo},
            type : "get",
            success : function(result){

                if(result > 0) {
                    alert("삭제 되었습니다.");
                    
                    // selecList();

                }else{
                    alert("삭제에 실패했습니다.");
                }
            },

            error : function(req, status, error){
                console.log("리뷰 삭제 실패");
                console.log(req.responseText);
            }

        });
    }

}

// 검색 유효성 검사
function searchValidate(){

    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){
        query.value = "";
        query.focus();
        return false;
    }

    return true;

}

