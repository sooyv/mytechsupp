// const feedbackList = document.querySelector('.ContainerFeedbackList');
// 이거는 product 에서 긁어 온것
function linkToSelectedFeedback(api) {
  let feedbackSelected = `/feedbackSelect/feedback/?num=${api}`;
  window.location.href = feedbackSelected;
};

async function createFeedbackList(api) {
  try {
    feedbackList.innerHTML = ``;
    for(let i = 0; i < api.length; i++) {
      feedbackList.innerHTML += `
      <article class="FeedbackContent" onclick="linkToSelectedFeedback(${api[i].id})">
        <div class="FeedbackListPicture">
        <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="FeebackListInformation">
          <div class="FeedbackName-FeedbackInformation">
            <h5>제품명: ${api[i].productName}</h5>
            <h5>제품 설명: ${api[i].information}</h5>
          </div>
          <div class="FeedbackInvestPrice-FeedbackPeriod-FeedbackProductStatus">
            <h5>개인 투자 금액:  ₩ ${api[i].investPrice}</h5>
            <h5>마감일 : ${api[i].period}</h5>
            <h5>투자성공/실패 :${api[i].productStatus}</h5>
          </div>
        </div>
      </article>`
    }
  } catch {
    
    feedbackList.innerHTML = ``;
    for(let i = 0; i < api.length; i++) {
      feedbackList.innerHTML += `
      <article class="FeedbackContent" onclick="linkToSelectedFeedback(${api[i].id})">
        <div class="FeedbackListPicture">
        <img src="http://localhost:8080/file/default/default.png" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="FeebackListInformation">
          <div class="FeedbackName-FeedbackInformation">
            <h5>제품명: ${api[i].productName}</h5>
            <h5>제품 설명: ${api[i].information}</h5>
          </div>
          <div class="FeedbackInvestPrice-FeedbackPeriod-FeedbackProductStatus">
            <h5>개인 투자 금액:  ₩ ${api[i].investPrice}</h5>
            <h5>마감일 : ${api[i].period}</h5>
            <h5>투자성공/실패 :${api[i].productStatus}</h5>
          </div>
        </div>
      </article>`

    }
  }
  

  
}


function createFiveFeedback(pageNumber, orderNumber, keyword) {
  fetch(`/api/feedbacks/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => createFeedbackList(data))
}
createFiveFeedback(pageNumber, orderNumber, keyword);


// function createFiveProduct(orderNumber, keyword) {
//   fetch(`/api/feedbacks/product?page=0&order=0&keyword=null`)
//   .then(response => response.json())
//   .then(data => console.log(data));
// }
// createFiveProduct(0, null);