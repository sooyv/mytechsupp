const investFrom = document.querySelector('.ContainerUserInput');

function InvestForm(api) {
  investFrom.innerHTML = `
  <form class="InvestForm" action="">
    <h5>아이디</h5>
    <input type="email" class="User_email" value="${api.userEmail}" name="user_email">
    
    <h5>휴대폰 번호</h5>
    <input type="tel" class = "User_phone" value="${api.userPhone}" name="user_phone">
    
    <div class="KakaomapContainer">
      <div class="Address">
        <h5>주소</h5>
        <input type="text" id="sample4_postcode" placeholder="우편번호" name="zip_code">
        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">        
      </div>
      <div class="RoadDetailAddress">
        <input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="street_addr">
        <input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detail_addr">
      </div>
      <div class = "PaymentContainer">
        <h6>카드 현금</h6>
        <div class = "PaymentRadio">
          <input type="checkbox" name="payment" onclick="checkBox(this)" value = "card" placeholder="카드">
          <input type="checkbox" name="payment"  onclick="checkBox(this)" value = "cash" placeholder="현금">
        </div>
    </div>
    </form>
  `
}

function createInvestForm() {
  fetch(`/api/userinformation`)
  .then(response => response.json())
  .then(data => InvestForm(data))
}

createInvestForm();

