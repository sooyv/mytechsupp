const feedbackSpecificContainer = document.querySelector('.FeedbackWritenByUserList');

function createFeedbackSpecificList() {
  feedbackSpecificContainer.innerHTML = ``;
  for (let i = 0; i < 6; i++) {
    feedbackSpecificContainer.innerHTML += `
    <article class="FeedbackWritenByUser">
      <div class="FeedbackPictureByUser">
        <h3>투자 제품 수령 사진(고객 업로드)</h3>
      </div>
      <div class="FeedbackScore-FeedbackUser">
        <h3>고객 점수</h3>
        <h3>고객 아이디</h3>
      </div>
      <div class="FeedbackText">
        <h3>피드백 텍스트</h3>
      </div>
    </article>
    `
  }
}

createFeedbackSpecificList();