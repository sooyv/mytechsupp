
function LinkToInvestComplete() {
  const investCompleteURL = `/invest/complete/?num=${productNumber}`
  location.href = investCompleteURL;
}

// form 검증
function checkEmptyKey(obj) {
  let messageObject = {
    productId : "제품 코드",
    detailAddr: "상세 주소",
    paymentDate: "결제 날짜",
    streetAddr: "도로명 주소",
    zipCode: "우편 번호",
    paymentPrice: "결제 금액",
    paymentMethod: "결제 수단"
  };
  let alertMessage = "";
  let objKeys = Object.keys(messageObject);

  objKeys.forEach(key => {
    if(obj.get(key) === "") {
      alertMessage += messageObject[key] + "\n";
    }}
    )
  
  if (alertMessage != "") {
    alert("주문 정보를 입력해 주세요 \n\n" + alertMessage);
    return false;
  }
}


function investRequest() {
  let form = document.querySelector('.InvestForm');
  let id = document.querySelector('.User_email');
  let phone = document.querySelector('.User_phone');
  let payment = document.querySelector('.Payment');
  let zipCode = document.querySelector('#sample4_postcode');
  let roadAddress = document.querySelector('#sample4_roadAddress');
  let detailAddress = document.querySelector('#sample4_detailAddress');
  let checkBoxList = document.getElementsByName('payment');
  let formDataPayment = new FormData();
  let formDataPaylog = new FormData();

  let checkBoxValue = "";
  checkBoxList.forEach(data => {
    if(data.checked) {
      checkBoxValue = data.value;
    }
  });


  let today = new Date();
  let year = today.getFullYear();
  let month = today.getMonth();
  month = month > 10 ? month : '0' + month;

  let day = today.getDay();
  day = day > 10 ? day : '0' + day;

  let hours = today.getHours();
  hours = hours > 10 ? hours : '0' + hours;

  let minutes = today.getMinutes();
  minutes =  minutes > 10 ? minutes : '0' + minutes

  let seconds = today.getSeconds();
  seconds = seconds > 10 ? seconds : '0' + seconds;

  let currentDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;

  formDataPayment.append("productId", productNumber);
  formDataPayment.append("detailAddr", detailAddress.value);
  formDataPayment.append("paymentDate", currentDate);
  formDataPayment.append("streetAddr", roadAddress.value);
  formDataPayment.append("zipCode", zipCode.value);
  formDataPayment.append("paymentPrice", payment.value);
  formDataPayment.append("paymentMethod", checkBoxValue);

  if (checkEmptyKey(formDataPayment) == false) {
    return false;
  }
  ;

  fetch(`/api/invest/post/${productNumber}`, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(
      {
      productId: productNumber,
      detailAddr: detailAddress.value,
      paymentDate: currentDate,
      streetAddr: roadAddress.value,
      zipCode: zipCode.value,
      paymentPrice: payment.value,
      paymentMethod: checkBoxValue
     }
    ),
  })
  .then((res) => res.json())
  .then(alert("투자가 완료 되었습니다"))
  .then(LinkToInvestComplete());
 
}



