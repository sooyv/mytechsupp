const feedbackList = document.querySelector('.ContainerFeedbackList');

function createFeedbackList() {
  feedbackList.innerHTML = '';

  for(let i = 0; i < 4; i++) {
    feedbackList.innerHTML += `
    <article class="FeedbackContent">
      <div class="FeedbackListPicture">
        <h2>제품 사진</h2>
      </div>
      <div class="FeebackListInformation">
        <div class="FeedbackListName-FeedbackListInformation">
          <h2>제품 제목</h2>
          <h3>제품 설명</h3>
        </div>
        <div class="FeedbackListScore-LimitDate-SuccesStatus">
          <h3>상품 점수</h3>
          <h3>투자 마감일</h3>
          <h3>투자성공여부</h3>
        </div>
      </div>
    </article>
    `;
  }
}

createFeedbackList();