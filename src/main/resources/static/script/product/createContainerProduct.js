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

function wish(wishId, id) {
  if(wishId != 'null') { 
    console.log("delete :" +id)
  fetch(`http://localhost:8080/api/wish/delete/?num=${+id}`)
  .then(response => response.json())
  .then(data => {createSingleProduct(id) });
  } else {
    console.log("post: " +id)
    fetch(`http://localhost:8080/api/wish/post/?num=${+id}`)
  .then(response => response.json())
  .then(data => {createSingleProduct(id) });
  }
}

function wishCheck(wishId, id) {
  if (wishId == "0") {
    alert("로그인 후 추가 가능합니다")
    window.location.href = "/login";
  } else {
    wish(wishId, id)
  }
}

function createSingleProductHtml(api) {
  SingleProduct.innerHTML = `
  <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture">
          <img src="http://localhost:8080${api.imgUrl}" style="max-width:80%; min-height:100px;"/>
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
        <input type=button onclick="wishCheck('${api.wishId}',  '${api.id}')" value="즐겨찾기">
      </div>
      <div class="ProductLimitDate">
       <h5>투자 마감일 : ${api.period}</h5>
      </div>
      <div class="ProductPercentage">
        <h5>현재 투자율 : ${Math.round(api.totalPrice / api.investPrice)} % </h5>
      </div>
      <div class="ProductInvesting">
          <input type=button onclick="LinkToInvestingProduct(${api.id}, '${api.paylog}')" value=" ₩ ${api.investPrice} 투자하기">
      </div>
    </div>
  `
}

function createSingleProduct(productNumber) {
  fetch(`http://localhost:8080/api/product/?num=${productNumber}`)
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




