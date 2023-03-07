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
          <h2>제품 사진 ${api.seqId}</h2>
          <img src="http://localhost:8080/file/product/product_${api.seqId}.png" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="ProductInvestmentAmount">
          <h5> 투자 목표액 ₩ ${api.totalPrice}</h5>
        </div>
        <div class="ProductInformation">
        <h5>제품 제목 설명 ${api.information}</h5>
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <h5>제품 상세 설명 ${api.productName}</h5>
        <h5>즐겨찾기</h5>
      </div>
      <div class="ProductLimitDate">
       <h5>투자 마감일 ${api.period}</h5>
      </div>
      <div class="ProductPercentage">
        <h5>투자율 ${Math.round(api.totalPrice / api.investPrice)} % </h5>
      </div>
      <div class="ProductInvesting">
          <input type=button onclick="LinkToInvestingProduct(${api.id})" value="${api.investPrice}원 개인 투자 금액 결제페이지 이동">
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




