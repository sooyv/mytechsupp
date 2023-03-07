const investFrom = document.querySelector('.ContainerUserInput');

function createInvestForm() {
  investFrom.innerHTML = `
  <form class="InvestForm" action="">
    아이디
    <input type="email" class="User_email" placeholder="아이디" name="user_email">
    휴대폰 번호
    <input type="tel" class = "User_phone" placeholder="휴대폰 번호" name="user_phone">
    <div class="KakaomapContainer">
      <div class="Address">
        주소
        <input type="text" id="sample4_postcode" placeholder="우편번호" name="zip_code">
        <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기">
      </div>
      <div class="RoadDetailAddress">
        <input type="text" id="sample4_roadAddress" placeholder="도로명주소" name="street_addr">
        <input type="text" id="sample4_detailAddress" placeholder="상세주소" name="detail_addr">
      </div>
      <div class = "PaymentContainer">
        <input type="checkbox" name="payment" onclick="checkBox(this)" value = "card" placeholder="카드">
        <input type="checkbox" name="payment"  onclick="checkBox(this)" value = "cash" placeholder="현금">


      </div>
     </form>
  `
}

createInvestForm();