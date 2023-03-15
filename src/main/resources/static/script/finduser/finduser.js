//// test
//$("input[name='find']").change(function() {
//    var test = $("input[name='find']:checked").val();
//    alert(test);
//})

$("#formFindPw").hide();


// 라디오 버튼
$(document).ready(function() {
    $("input[name='find']").change(function() {

        // 아이디 찾기
        if($("input[name='find']:checked").val() === 'id') {
            $("#formFindId").show();
            $("#formFindPw").hide();
            console.log("이메일 찾기");

        // 비밀번호 찾기
        } else if ($("input[name='find']:checked").val() === 'pw') {
            $("#formFindPw").show();
            $("#formFindId").hide();
            console.log("비밀번호 찾기");
        }
    });
});


// 아이디 찾기
const formFindId = document.getElementById("formFindId");

formFindId.addEventListener("submit", event => {
  event.preventDefault();

    const userName = $("#userName").val();
    const userPhone = $("#userPhone").val();

    if(!userName || !userPhone) {
        alert("모든 항목을 입력하세요");
        return;
    }

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
            var findUserEmailModal = $(".modal-body");
            if (response == "") {
                findUserEmailModal.html('');
                notFoundEmail = $("<p>").text("가입 정보가 없습니다.");
                findUserEmailModal.append(notFoundEmail);
                $(".modal").show();
                console.log("find failed");
            } else {
                findUserEmailModal.html('');
                // 찾은 이메일 for문으로 modal에 나타내기
                for(let i = 0; i < response.length; i++) {
                    var userEmail = response[i];
                    var userEmails = $("<p>").text(userEmail);
                    findUserEmailModal.append(userEmails);
                };
                $(".modal").show();
                console.log("find success");
            };
          }
    });
});

// X버튼 또는 확인버튼 누르면 modal close
// class가 'btn-close'인 요소 클릭 시 모달 창 닫기
$('.btn-close').click(function() {
  $('.modal').hide();
});

// id가 'findEmailModal-btn'인 요소 클릭 시 모달 창 닫고 추가 작업 수행
$('#findEmailModal-btn').click(function() {
  $('.modal').hide();
});


// modal의 비밀번호 찾기 버튼
$('#findPw').click(function() {
    $('.modal').hide();
    var pwRadioButton = document.querySelector('input[name="find"][value="pw"]');
    pwRadioButton.checked = true;
    $('#formFindId').hide();
    $('#formFindPw').show();
});


// 비밀번호 찾기
const formFindPw = document.getElementById("formFindPw");
 const findPw = $("#findPw-btn").val();

formFindPw.addEventListener("submit", event => {
  event.preventDefault();

  const userEmail = $("#userEmail").val();

  $.ajax({
            type: 'POST',
            url: "/find/member/pw",
            data: {
              userEmail: userEmail,
             },
            success: function (response) {
                alert(response);
            }
      });
  });

// 비밀번호 재발급 후 main으로
$("#goMain").on("click", function() {
    window.location.href="/";
});

