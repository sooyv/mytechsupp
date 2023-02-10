// 메인 페이지에 html 태그를 생성하는 script
const productList = document.querySelector('.ContainerProductList');

function createProductList(api) {
  productList.innerHTML = ``;
  console.log(api);
  for(let i = 0; i < 5; i++) {
    productList.innerHTML += `<article class="ProductContent">
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

// 이제 api를 요청해서 그거를 담아주는 걸 짜야함

// 테스트
function createFiveProduct(value) {
  fetch(`/productMain/${value}`)
  .then(response => response.json())
  .then(data => createProductList(data))
}


createFiveProduct(0);



// 콘솔 확인용
// function getOneItemFromSpring() {
//   fetch("/api/1")
//   .then(response => response.json())
//   .then(data => console.log(data))
// }