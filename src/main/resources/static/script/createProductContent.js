const productList = document.querySelector('.ContainerProductList');

function createProductList() {
  productList.innerHTML = ``;

  for(let i = 0; i < 4; i++) {
    productList.innerHTML += `<article class="ProductContent">
    <div class="ProductListPicture">
      <h2>제품 사진</h2>
    </div>
    <div class="ProductListInformation">
      <h2>제품 제목</h2>
      <div class="ProductListInvestment-ProductListLimitDate">
        <h3>개인 투자 금액</h3>
        <h3>투자 마감일</h3>
      </div>
    </div>
    <div class="ProductListPercentage-ProductListWish">
      <h3>투자율</h3>
      <h3>즐겨찾기</h3>
    </div>
  </article>`
  }
}

createProductList();
