const productOne = document.querySelector('.ContainerProduct');

function productOneCreate(api) {
  productOne.innerHTML = `
  <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture">
          <h2>${api.picture}제품 사진</h2>
        </div>
        <div class="ProductInvestmentAmount">
          <h3>${api.investment} 투자 목표액</h3>
        </div>
        <div class="ProductInformation">
        <h2>${api.product}제품 상세 설명</h2>
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <h2>${api.product}제품 상세 설명</h2>
        <h3>${api.wish}즐겨찾기</h3>
      </div>
      <div class="ProductLimitDate">
       <h3>${api.limitdate}투자 마감일</h3>
      </div>
      <div class="ProductPercentage">
        <h3>${api.percent}투자율</h3>
      </div>
      <div class="ProductInvesting">
        <h3>${api.investment} / 10 개인 투자 금액 결제페이지 이동</h3>
      </div>
    </div>
  `
}

function createProductOne() {
  const url = location.href;
  const urlParams = new URL(url).searchParams;
  const productNumber = urlParams.get('num');
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => productOneCreate(data));
}
createProductOne();


// // 콘솔에 찍는 용
// function createProductOne() {
//   const url = location.href;
//   const urlParams = new URL(url).searchParams;
//   const productNumber = urlParams.get('num');
//   console.log(url);
//   console.log(urlParams);
//   console.log(productNumber);
//   fetch(`/api/product/?num=${productNumber}`)
//   .then(response => response.json())
//   .then(data => console.log(data));
// }
// createProductOne();




