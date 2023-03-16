function FeedbackFormInput() {
  let form = document.querySelector('.FeedbackForm');
  let scoreList = document.getElementsByName('score');
  let feedbackImage = document.querySelector('.FeedbackImage');
  let feedbackText = document.querySelector('.FeedbackText');
  let feedbackScore = 0;
  let formData = new FormData();

 
  scoreList.forEach((node) => { if(node.checked){feedbackScore = (node.value)}});

  let alertMessage = "빈 입력란이 존재합니다." + "\n\n";

  if (feedbackScore == '') {
    alertMessage += "점수" + "\n";
  }

  if (feedbackText.value == '') {
    alertMessage += "피드백 작성란" + "\n";
  }

  if (feedbackImage.value == '') {
    alertMessage += "이미지 첨부"
  }

  if (alertMessage != "빈 입력란이 존재합니다.\n\n") {
    alert(alertMessage);
  } else {
    console.log("fetch start")

    formData.append("score", feedbackScore);
    formData.append("image", $(feedbackImage)[0].files[0]);
    formData.append("text",  feedbackText.value);
    formData.append("num", productNumber);
    

    fetch(`/api/feedback/post/?num=${productNumber}`, {
      method: "POST",
      body: formData
    })
      .then(res => res.json())
      .then(alert("소중한 피드백에 감사드립니다"))
      .then(returnToFeedbackSelect())
  }
}
  

function returnToFeedbackSelect() {
  let feedbackSelected = `/feedbackSelect/feedback/?num=${productNumber}`;
  window.location.href = feedbackSelected;
}
  

function createSubmit () {
const feedbacksubmit = document.querySelector('.FeedbackSubmit');
feedbacksubmit.innerHTML = ``;
feedbacksubmit.innerHTML = `
<input type="button" onclick = "FeedbackFormInput()" value="작성하기">
`;
}

createSubmit();