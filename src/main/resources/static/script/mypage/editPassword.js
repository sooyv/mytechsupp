const form = document.getElementById("editPasswordForm");
const passwordInput = document.getElementById("password");
const checkPasswordInput = document.getElementById("checkPassword");

// 비밀번호 일치 여부

$("#checkPassword").on("keyup", function(event) { // 키보드에서 손을 땠을 때 실행

});


// 비밀번호 일치 여부

form.addEventListener("submit", event => {
  event.preventDefault();

  const password = passwordInput.value;
  const checkPassword = checkPasswordInput.value;


  if (password !== checkPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

//  alert(`Sign-up successful!\nUsername: ${userName}\nEmail: ${email}\nPhone: ${userPhone}`);



$(document).ready(function () {
    const password = $("#password").val();
    const checkPassword = $("#checkPassword").val();
   $.ajax({
      type: 'POST',
      url: "mypage/editPassword",
      data: {
//        userName: userName,
        email: email,
        password: password,
        checkPassword: checkPassword,
        userPhone : userPhone
        address : userAddress
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
