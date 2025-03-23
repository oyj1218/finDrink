const password = document.getElementById("password");
const passwordCheck = document.getElementById("passwordCheck");
const passwordMessage = document.getElementById("passwordMessage");
console.log("뭘까...");
const checkObj = {
    "password"      : false,
    "passwordCheck" : false
}

password.addEventListener("input", function(){
    if( password.value == "" ){
        passwordMessage.innerText = "비밀번호를 입력해주세요."
        passwordMessage.classList.remove("error", "confirm");
    } else {
		if( password.value == passwordCheck.value ){
	        passwordMessage.innerText = "비밀번호가 일치합니다.";
	        passwordMessage.classList.add("confirm");
	        passwordMessage.classList.remove("error");
	
	        checkObj.password = true;
	        checkObj.passwordCheck = true;
	    } else if( passwordCheck.value.length > 0 ){
			passwordMessage.innerText = "비밀번호가 일치하지 않습니다."
            passwordMessage.classList.add("error");
            passwordMessage.classList.remove("confirm");
    
            checkObj.password = false;
            checkObj.passwordCheck = false;
		}
	}
    
});

passwordCheck.addEventListener("input", function(){
	if( passwordCheck.value == "" ){
		passwordMessage.innerText = "비밀번호 확인을 입력해주세요."
        passwordMessage.classList.remove("error", "confirm");
        return;
	}
	
	if( passwordCheck.value.length > 0 ){
		if( password.value == passwordCheck.value ){
            passwordMessage.innerText = "비밀번호가 일치합니다.";
            passwordMessage.classList.add("confirm");
            passwordMessage.classList.remove("error");
    
            checkObj.password = true;
            checkObj.passwordCheck = true;
        } else {
            passwordMessage.innerText = "비밀번호가 일치하지 않습니다."
            passwordMessage.classList.add("error");
            passwordMessage.classList.remove("confirm");
    
            checkObj.password = false;
            checkObj.passwordCheck = false;
        }
	}
});

function validate(){
    for( let key in checkObj ){
        if( !checkObj[key] ){
            return false;
        }
    }
}