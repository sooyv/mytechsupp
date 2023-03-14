

async function feedbackSpecificProductList(api, status) {
  try {
    feedbackSpecificContainer.innerHTML = ``;
    let string = status.innerText;
    if (string != 'FAIL') {
      if (api.length != 0){
        for (let i = 0; i < api.length -1; i++) {
          feedbackSpecificContainer.innerHTML += `
          <article class="FeedbackWritenByUser">
            <div class="FeedbackPictureByUser">
            <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
            </div>
            <div class="FeedbackScore-FeedbackUser">
              <h5>고객 아이디 ${api[i].userId}</h5>  
              <h5 >고객 점수</h5>
              <h5 class="Score"> ${api[i].userId} </h5>
            </div>
            <div class="FeedbackText">
              <h3>피드백 텍스트 ${api[i].feedbackText}</h3>
            </div>
          </article>
          `
        }
      } else {
        feedbackSpecificContainer.innerHTML += `
      <article class="FeedbackWritenByUser">
        <div class="FeedbackPictureByUser">
        <img src="http://localhost:8080/file/default/fail.png" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="FeedbackScore-FeedbackUser">
          <h3>작성된 상품 후기가 없습니다.</h3>  
          
        </div>
        <div class="FeedbackText">
          <h3>후기를 작성해 주세요</h3>
        </div>
      </article>
      `
      }
    } else {
      feedbackSpecificContainer.innerHTML += `
      <article class="FeedbackWritenByUser">
        <div class="FeedbackPictureByUser">
        <img src="http://localhost:8080/file/default/investfail.png" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="FeedbackScore-FeedbackUser">
          <h3>투자에 실패한 상품 입니다</h3>  
        </div>
        <div class="FeedbackText">
          <h3>투자에 실패한 상품은 후기 작성이 불가능합니다</h3>
        </div>
      </article>
      `
    }
  } catch {
    console.log("fail to create list")
  } finally {
    console.log("create list complete")
  }
}

async function averageScoreOfFeedback(api) {
  try {
    let score = document.querySelectorAll('.Score');
    let statusForFunction = document.querySelector('.FeedbackStatus');
    let totalScore = 0;
    if (statusForFunction == 'FAIL') {
      score.textContent = "0";
    } else {
      if (score.length != 0 ){
        for (let i = 0; i < api.length -1; i++) {
        totalScore += api[i].score;
        }
        let AveageScore = Math.round(totalScore / api.length); 
        score.textContent = `${AveageScore}
        `;
        console.log("#####" + AveageScore)
      } else {
        score.textContent = "0";
      }
    }
    
  } catch {
    console.log("fail to get average score")
  } finally {
    console.log("create average score")
  }
}



function createSpecificFeedbackList(status) {
  fetch(`/api/feedback/specificlist/?num=${productNumber}`)
  .then(res => res.json())
  .then(data => feedbackSpecificProductList(data, status))
  .then(data => (averageScoreOfFeedback(data)))
}


createSingleFeedback();


