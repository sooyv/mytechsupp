

// 구현 해야하는 기능은 선택한 상품의 상세 정보 페이지로 이동하는것
// 현재 구현된 것은 feedbackSelect 링크로 가게 하는 것까지만 구현

// 피드백 작성 폼으로 넘어가는 것 (버튼에 들어가야함)
function linkToFeedbackWriteForm(api) {
  let feedbackForm = `/feedbackSelect/feedback/form/?num=${api}`;
  window.location.href = feedbackForm;
}

// 선택된 제품 하나 생성하는 function

function createFeedbackSingleHTML(api) {
  singleFeedback.innerHTML = ``;
  singleFeedback.innerHTML = `
  <article class="FeedbackContent">
    <div class="FeedbackListPicture">
      <h4>${api.seqId}제품 사진</h4>
    </div>
    <div class="FeebackListInformation">
      <div class="FeedbackListName-FeedbackListInformation">
        <h5>${api.productName}제품 제목</h5>
        <h5>${api.information}제품 설명</h5>
      </div>
      <div class="FeedbackListScore-LimitDate-SuccesStatus">
        <h5>상품 점수 다른 테이블에서 가져와야함</h5>
        <h5>${api.period}투자 마감일</h5>
        <h5>${api.productStatus}투자성공여부</h5>
      </div>
    </div>
  </article>
  `;
}

function createFeedbackWriteButton(api) {
  if (writeFormButton != null ) {
  writeFormButton.innerHTML = ``;
  writeFormButton.innerHTML = `
  <input type="button" onclick = "linkToFeedbackWriteForm(${api.id})" value="후기 작성하러 가기">
  `}
}

function createSingleFeedback() {
  fetch(`/api/product/?num=${productNumber}`)
  .then(res => res.json())
  .then(data => (createFeedbackSingleHTML(data), createFeedbackWriteButton(data)))

}

createSingleFeedback();