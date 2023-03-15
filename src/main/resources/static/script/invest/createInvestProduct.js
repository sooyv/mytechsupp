

function createInvestingProduct(api) {
  investProductContent.innerHTML = `
  <div class="ProductListPicture">
    <img src="http://localhost:8080/file/product/product_${api.seqId}.png" style="max-width:80%; min-height:100px;"/>
  </div>
  <div class="ProductListInformation">
    <div class="ProductName-ProductInvestment">
      <h5>제품명 : ${api.productName}</h5>
      <h5>현재 투자율 : ${Math.round((api.investPrice * api.paymentCount) / api.totalPrice * 100)}%</h5>
    </div>
    <div class="ProductListInvestment-ProductListLimitDate">
      <h5>개인 투자 금액 : ${api.investPrice}원 </h5>
      <input class = "Payment" type="hidden" value = "${api.investPrice}">
      <h5>투자 마감일 : ${api.period}</h5>
    </div>
  </div>
  <div class="ProductListPercentage-ProductListWish">
    
  </div>
  `
}

function createSingleInvestingProduct(productNumber) {
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => createInvestingProduct(data));
}

createSingleInvestingProduct(productNumber);