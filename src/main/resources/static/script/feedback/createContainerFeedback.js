

// 구현 해야하는 기능은 선택한 상품의 상세 정보 페이지로 이동하는것
// 현재 구현된 것은 feedbackSelect 링크로 가게 하는 것까지만 구현

// 피드백 작성 폼으로 넘어가는 것 (버튼에 들어가야함)
function linkToFeedbackWriteForm(id, paylog, status) {
  if (paylog == "n" && status == "SUCCESS") {
  let feedbackForm = `/feedbackSelect/feedback/form/?num=${id}`;
  window.location.href = feedbackForm; 
  } else if (status == "FAIL") {
    alert("후기 작성이 불가능한 상품 입니다")
  } else {
    alert("후기 작성은 투자하신 상품만 가능합니다")
  }
}

// 선택된 제품 하나 생성하는 function

async function createFeedbackSingleHTML(api) {
  try {
  singleFeedback.innerHTML = ``;
  singleFeedback.innerHTML = `
  <article class="FeedbackContent">
    <div class="FeedbackListPicture">
      <h4>제품 사진 ${api.seqId}</h4>
      <img src="http://localhost:8080${api.imgUrl}" style="max-width:80%; min-height:100px;"/>
    </div>
    <div class="FeebackListInformation">
      <div class="FeedbackListName-FeedbackListInformation">
        <h5>제품 제목 ${api.productName}</h5>
        <h5>제품 설명 ${api.information}</h5>
      </div>
      <div class="FeedbackListScore-LimitDate-SuccesStatus">
        <h5 class = "FeedbackScore">상품 점수 다른 테이블에서 가져와야함</h5>
        <h5>투자 마감일 ${api.period}</h5>
        <h5>투자 성공 여부</h5>
        <h5 class = "FeedbackStatus">${api.productStatus}</h5>
      </div>
    </div>
  </article>
  `;} catch {
    console.log("create single html fail")
  } finally {
    const productStatus = document.querySelector('.FeedbackStatus');
    console.log(productStatus);
    createSpecificFeedbackList(productStatus);
  }
}

function createFeedbackWriteButton(api) {
  if (writeFormButton != null ) {
  writeFormButton.innerHTML = ``;
  writeFormButton.innerHTML = `
  <input type="button" onclick = "linkToFeedbackWriteForm(${api.id}, '${api.paylog}', '${api.productStatus}')" value="후기 작성하러 가기">
  `}
}

function createSingleFeedback() {
  fetch(`/api/product/?num=${productNumber}`)
  .then(res => res.json())
  .then(data => (createFeedbackSingleHTML(data), createFeedbackWriteButton(data)))
}



