$('#password').keypress(function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    $('#checkPassword').click();
  }
});


$('#checkPassword').click(function(e) {
    e.preventDefault();
    const checkPassword = $('#password').val();

        if (!checkPassword || checkPassword.trim() === "") {
            alert("비밀번호를 입력하세요.");
        } else {
            $.ajax({
                type: 'POST',
                url: '/user/checkpassword',
                data: {'checkPassword': checkPassword},
                datatype: "json"
            }).done(function(resultPath) {
                console.log("Success: " + resultPath);
                window.location.href = resultPath;
            }).fail(function(error) {
                console.log("Error: " + JSON.stringify(error));
                alert("비밀번호가 맞지 않습니다.");
                window.location.reload();
            });
        }
    });


