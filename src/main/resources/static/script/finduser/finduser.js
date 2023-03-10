//// test
//$("input[name='find']").change(function() {
//    var test = $("input[name='find']:checked").val();
//    alert(test);
//})

$("#find-pw").hide();

// 라디오 버튼
$(document).ready(function() {
    $("input[name='find']").change(function() {
        // 초깃값 설정

        if($("input[name='find']:checked").val() === 'id') {
            $("#find-id").show();
            $("#find-pw").hide();

        } else if ($("input[name='find']:checked").val() === 'pw') {
            $("#find-pw").show();
            $("#find-id").hide();
        }
    });
});


// 아이디 찾기
$(document).ready(function () {
    const userName = $("#userName").val();
    const userPhone = $("#userPhone").val();
    console.log("id 찾기 ajax 직전");
    $.ajax({
      type: 'POST',
      url: "/find/member/id",
      data: {
        userName: userName,
        userPhone : userPhone,
       },
      success: function (response) {
        console.log(response);
        // success시 console -> 모달 창으로 userEmail return
      },
      error: function (error) {
        alert("회원 정보가 없습니다.");
      }
    });
});

// 비밀번호 찾기
$(document).ready(function () {
    const userName = $("#userEmail").val();
    const userPhone = $("#mail-authentication").val();
    console.log("id 찾기 ajax 직전");
    $.ajax({
      type: 'POST',
      url: "/find/member/pw",
      data: {
        userEmail: userEmail,
        mailAuthentication : mailAuthentication,
       },
      success: function (response) {
        console.log(response);
      },
      error: function (error) {
        alert("회원 정보가 없습니다.");
      }
    });
});