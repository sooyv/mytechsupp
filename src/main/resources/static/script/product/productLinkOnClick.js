const productContentAll = document.querySelectorAll('.ProductPay');


// 구현해야하는 기능은 선택한 상품의 상세 정보 페이지로 이동하는것
// 현재 구현된 것은 productSelect 링크로 가게 하는 것 까지만 구현
function linkToSelectedProduct() {
  const productSelected = '/productSelect';
  window.location.href = productSelected;
}

// 선택한 클래스에 이벤트 리스너 전부 적용하는 함수
function addEventToProductContent() {
  for(let i = 0; i < productContentAll.length; i++) {
    productContentAll[i].addEventListener('click', linkToSelectedProduct);
  }
}


//  여기 현재 안씀 나중에 지우든지 해야함