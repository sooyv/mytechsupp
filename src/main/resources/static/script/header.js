alert("header.js")

  $('#logout-btn').click(function(event) {
    event.preventDefault(); // 기본 이벤트 제거
    $.ajax({
      url: '/member/logout', // 로그아웃 URL
      type: 'POST',
      success: function(data) {
        alert("로그아웃되었습니다."); // 로그아웃 성공 메시지
        window.location.href = '/'; // 페이지 이동
      },
      error: function(xhr, status, error) {
        console.error(xhr.responseText); // 에러 로그 출력
      }
    });
  });

