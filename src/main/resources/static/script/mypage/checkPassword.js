$('#password').keypress(function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    $('#checkPassword').click();
  }
});


$('#checkPassword').click(function(e) {
        e.preventDefault();
        const checkPassword = $('#password').val();
        if(!checkPassword || checkPassword.trim() === ""){
            alert("비밀번호를 입력하세요.");
        } else{
            $.ajax({
                type: 'POST',
                url: '/user/checkpassword',
                data: {'checkPassword': checkPassword},
                datatype: "text"
            }).done(function(result){
                console.log(result);
                if(result){
                    console.log("비밀번호 일치");
                    window.location.href="/user/edituser";
                } else{
                    console.log("비밀번호 틀림");
                    // 비밀번호가 일치하지 않으면
                    alert("비밀번호가 맞지 않습니다.");
                    window.location.reload();
                };
            }).fail(function(error){
                console.log(JSON.stringify(error));
            })
        }
    });


