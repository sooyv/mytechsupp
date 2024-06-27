const form = document.getElementById("editUserForm");
const userPhoneInput = document.getElementById("userPhone");



// 비밀번호 일치 여부

$("#checkPassword").on("keyup", function(event) { // 키보드에서 손을 땠을 때 실행

});


// 비밀번호 일치 여부

form.addEventListener("submit", event => {
  event.preventDefault();

  const userName = usernameInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;
  const checkPassword = checkPasswordInput.value;
  const userPhone = userPhoneInput.value;

// 모든 항목 작성
  if (!password || !checkPassword || !userPhone ) {
    alert("모든 항목을 작성해주세요.");
    return;
  }

  if (password !== checkPassword) {
    alert("비밀번호가 일치하지 않습니다.");
    return;
  }

//  alert(`Sign-up successful!\nUsername: ${userName}\nEmail: ${email}\nPhone: ${userPhone}`);



$(document).ready(function () {
    const userName = $("#userName").val();
    const email = $("#email").val();
    const password = $("#password").val();
    const checkPassword = $("#checkPassword").val();
    const PhoneNumber = $("#userPhone").val();
    const address = $("#userAddress").val();
    $.ajax({
      type: 'POST',
      url: "mypage/editUser",
      data: {
//        userName: userName,
        email: email,
        password: password,
        checkPassword: checkPassword,
        userPhone : userPhone,
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
