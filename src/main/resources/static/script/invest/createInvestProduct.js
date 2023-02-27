

function createInvestingProduct(api) {
  investProductContent.innerHTML = `
  <div class="ProductListPicture">
    <h5>${api.seqId}제품 사진</h5>
  </div>
  <div class="ProductListInformation">
    <h5>${api.productName}제품 제목</h5>
    <div class="ProductListInvestment-ProductListLimitDate">
      <h5>${api.investPrice}개인 투자 금액</h5>
      <h5>${api.period}투자 마감일</h5>
    </div>
  </div>
  <div class="ProductListPercentage-ProductListWish">
    <h5>${Math.round(api.totalPrice / api.investPrice)}투자율</h5>
    <h5>즐겨찾기</h5>
  </div>
  `
}

function createSingleInvestingProduct(productNumber) {
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => createInvestingProduct(data));
}

createSingleInvestingProduct(productNumber);