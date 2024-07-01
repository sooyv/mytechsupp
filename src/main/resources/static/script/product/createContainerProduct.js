// const SingleProduct = document.querySelector('.ContainerProduct');
// const productNumber = urlParams.get('num');


function LinkToInvestingProduct(id , paylog) {
  if(paylog == "n") {
   return alert("이미 투자한 상품 입니다.")
  } else {
  const investingURL = `/invest/?num=${id}`
  window.location.href = investingURL;
  }
}

function alertWishProductName(wishId , productName) {
  if(wishId != 'null'){
    alert(`${productName}이 즐겨찾기에서 삭제 되었습니다`)
  } else{
    alert(`${productName}이 즐겨찾기에 추가 되었습니다`)
  }
}

function wish(wishId , id, productName) {
  if(wishId != 'null') { 
  fetch(`/api/wish/delete/?num=${+id}`)
  .then(response => response.json())
  .then(alertWishProductName(wishId , productName))
  .then(data => {createSingleProduct(id) });
  } else {
    fetch(`/api/wish/post/?num=${+id}`)
  .then(response => response.json())
  .then(alertWishProductName(wishId , productName))
  .then(data => {createSingleProduct(id) });
  }
}

function wishCheck(wishId, id, productName) {
  if (wishId == "0") {
    alert("로그인 후 추가 가능합니다")
    window.location.href = "/login";
  } else {
    wish(wishId, id, productName)
  }
}

function createSingleProductHtml(api) {
  SingleProduct.innerHTML = `
  <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture">
          <img src="${api.imgUrl}" style="max-width:80%; min-height:100px;"/>
        </div>
        <div class="ProductInvestmentAmount">
          <h5> 목표 투자액 :  ₩ ${api.totalPrice}</h5>
        </div>
        <div class="ProductInformation">
        <h5>제품 제목 설명 : ${api.information}</h5>
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <h5>제품 설명 : ${api.productName}</h5>
        <input class="btn btn-outline-dark mt-auto" type=button onclick="wishCheck('${api.wishId}',  '${api.id}', '${api.productName}')" value="즐겨찾기">
      </div>
      <div class="ProductLimitDate">
       <h5>투자 마감일 : ${api.period}</h5>
      </div>
      <div class="ProductPercentage">
        <h5>현재 투자율 : ${Math.round((api.investPrice * api.paymentCount) / api.totalPrice * 100)} % </h5>
      </div>
      <div class="ProductInvesting">
          <input class="btn btn-lg btn-primary" type=button onclick="LinkToInvestingProduct(${api.id}, '${api.paylog}')" value=" ₩ ${api.investPrice} 투자하기">
      </div>
    </div>
  `
}

function createSingleProduct(productNumber) {
  fetch(`/api/product/?num=${productNumber}`)
  .then(response => response.json())
  .then(data => createSingleProductHtml(data));
}

createSingleProduct(productNumber);


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




