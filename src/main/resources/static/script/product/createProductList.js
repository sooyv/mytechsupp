function linkToSelectedProduct(api) {
  let productSelected = `/productSelect/product/?num=${api}`;
  window.location.href = productSelected;
};

function linkBackToProductMain() {
  let productMain = `/productMain/product?page=${pageNumber}&order=${orderNumber}`;
  window.location.href = productMain;
}

function alertWishProductName(wishId , productName) {
  if(wishId != 'null'){
    alert(`${productName}이 즐겨찾기에서 삭제 되었습니다`)
  } else{
    alert(`${productName}이 즐겨찾기에 추가 되었습니다`)
  }
}

function wish(wishId, id, productName) {
  if(wishId != 'null') { 
    fetch(`http://localhost:8080/api/wish/delete/?num=${+id}`)
    .then(response => response.json())
    .then(alertWishProductName(wishId , productName))
    .then(data => {linkBackToProductMain() });
  } else {
    fetch(`http://localhost:8080/api/wish/post/?num=${+id}`)
    .then(response => response.json())
    .then(alertWishProductName(wishId , productName))
    .then(data => {linkBackToProductMain() });
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

function addLinkToDom(api) {
  const picture = document.querySelectorAll('.ProductListPicture');
  const name = document.querySelectorAll('.ProductName');
  const period = document.querySelectorAll('.ProductPeriod');
  const investPercent = document.querySelectorAll('.ProductInvestPercent');

  for (let i = 0; i < api.length;  i++) {
    picture[i].addEventListener("click", () => {linkToSelectedProduct(api[i].id)})
    name[i].addEventListener("click",() => {linkToSelectedProduct(api[i].id)})
    period[i].addEventListener("click", () => {linkToSelectedProduct(api[i].id)})
    investPercent[i].addEventListener("click", () => {linkToSelectedProduct(api[i].id)})
  }
} 




async function createProductList(api) {
  try {
    if (api.length != undefined){
      productList.innerHTML = ``;
      for(let i = 0; i < api.length; i++) {
          productList.innerHTML += `
          <article class="ProductContent">
            <div class="ProductListPicture">
              <img src="http://localhost:8080${api[i].imgUrl}" style="max-width:80%; min-height:100px;"/>
            </div>
            <div class="ProductListInformation">
              <h5 class="ProductName">제품명 :  ${api[i].productName}</h5>
              <div class="ProductListInvestment-ProductListLimitDate">
                <h5 class="ProductPeriod">투자 마감일: ${api[i].period}</h5>  
                <input class = "ProductPay btn btn-lg btn-primary" type=button onclick=linkToSelectedProduct(${api[i].id}) value="개인 투자 금액:  ₩ ${api[i].investPrice}">
              </div>
              <div class="ProductListPercentage-ProductListWish">
                <h5 class="ProductInvestPercent">투자율: ${Math.round( (api[i].investPrice * api[i].paymentValue) / api[i].totalPrice * 100)}%</h5>
                <input class="btn btn-outline-dark mt-auto" type=button onclick = "wishCheck('${api[i].wishId}' , '${api[i].id}' , '${api[i].productName}')" value="즐겨찾기">
              </div>
            </div>
          </article>`
        }
      } else {
        productList.innerHTML = ``;
      productList.innerHTML += `
          <article class="ProductContent">
            <h1>검색된 상품이 없습니다</h1>
          </article>`
      }
    } catch {
      console.log("fail to create Product list");
      
    } finally {
      try {
        addLinkToDom(api)
      } catch (error) {
        console.log("keyword undefined")
      }
    }
}


function createFiveProduct(pageNumber, orderNumber, keyword) {
  fetch(`/api/products/product?page=${pageNumber}&order=${orderNumber}&keyword=${keyword}`)
  .then(response => response.json())
  .then(data => {try {
    createProductList(data)
  } catch (error) {
    console.log("no list")
    createProductList(data)
  }});
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



