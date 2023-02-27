console.log("연결완료")

const form = document.getElementById("signUpForm");
console.log(form);
const usernameInput = document.getElementById("userName");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const checkPasswordInput = document.getElementById("checkPassword");
const userPhoneInput = document.getElementById("userPhone");

$("#passwordHelp").hide();
$("#password-same").hide();


var $email = $("#email");
// 아이디 정규식
$("#email").on("keyup", function(event) {
    console.log("Email keyup 발생")

    var emailRegExp = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/;

    // email 형식 정규화
    if (!emailRegExp.test($email.val())) {
        idchk = false;
        var emailHelp = document.getElementById("emailHelp");
        emailHelp.innerHTML = "이메일 형식에 맞게 작성해주세요"
        $("#emailHelp").css({
                "color": "#FA3E3E",
                "font-weight": "bold",
                "font-size": "15px"
        })
    } else { // 공백아니면 중복체크
        $.ajax({
            type : "POST",                  // http 방식
            url : "/signup/checkid",        // ajax 통신 url
            data : {                        // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
                "id" : $email.val(),
                "type" : "email"
            },
            success : function(data) {
                if (data === 1) {                // 1이면 이메일 중복
                    console.log(data)
                    idchk = false;
                    var elements = document.getElementById("emailHelp");
                    elements.innerHTML = "이미 사용중인 이메일입니다"
                    $("#emailHelp").css({
                        "color": "#FA3E3E",
                        "font-weight": "bold",
                        "font-size": "15px"
                    })
                    //console.log("중복아이디");
                } else if (data === 0) {                // 아니면 중복아님
                    console.log(data)
                    idchk = true;
                    var emailHelp = document.getElementById("emailHelp");
                    emailHelp.innerHTML = "사용 가능한 이메일입니다"
                    $("#emailHelp").css({
                            "color": "green",
                            "font-weight": "bold",
                            "font-size": "15px"
                    })
                    //console.log("중복아닌 아이디");
                }
            }
        })
    }
});

// 비밀번호 형식 정규화(최소 8자, 영문 숫자 특수문자)
$("#password").on("keyup", function(event) {
    console.log("pw keyup 발생")

    var pwRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;

    if (!pwRegExp.test($("#password").val())) {       // 비밀번호 정규화
        $("#passwordHelp").show();
    } else {
        $("#passwordHelp").hide();
    }
});

// password passwordcheck 일치 검사
$("#checkPassword").on("keyup", function(event) {
    console.log("pw 일치 검사")

    if ($("#password").val() !== $("#checkPassword").val()) {
        $("#password-same").show();
    } else {
        $("#password-same").hide();
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
      url: "user/signup",
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
        $("#signUpBtn").addClass('shake');
            setTimeout(function() {
                $("#signUpBtn").removeClass('shake'); // 0.5초 후 shake 클래스 제거
            }, 800);
        console.log(error);
      }
    });
  });
});
