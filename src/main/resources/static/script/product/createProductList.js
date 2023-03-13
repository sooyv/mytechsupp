function linkToSelectedProduct(api) {
  let productSelected = `/productSelect/product/?num=${api}`;
  window.location.href = productSelected;
};

function linkBackToProductMain() {
  let productMain = `/productMain/product?page=${pageNumber}&order=${orderNumber}`;
  window.location.href = productMain;
}


function wish(wishId, id) {
  if(wishId != 'null') { 
    console.log("delete :" +id)
  fetch(`http://localhost:8080/api/wish/delete/?num=${+id}`)
  .then(response => response.json())
  .then(data => {linkBackToProductMain() });
  } else {
    console.log("post: " +id)
    fetch(`http://localhost:8080/api/wish/post/?num=${+id}`)
  .then(response => response.json())
  .then(data => {linkBackToProductMain() });
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

function createProductList(api) {
  productList.innerHTML = ``;
  for(let i = 0; i < 5; i++) {
    productList.innerHTML += `
    <article class="ProductContent">
      <div class="ProductListPicture">
        <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
      </div>
      <div class="ProductListInformation">
        <h5>제품 제목 ${api[i].productName}</h5>
        <div class="ProductListInvestment-ProductListLimitDate">
          <input class = "ProductPay" type=button onclick=linkToSelectedProduct(${api[i].id}) value="투자하기">
          <h5>개인 투자 금액 ₩ ${api[i].investPrice}</h5>
          <h5>투자 마감일 ${api[i].period}</h5>
        </div>
        <div class="ProductListPercentage-ProductListWish">
          <h5>투자율 ${Math.round( (api[i].investPrice * api[i].paymentValue) / api[i].totalPrice * 100)}%</h5>
          <input type=button onclick = "wishCheck('${api[i].wishId}' , '${api[i].id}' )" value="즐겨찾기">
          <h5>즐겨찾기</h5>
        </div>
      </div>
    </article>`
  }
}

function createFiveProduct(pageNumber, orderNumber, keyword) {
  fetch(`/api/products/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => (createProductList(data), console.log(data)));
}
createFiveProduct(pageNumber, orderNumber, keyword);



// // 콘솔 확인용
// function getOneItemFromSpring() {
  //   fetch(`/api/products/${value}`)
  //   .then(response => response.json())
  //   .then(data => console.log(data))
  // }
  
  // 선택한 클래스에 이벤트 리스너 전부 적용하는 함수
  // article에 click 시 이동하는 걸로 바꿔서 현재는 사용 x (02/14)
  // function addEventToProductContent(api) {
  //   const productContentAll = document.querySelectorAll('.ProductContent');
  //   for(let i = 0; i < productContentAll.length; i++) {
  //     productContentAll[i].addEventListener('click', linkToSelectedProduct(api[i].id));
  //   }
  // };



