const form = document.getElementById("editUser-form");

// // 비밀번호 일치 여부
// $("#checkPassword").on("keyup", function(event) { // 키보드에서 손을 땠을 때 실행
//
// });


// 비밀번호 일치 여부
form.addEventListener("submit", event => {
    event.preventDefault();

    $(document).ready(function () {
        const userName = $("#userName").val();
        const PhoneNumber = $("#userPhone").val();
        $.ajax({
          type: 'POST',
          url: "/mypage/editUser",
          data: {
            userPhone : userName,
            address : PhoneNumber
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
