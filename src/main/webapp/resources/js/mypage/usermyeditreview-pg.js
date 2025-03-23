// $(document).ready(function() {
	// 	//여기 아래 부분
// 	$('#summernote').summernote({
	
// 		  height: 300,                 // 에디터 높이
//           width: 1200,
// 		  minHeight: null,             // 최소 높이
// 		  maxHeight: null,             // 최대 높이
// 		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
// 		  lang: "ko-KR",					// 한글 설정
// 		  placeholder: '내용을 작성하세요'	//placeholder 설정

// 	});
// });

// 리뷰 작성 유효성 검사
function submitReviewValidate(){
	
	const storeName = document.getElementById("storeName");
	const reviewTitle = document.getElementById("reviewTitle");
	const reviewContent = document.querySelector("[name='reviewContent']");

	if(storeName.value.length == 0){
		alert("매장 이름을 작성해주세요.");
		storeName.focus();
		storeName.value = "";
		return false;
	}
	
	if(reviewTitle.value.length == 0){
		alert("제목을 작성해주세요.");
		reviewTitle.focus();
		reviewTitle.value = "";
		return false;
	}
	
	if(reviewContent.value.length == 0){
		alert("리뷰를 작성해주세요.");
		reviewContent.focus();
		reviewContent.value = "";
		return false;
	}

	// input 값을 하나 안 적고 submt 버튼 눌러도 input값 유지 시켜야 됨 
	
	
	return true;
}


// 프로필
const inputImage = document.getElementById("upload-input");

if(inputImage != null){ 

    inputImage.addEventListener("change", function(){
        if(this.files[0] != undefined){
            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e){
                const profileImage = document.getElementById("preview-image");
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

        // alert("이미지를 선택한 후 update버튼을 눌러주세요.");
        
        return false;
    }
    
    return true;
}
