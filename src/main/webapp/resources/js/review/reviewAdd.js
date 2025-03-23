const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");
const deleteList = document.getElementById("deleteList");
const deleteSet = new Set();

for(let i=0; i<inputImage.length; i++){
    inputImage[i].addEventListener("change", function(){

        if(this.files[0] != undefined){  
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

function writeValidate(){
    const title = document.getElementById("review-title");
    if( title.value.trim().length == 0 ){
        alert("제목을 입력해주세요.");
        return false;
    }

    const content = document.getElementById("review-content");
    if( content.value.trim().length == 0 ){
        alert("내용을 입력해주세요.");
        return false;
    }

    return true;
}

const title = document.getElementById("review-title");
title.addEventListener("input", function(){
    if( this.value.trim().length > 30 ){
        this.value = this.value.substring(0, 29);
    }
});

(function(){
    const goToList = document.getElementById("goToListBtn");
    console.log("hi");
    if( goToList != null ){
        
        goToList.addEventListener("click", function(){
            console.log("hi2");

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

