// const feedbackList = document.querySelector('.ContainerFeedbackList');
// 이거는 product 에서 긁어 온것
function linkToSelectedFeedback(api) {
  let feedbackSelected = `/feedbackSelect/feedback/?num=${api}`;
  window.location.href = feedbackSelected;
};


// function createFeedbackList() {
//   feedbackList.innerHTML = '';

//   for(let i = 0; i < 4; i++) {
//     feedbackList.innerHTML += `
//     <article class="FeedbackContent">
//       <div class="FeedbackListPicture">
//         <h2>제품 사진</h2>
//       </div>
//       <div class="FeebackListInformation">
//         <div class="FeedbackListName-FeedbackListInformation">
//           <h2>제품 제목</h2>
//           <h3>제품 설명</h3>
//         </div>
//         <div class="FeedbackListScore-LimitDate-SuccesStatus">
//           <h3>상품 점수</h3>
//           <h3>투자 마감일</h3>
//           <h3>투자성공여부</h3>
//         </div>
//       </div>
//     </article>
//     `;
//   }
// }

function createFeedbackList(api) {
  feedbackList.innerHTML = ``;
  for(let i = 0; i < 5; i++) {
    feedbackList.innerHTML += `
    <article class="FeedbackContent" onclick="linkToSelectedFeedback(${api[i].id})">
      <div class="FeedbackListPicture">
       <h5>${api[i].seqId}피드백 사진</h5>
      </div>
      <div class="FeebackListInformation">
        <div class="FeedbackName-FeedbackInformation>
          <h5>${api[i].productName}피드백 제품 제목</h5>
          <h5>${api[i].information}피드백 제품 설명</h5>
        </div>
        <div class="FeedbackListName-FeedbackListInformation">
          <h5>${api[i].investPrice}개인 투자 금액</h5>
          <h5>${api[i].period}투자 마감일</h5>
          <h5>${api[i].productStatus}투자성공/실패</h5>
        </div>
      </div>
    </article>`
  }
}




function createFiveFeedback(orderNumber, keyword) {
  fetch(`/api/feedbacks/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => createFeedbackList(data))
}
createFiveFeedback(orderNumber, keyword);


// function createFiveProduct(orderNumber, keyword) {
//   fetch(`/api/feedbacks/product?page=0&order=0&keyword=null`)
//   .then(response => response.json())
//   .then(data => console.log(data));
// }
// createFiveProduct(0, null);