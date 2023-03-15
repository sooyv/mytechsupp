function checkPass() {
	var questionId = $('input[name=questionId]').val();
	var questionPass = $('input[name=questionPass]').val();

	$.ajax({
		type: 'GET',
		url: '/notice/question-check/getPass',
		data : {questionId,questionPass},
		success: function(result) {
			if(result){
			    alert("비밀번호가 일치합니다.");
				location.href="/notice/question-list/"+questionId;
			}
			else{
				alert("비밀번호가 일치하지않습니다");
			}
		}
		,
		error: function(result) {
		},
		complete: function() {
		}

	})
}