

function createInvestingProduct(api) {
  investProductContent.innerHTML = `
  <div class="ProductListPicture">
    <h2>${api.picture}제품 사진</h2>
  </div>
  <div class="ProductListInformation">
    <h2>${api.product}제품 제목</h2>
    <div class="ProductListInvestment-ProductListLimitDate">
      <h3>${api.investment}개인 투자 금액</h3>
      <h3>${api.limitdate}투자 마감일</h3>
    </div>
  </div>
  <div class="ProductListPercentage-ProductListWish">
    <h3>${api.percent}투자율</h3>
    <h3>${api.wish}즐겨찾기</h3>
  </div>
  `
}

function createSingleInvestingProduct(productNumber) {
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => createInvestingProduct(data));
}

createSingleInvestingProduct(productNumber);