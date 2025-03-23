Kakao.init("f1c445d8562fe41e54352b69e8bec81d");



function kakaoLogin() {
	Kakao.Auth.loginForm({
		scope: 'profile_nickname, profile_image, talk_message',
		success: function(authObj) {
			console.log(authObj);
			Kakao.API.request({
				url: '/v2/user/me',
				success: res => {
					const kakao_account = res.kakao_account;
					console.log(kakao_accuont);
				}
			});
		}
	})
};



$(document).ready(function() {
	$('.password-area1 i').on('click', function() {
		$('input').toggleClass('active');
		if ($('input').hasClass('active')) {
			$(this).attr('class', "fa fa-eye fa-lg")
				.prev('input').attr('type', "text");
		} else {
			$(this).attr('class', "fa fa-eye-slash fa-lg")
				.prev('input').attr('type', 'password');

		}
	});
});

function loginValidate(){
	const id = document.getElementById("id");
	if( id.value.trim().length == 0 ){
		alert("아이디를 입력해주세요.");
		return false;
	}

	const password = document.getElementById("password");
	if( password.value.trim().length == 0 ){
		alert("비밀번호를 입력해주세요.");
		return false;
	}

	return true;
}