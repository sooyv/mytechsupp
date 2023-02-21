function linkToSelectedProduct(api) {
  let productSelected = `/productSelect/product/?num=${api}`;
  window.location.href = productSelected;
};

function createProductList(api) {
  productList.innerHTML = ``;
  for(let i = 0; i < 5; i++) {
    productList.innerHTML += `<article class="ProductContent" onclick="linkToSelectedProduct(${api[i].id})">
    <div class="ProductListPicture">
    <h2>${api[i].picture}제품 사진</h2>
    </div>
    <div class="ProductListInformation">
    <h2>${api[i].product}제품 제목</h2>
    <div class="ProductListInvestment-ProductListLimitDate">
    <h3>${api[i].investment}개인 투자 금액</h3>
    <h3>${api[i].limitdate}투자 마감일</h3>
    </div>
    </div>
    <div class="ProductListPercentage-ProductListWish">
    <h3>${api[i].percent}투자율</h3>
    <h3>${api[i].wish}즐겨찾기</h3>
    </div>
    </article>`
  }
}

function createFiveProduct(orderNumber, keyword) {
  fetch(`/api/products/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => createProductList(data));
}
createFiveProduct(orderNumber, keyword);



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