console.log("연결완료")

const form = document.getElementById("signUpForm");
console.log(form);
const usernameInput = document.getElementById("userName");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const checkPasswordInput = document.getElementById("checkPassword");
const userPhoneInput = document.getElementById("userPhone");

$("#emailHelp").hide();
$("#email-certification").hide();
$("password-same").hide();


////var $email = $("#email");
// 아이디 정규식
		$("#email").on("keyup", function(event) { // 키보드에서 손을 땠을 때 실행
//	        alert("keyup 발생")
			var regExp = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;

			if (!regExp.test($("#email").val())) { // id 가 공백인 경우 체크
//				idchk = false;
				$("#emailHelp").html("<small id='emailHelp'>이메일 형식에 맞게 작성하세요</small>");
				$("#emailHelp").show();
				$("#emailHelp").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "15px"
				})
			} else { // 공백아니면 중복체크
                $("#emailHelp").hide();
				$.ajax({
					type : "POST", // http 방식
					url : "/login/checkEmail", // ajax 통신 url
					data : { // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
						"type" : "email",
						"id" : $email.val()
					},
					success : function(data) {
						if (data == 1) { // 1이면 중복
							idchk = false;
							$email.html("<small id='emailHelp'>이미 존재하는 아이디입니다</small>")
							$("#emailHelp").css({
								"color" : "#FA3E3E",
								"font-weight" : "bold",
								"font-size" : "15px"

							})
							//console.log("중복아이디");
						} else { // 아니면 중복아님
							idchk = true;
							$email.html("<small id='emailHelp'>사용가능한 아이디입니다</span>")

							$("#emailHelp").css({
								"color" : "#0D6EFD",
								"font-weight" : "bold",
								"font-size" : "15px"

							})
							//console.log("중복아닌 아이디");
						}
					}
				})

			}
		});


form.addEventListener("submit", event => {
  event.preventDefault();

  const userName = usernameInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;
  const checkPassword = checkPasswordInput.value;
  const userPhone = userPhoneInput.value;

// 모든 항목 작성
  if (!userName || !email || !password || !checkPassword || !userPhone) {
    alert("모든 항목을 작성해주세요.");
    return;
  }

// 비밀번호 일치 여부
  if (password !== checkPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

  alert(`Sign-up successful!\nUsername: ${userName}\nEmail: ${email}\nPhone: ${userPhone}`);



$(document).ready(function () {
    const userName = $("#userName").val();
    const email = $("#email").val();
    const password = $("#password").val();
    const checkPassword = $("#checkPassword").val();
    const PhoneNumber = $("#userPhone").val();
    console.log("ajax직전");
    $.ajax({
      type: 'POST',
      url: "user/signUp",
      data: {
        userName: userName,
        email: email,
        password: password,
        checkPassword: checkPassword,
        userPhone : userPhone
        },
      success: function (response) {
        console.log(response);
      },
      error: function (error) {
        console.log(error);
      },
    });
  });
});
