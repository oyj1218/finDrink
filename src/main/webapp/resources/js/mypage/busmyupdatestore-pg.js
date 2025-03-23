checkObj = {
    "storeImage"    : false,
    "storeName"     : false,
    "storeAdrr"     : false,
    "storeTel"      : false,
    "storeIntro"    : false,
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

                profileImage.setAttribute("src", e.target.result);

                storeImage = true;
            }
        } else {
            storeImage = false;
        }
    })
}

const sname = document.getElementById("store-name");
if( sname != null ){
    sname.addEventListener("input", function(){
        if( sname.value.trim().length == 0 ){
            storeName = false;
        } else {
            storeName = true;
        }
    })
}

const sAddress = document.getElementById("store-address");
if( sAddress != null ){
    sAddress.addEventListener("change", function(){
        $.ajax({
            url : contextPath + "/store/addressDupCheck",
            data : { "address" : sAddress.value },
            success : function(res){
                if( res > 0 ){
                    alert("이미 등록된 매장입니다.");
                    sAddress.focus();
                    sAddress.value = "";
                    storeAdrr= false;
                } else {
                    storeAdrr = true;
                }
            },
            error : function(req, status, error){
                console.log(req.responseText);
            }
        })
    })
}

const sTel = document.getElementById("store-tel");
if( sTel != null ){
    sTel.addEventListener("input", function(){
        if( sTel.value.trim().length == 0 ){
            storeTel = false;
        } else {
            storeTel = true;
        }
    })
}

const sIntro = document.getElementById("store-intro");
if( sIntro != null ){
    sIntro.addEventListener("input", function(){
        if( sIntro.value.trim().length == 0 ){
            storeIntro = false;
        } else {
            storeIntro = true;
        }
    })
}


function storeAddValidate(){
    for( let key of checkObj ){
        if( !checkObj[key] ){
            return false;
        }
    }

    return true;
}
