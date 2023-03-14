const feedbackSpecificContainer = document.querySelector('.FeedbackWritenByUserList');

function feedbackSpecificProductList(api) {
  feedbackSpecificContainer.innerHTML = ``;
  for (let i = 0; i < api.length -1; i++) {
    feedbackSpecificContainer.innerHTML += `
    <article class="FeedbackWritenByUser">
      <div class="FeedbackPictureByUser">
      <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
      </div>
      <div class="FeedbackScore-FeedbackUser">
        <h3>고객 아이디 ${api[i].userId}</h3>  
        <h3>고객 점수 ${api[i].score}</h3>
      </div>
      <div class="FeedbackText">
        <h3>피드백 텍스트 ${api[i].feedbackText}</h3>
      </div>
    </article>
    `
  }
}


function createSpecificFeedbackList() {
  fetch(`/api/feedback/specificlist/?num=${productNumber}`)
  .then(res => res.json())
  .then(data => feedbackSpecificProductList(data))
}

createSpecificFeedbackList();
