// 미리보기 관련 요소 모두 얻어오기

const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");
const deleteList = document.getElementById("deleteList");
const deleteSet = new Set();

for(let i=0; i<inputImage.length; i++){

    
    inputImage[i].addEventListener("change", function(){

        if(this.files[0] != undefined){ // 파일이 선택되었을 때    
            const reader = new FileReader(); 
            reader.readAsDataURL(this.files[0]);
            
            reader.onload = function(e){
                preview[i].setAttribute("src", e.target.result);
                deleteSet.delete(i);
            }
        } else { 
            preview[i].removeAttribute("src"); 

        }

    });

    deleteImage[i].addEventListener("click", function(){

        
        if(preview[i].getAttribute("src") != ""){

            preview[i].removeAttribute("src");
            
            inputImage[i].value = "";
        
            deleteSet.add(i);
        }

    })

}

// 게시글 작성 유효성 검사
function writeValidate(){
    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent1 = document.querySelector("[name='boardContent']");

    if(boardTitle.value.trim().length == 0){
        alert("제목을 입력해 주세요");
        boardTitle.value = "";
        boardTitle.focus();
        return false;
    }

    if(boardContent.value.trim().length == 0){
        alert("내용을 입력해 주세요");
        boardContent.value = "";
        boardContent.focus();
        return false;
    }
    deleteList.value = Array.from(deleteSet);
    return true;
}