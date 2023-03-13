// const feedbackList = document.querySelector('.ContainerFeedbackList');
// 이거는 product 에서 긁어 온것
function linkToSelectedFeedback(api) {
  let feedbackSelected = `/feedbackSelect/feedback/?num=${api}`;
  window.location.href = feedbackSelected;
};

function createFeedbackList(api) {
  feedbackList.innerHTML = ``;
  for(let i = 0; i < 5; i++) {
    feedbackList.innerHTML += `
    <article class="FeedbackContent" onclick="linkToSelectedFeedback(${api[i].id})">
      <div class="FeedbackListPicture">
       <h5>${api[i].seqId}피드백 사진</h5>
       <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
      </div>
      <div class="FeebackListInformation">
        <div class="FeedbackName-FeedbackInformation">
          <h5>피드백 제품 제목${api[i].productName}</h5>
          <h5>피드백 제품 설명${api[i].information}</h5>
        </div>
        <div class="FeedbackInvestPrice-FeedbackPeriod-FeedbackProductStatus">
          <h5>개인 투자 금액 ₩ ${api[i].investPrice}</h5>
          <h5>투자 마감일${api[i].period}</h5>
          <h5>투자성공/실패${api[i].productStatus}</h5>
        </div>
      </div>
    </article>`
  }
}


function createFiveFeedback(orderNumber, keyword) {
  fetch(`/api/feedbacks/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => createFeedbackList(data), console.log(data))
}
createFiveFeedback(orderNumber, keyword);


// function createFiveProduct(orderNumber, keyword) {
//   fetch(`/api/feedbacks/product?page=0&order=0&keyword=null`)
//   .then(response => response.json())
//   .then(data => console.log(data));
// }
// createFiveProduct(0, null);