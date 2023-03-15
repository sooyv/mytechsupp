

function createInvestingProduct(api) {
  investProductContent.innerHTML = `
  <div class="ProductListPicture">
    <h5>${api.seqId}제품 사진</h5>
    <img src="http://localhost:8080/file/product/product_${api.seqId}.png" style="max-width:80%; min-height:100px;"/>
  </div>
  <div class="ProductListInformation">
    <h5 class="ProductName">제품 제목 ${api.productName}</h5>
    <div class="ProductListInvestment-ProductListLimitDate">
      <h5>개인 투자 금액 ${api.investPrice}원 </h5>
      <input class = "Payment" type="hidden" value = "${api.investPrice}">
      <h5>투자 마감일 ${api.period}</h5>
    </div>
  </div>
  <div class="ProductListPercentage-ProductListWish">
    <h5>투자율 ${Math.round(api.totalPrice / api.investPrice)}%</h5>
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