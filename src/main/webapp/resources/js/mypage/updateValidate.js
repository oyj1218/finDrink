function updateValidate(){
    const password = document.getElementById("password");
    const passwordConfirm = document.getElementById("passwordConfirm");

    if( password.value.trim().length == 0 ){
        alert("기존 비밀번호를 입력하시거나 변경할 비밀번호를 입력해주세요");
        return false;
    }

    const regEx = /^[\w!@#\-_]{8,30}$/;
	if( !regEx.test(password.value) ){
        alert("비밀번호 형식이 유효하지 않습니다.");
        return false;
    }

    if( password.value != passwordConfirm.value ){
        alert("비밀번호 확인을 다시 입력해주세요.");
        return false;
    }
}