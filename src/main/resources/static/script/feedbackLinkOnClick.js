const feedbackContentAll = document.querySelectorAll('.FeedbackContent');

// 구현 해야하는 기능은 선택한 상품의 상세 정보 페이지로 이동하는것
// 현재 구현된 것은 feedbackSelect 링크로 가게 하는 것까지만 구현
function linkToSelectedFeedback() {
  const feedbackSelected = './feedbackSelect';
  window.location.href = feedbackSelected;
}

function addEventToFeedbackContent() {
  for(let i = 0; i < feedbackContentAll.length; i++) {
    feedbackContentAll[i].addEventListener('click', linkToSelectedFeedback);
  }
}

addEventToFeedbackContent();