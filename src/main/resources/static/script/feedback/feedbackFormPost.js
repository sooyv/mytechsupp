function testFormInput () {
  let form = document.querySelector('.FeedbackForm');
  let scoreList = document.getElementsByName('score');
  let image = document.querySelector('.FeedbackImage');
  let text = document.querySelector('.FeedbackText');
  let formData = new FormData(form);
  let score = 0;

 
  scoreList.forEach((node) => { if(node.checked){score = (node.value)}});


  formData.append("score", score);
  formData.append("image", $(image)[0].files[0]);
  formData.append("text", $(text)[0]);

  console.log(formData.get("score"));
  console.log(formData.get("image"));
  console.log(formData.get("text"));

  // set / get 해야함

  $.ajax({
    url: "/api/feedback/post",
    processData: false,
    contentType: false,
    data: formData,
    type: 'POST',
    success: function(result){
      console.log(result);
        alert("업로드 성공!!");
    }
    });
}

function createSubmit () {
const feedbacksubmit = document.querySelector('.FeedbackSubmit');
feedbacksubmit.innerHTML = ``;
feedbacksubmit.innerHTML = `
<input type="button" onclick = "testFormInput()" value="작성하기">
`;
}

createSubmit();