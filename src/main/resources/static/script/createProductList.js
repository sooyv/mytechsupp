// 메인 페이지에 html 태그를 생성하는 script
const productList = document.querySelector('.ContainerProductList');

// 구현해야하는 기능은 선택한 상품의 상세 정보 페이지로 이동하는것
// 현재 구현된 것은 productSelect 링크로 가게 하는 것 까지만 구현
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



// 이제 api를 요청해서 그거를 담아주는 걸 짜야함

// 테스트
function createFiveProduct() {
  const url = location.href;
  const urlParams = new URL(url).searchParams;
  const pageNumber = urlParams.get('order');
  fetch(`/api/products/${pageNumber}`)
  .then(response => response.json())
  .then(data => createProductList(data));
}
createFiveProduct();



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