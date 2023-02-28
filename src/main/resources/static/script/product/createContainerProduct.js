// const SingleProduct = document.querySelector('.ContainerProduct');
// const productNumber = urlParams.get('num');


function LinkToInvestingProduct(api) {
  const investingURL = `/invest/?num=${api}`
  window.location.href = investingURL;
}

function createSingleProductHtml(api) {
  SingleProduct.innerHTML = `
  <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture">
          <h2>${api.seqId}제품 사진</h2>
        </div>
        <div class="ProductInvestmentAmount">
          <h5>${api.totalPrice} 투자 목표액</h5>
        </div>
        <div class="ProductInformation">
        <h5>${api.information}제품 제목 설명</h5>
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <h5>${api.productName}제품 상세 설명</h5>
        <h5>즐겨찾기</h5>
      </div>
      <div class="ProductLimitDate">
       <h5>${api.period}투자 마감일</h5>
      </div>
      <div class="ProductPercentage">
        <h5>${Math.round(api.totalPrice / api.investPrice)}투자율</h5>
      </div>
      <div class="ProductInvesting">
          <input type=button onclick="LinkToInvestingProduct(${api.id})" value=${api.investPrice}개인 투자 금액 결제페이지 이동>
      </div>
    </div>
  `
}

function createSingleProduct() {
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => createSingleProductHtml(data));
}

createSingleProduct();


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




