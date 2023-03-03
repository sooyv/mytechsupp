function linkToSelectedProduct(api) {
  let productSelected = `/productSelect/product/?num=${api}`;
  window.location.href = productSelected;
};

function createProductList(api) {
  productList.innerHTML = ``;
  for(let i = 0; i < 5; i++) {
    productList.innerHTML += `
    <article class="ProductContent" onclick="linkToSelectedProduct(${api[i].id})">
      <div class="ProductListPicture">
        <img src="http://localhost:8080/file/product/product_${api[i].seqId}.png" style="max-width:80%; min-height:100px;"/>
      </div>
      <div class="ProductListInformation">
        <h5>${api[i].productName}제품 제목</h5>
        <div class="ProductListInvestment-ProductListLimitDate">
          <h5>${api[i].investPrice}개인 투자 금액</h5>
          <h5>${api[i].period}투자 마감일</h5>
        </div>
        <div class="ProductListPercentage-ProductListWish">
          <h5>${Math.round(api[i].totalPrice / api[i].investPrice)}투자율</h5>
          <h5>즐겨찾기</h5>
        </div>
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