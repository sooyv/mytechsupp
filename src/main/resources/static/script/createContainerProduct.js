const productOne = document.querySelector('.ContainerProduct');

function productOneCreate(api) {
  productOne.innerHTML = `
  <div class="ContainerPictureInvestmentAmountInformation">
      <div>
        <div class="ProductPicture">
          <h2>${api[i].picture}제품 사진</h2>
        </div>
        <div class="ProductInvestmentAmount">
          <h3>${api[i].investment} 투자 목표액</h3>
        </div>
        <div class="ProductInformation">
        <h2>${api[i].product}제품 상세 설명</h2>
        </div>
      </div>
    </div>
    <div class="ContainerNameLimitDatePrecentageInvesting">
      <div class="ProductNameWish">
        <h2>${api[i].product}제품 상세 설명</h2>
        <h3>${api[i].wish}즐겨찾기</h3>
      </div>
      <div class="ProductLimitDate">
       <h3>${api[i].limitdate}투자 마감일</h3>
      </div>
      <div class="ProductPercentage">
        <h3>${api[i].percent}투자율</h3>
      </div>
      <div class="ProductInvesting">
        <h3>${api[i].investment} / 10 개인 투자 금액 결제페이지 이동</h3>
      </div>
    </div>
  `
}

function createProductOne(value) {
  fetch(`/productSelect/${value}`)
  .then(response => response.json())
  .then(data => productOneCreate(data));
}

createProductOne();