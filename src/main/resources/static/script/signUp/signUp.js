console.log("연결완료")

const form = document.getElementById("signUpForm");
const usernameInput = document.getElementById("userName");
let emailInput = document.getElementById("email");
let emailAuthInput = document.getElementById("emailAuthNum");
let passwordInput = document.getElementById("password");
let checkPasswordInput = document.getElementById("checkPassword");
let userPhoneInput = document.getElementById("userPhone");
let idchk = false;
let mailchk = false;
$("#passwordHelp").hide();
$("#password-same").hide();


// 메일인증
$("#emailSend").on("click", function() {
//       console.log(idchk);
//       console.log(emailInput.value=="");
      if(emailInput.value == ""){
        alert("이메일을 입력하세요");
        emailInput.focus();
        return;
      }

      if(!idchk){
        alert("이메일을 형식에 맞게 입력해주세요");
        emailInput.focus();
        return;
      }

      if(!mailchk) {
        alert("이미 존재하는 이메일입니다. 사용하실 수 없습니다.")
        emailInput.focus();
        return;
      }


      const email = emailInput.value;
        $.ajax({
              type: 'POST',
              url: "/signup/mailcheck",
              data: {
                email : email
               },
              success: function(data) {
                alert("이메일로 인증번호가 발송되었습니다.");
              },
              error: function(xhr, status, error) {
                if (xhr.status === 400) {
                    alert(xhr.responseText);
                }
              }
        });
});


// 인증번호 check
//$("#mailCheckNum").on("click", function() {
//    const emailAuth = emailAuthInput.value;
//
//    $.ajax({
//          type: 'POST',
//          url: "/mail/check/auth",
//          data: {
//            emailAuth : emailAuth
//           },
//          success: function(data) {
//                if(data === 0) {
//                    alert("메일 인증이 완료되었습니다.");
//                } else if(data === 1) {
//                    alert("세션이 만료되었습니다. 처음부터 다시 시도해주세요.");
//                } else {
//                    alert("인증번호가 일치하지 않습니다.");
//                }
//          }
//    });
//});


var $email = $("#email");
// 아이디 정규식
$("#email").on("keyup", function(event) {
    console.log("Email keyup 발생");

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
        idchk = true;
        $.ajax({
            type : "POST",                  // http 방식
            url : "/signup/checkid",        // ajax 통신 url
            data : {                        // ajax 내용 => 파라미터 : 값 이라고 생각해도 무방
                "id" : $email.val(),
                "type" : "email"
            },
            success : function(data) {
                if (data === 1) {                // 1이면 이메일 중복
                    console.log(data);
                    mailchk = false;
                    var elements = document.getElementById("emailHelp");
                    elements.innerHTML = "이미 사용중인 이메일입니다"
                    $("#emailHelp").css({
                        "color": "#FA3E3E",
                        "font-weight": "bold",
                        "font-size": "15px"
                    })
                } else if (data === 0) {                // 아니면 중복아님
                    console.log(data);
                    mailchk = true;
                    var emailHelp = document.getElementById("emailHelp");
                    emailHelp.innerHTML = "사용 가능한 이메일입니다"
                    $("#emailHelp").css({
                            "color": "green",
                            "font-weight": "bold",
                            "font-size": "15px"
                    })
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
  const emailAuth = emailAuthInput.value;
  const password = passwordInput.value;
  const checkPassword = checkPasswordInput.value;
  const userPhone = userPhoneInput.value;

// 모든 항목 작성
  if (!userName || !email || !emailAuth || !password || !checkPassword || !userPhone) {
    alert("모든 항목을 작성해주세요.");
    return;
  }


$(document).ready(function () {
    const userName = $("#userName").val();
    const email = $("#email").val();
    const authNum = $("#emailAuthNum").val();
    const password = $("#password").val();
    const checkPassword = $("#checkPassword").val();
    const userPhone = $("#userPhone").val();
    console.log("ajax 직전");
    $.ajax({
      type: 'POST',
      url: "/member/signup",
      data: {
        userName: userName,
        email: email,
        authNum : authNum,
        password: password,
        checkPassword: checkPassword,
        userPhone : userPhone
        },
      success: function (response) {
        console.log(response);
        window.location.href="/";
        alert(`Sign-up successful!\nUsername: ${userName}\nEmail: ${email}\nPhone: ${userPhone}`);
      },
      error: function (error) {
        if (error.responseText == "password") {
            alert("비밀번호 확인을 체크해주세요.");
            checkPasswordInput.focus();
        }

        if (error.responseText == "passwordRegExp") {
            alert("비밀번호는 영문, 숫자, 특수문자를 포함하여 최소 8자 이상이어야합니다.");
            passwordInput.focus();
        }

        if (error.responseText == "authNum") {
            alert("인증번호를 확인해주세요.");
            emailAuthInput.focus();
        } else if (error.responseText == "codeNull") {
            alert("세션이 만료되었습니다. 처음부터 다시 시도해주세요.");
            emailAuthInput.focus();
        }

        $("#signUpBtn").addClass('shake');
            setTimeout(function() {
                $("#signUpBtn").removeClass('shake'); // 0.8초 후 shake 클래스 제거
            }, 800);
        console.log(error.responseText);
      }
    });
  });
});
