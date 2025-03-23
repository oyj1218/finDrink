const checkObj = {
	"memberId"			: false,
	"memberPw"			: false,
	"memberConfirmpw"	: false,
	"memberNickname"	: false,
}

const id = document.getElementById("id");
const idMessage = document.getElementById("idMessage");

id.addEventListener("input", function(){

	if( id.value == "" ){
		idMessage.innerText = "아이디를 입력해주세요.(영어/숫자 2~10글자 사이)";
		idMessage.classList.remove("confirm", "error");

		checkObj.memberId = false;
		
		return;
	}

	const regEx = /^[a-zA-Z0-9]{2,10}$/;
	if( regEx.test(id.value) ){
		$.ajax({
			url : "${contextPath}/member/idDupCheck",
			data : { "id" : id.value },
			success : function(result){
				if( result == 1 ){
					idMessage.innerText = "이미 사용중인 아이디 입니다.";
					idMessage.classList.add("error");
					idMessage.classList.remove("confirm");

					checkObj.memberId = false;
				} else {
					idMessage.innerText = "사용 가능한 아이디 입니다.";
					idMessage.classList.add("confirm");
					idMessage.classList.remove("error");
					
					checkObj.memberId = true;
				}
			},
			error : function(req, status, error){
				console.log();
			}
		})
	} else {
		idMessage.innerText = "아이디 형식이 올바르지 않습니다.";
		idMessage.classList.add("error");
		idMessage.classList.remove("confirm");

		checkObj.memberId = false;
	}
});

const password = document.getElementById("password");
const passwordConfirm = document.getElementById("passwordConfirm");
const passwordMessage = document.getElementById("passwordMessage");

password.addEventListener("input", function(){
	
	if( password.value == "" ){
		passwordMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 8~30글자 사이로 작성해주세요.";
		passwordMessage.classList.remove("confirm", "error");

		checkObj.memberPw = false;
		return;
	}

	const regEx = /^[\w!@#\-_]{8,30}$/;
	if( regEx.test(password.value) ){
		if( passwordConfirm.value.length == 0 ){
			passwordMessage.innerText = "유효한 비밀번호 형식입니다.";
			passwordMessage.classList.add("confirm");
			passwordMessage.classList.remove("error");

			checkObj.memberPw = true;
		} else {
			checkPw();
		}
	} else {
		passwordMessage.innerText = "비밀번호 형식이 올바르지 않습니다.";
		passwordMessage.classList.add("error");
		passwordMessage.classList.remove("confirm");

		checkObj.memberPw = false;
	}
})

passwordConfirm.addEventListener("input", checkPw);

function checkPw(){
	if( password.value == passwordConfirm.value ){
		passwordMessage.innerText = "비밀번호가 일치합니다.";
		passwordMessage.classList.add("confirm");
		passwordMessage.classList.remove("error");

		checkObj.memberConfirmpw = true;
	} else {
		passwordMessage.innerText = "비밀번호가 일치하지 않습니다.";
		passwordMessage.classList.add("error");
		passwordMessage.classList.remove("confirm");

		checkObj.memberConfirmpw = false;
	}
}

const nickname = document.getElementById("nickname");
const nicknameMessage = document.getElementById("nicknameMessage");

nickname.addEventListener("input", function(){
	if( nickname.value == "" ){
		nicknameMessage.innerText = "닉네임을 입력해주세요.(영어/숫자/한글 2~10글자 사이)";
		nicknameMessage.classList.remove("error", "confirm");

		checkObj.memberNickname = false;
		return;
	}

	const regEx = /^[a-zA-Z0-9가-힣]{2,10}$/;
	if( regEx.test(nickname.value) ){
		$.ajax({
			url : "${contextPath}/member/nicknameDupCheck",
			data : { "nickname" : nickname.value },
			success : function(result){
				if( result == 0 ){
					nicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
					nicknameMessage.classList.add("confirm");
					nicknameMessage.classList.remove("error");

					checkObj.memberNickname = true;
				} else {
					nicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
					nicknameMessage.classList.add("error");
					nicknameMessage.classList.remove("confirm");

					checkObj.memberNickname = false;
				}
			},
			error : function(req, status, error){

			}
		})
	} else {
		nicknameMessage.innerText = "닉네임 형식이 올바르지 않습니다.";
		nicknameMessage.classList.add("error");
		nicknameMessage.classList.remove("confirm");

		checkObj.memberNickname = false;
	}
})

function signUpValidate(){
		
	for( let key in checkObj ){
		if( !checkObj[key] ){
			return false;
		}
	}
	
	return true;
}
$(document).ready(function(){
  $('.password-area1 i').on('click', function(){
	$('input').toggleClass('active');
	if($('input').hasClass('active')){
		$(this).attr('class',"fa fa-eye fa-lg")
		.prev('input').attr('type', "text");
	} else{
		$(this).attr('class',"fa fa-eye-slash fa-lg")
		.prev('input').attr('type', 'password');
	
    }
  });
});
$(document).ready(function(){
  $('.password-area2 i').on('click', function(){
	$('input').toggleClass('active');
	if($('input').hasClass('active')){
		$(this).attr('class',"fa fa-eye fa-lg")
		.prev('input').attr('type', "text");
	} else{
		$(this).attr('class',"fa fa-eye-slash fa-lg")
		.prev('input').attr('type', 'password');
	
    }
  });
});