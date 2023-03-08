
function LinkToInvestComplete() {
  const investCompleteURL = `/invest/complete/?num=${productNumber}`
  location.href = investCompleteURL;
}

// form 검증
async function checkEmptyKey(obj) {
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
  
  if (alertMessage == "") {
    return obj;
  } else {
    alert("주문 정보를 입력해 주세요 \n\n" + alertMessage);
    return false;
  }

}


async function investRequest() {
  let form = document.querySelector('.InvestForm');
  let id = document.querySelector('.User_email');
  let phone = document.querySelector('.User_phone');
  let payment = document.querySelector('.Payment');
  let zipCode = document.querySelector('#sample4_postcode');
  let roadAddress = document.querySelector('#sample4_roadAddress');
  let detailAddress = document.querySelector('#sample4_detailAddress');
  let checkBoxList = document.getElementsByName('payment');
  let formDataPayment = new FormData();
  // let formDataPayment = new Object();
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

  


  // checkEmptyKey(formDataPayment)
  console.log("================")
  console.log(typeof(formDataPayment))
  return formDataPayment;

  // let messageObject = {
  //   productId : "제품 코드",
  //   detailAddr: "상세 주소",
  //   paymentDate: "결제 날짜",
  //   streetAddr: "도로명 주소",
  //   zipCode: "우편 번호",
  //   paymentPrice: "결제 금액",
  //   paymentMethod: "결제 수단"
  // };
  // let alertMessage = "";
  // let objKeys = Object.keys(messageObject);

  // objKeys.forEach(key => {
  //   if(formDataPayment.get(key) === "") {
  //     alertMessage += messageObject[key] + "\n";
  //   }}
  //   )
  
  // if (alertMessage == "") {
  //   alert("투자 성공")
  //   return formDataPayment;
  // } else {
    
  //   alert("주문 정보를 입력해 주세요 \n\n" + alertMessage);
  //   return false;
  // }


}

// formDataPayment 에 값이 전부 들어가 있는 지 확인하는 함수 필요함

// function checkForm(formDataPayment) {
//   let messageObject = {
//     productId : "제품 코드",
//     detailAddr: "상세 주소",
//     paymentDate: "결제 날짜",
//     streetAddr: "도로명 주소",
//     zipCode: "우편 번호",
//     paymentPrice: "결제 금액",
//     paymentMethod: "결제 수단"
//   };
//   let alertMessage = "";
//   let objKeys = Object.keys(messageObject);

//   function getEmtyKey(formDataPayment , value) {
//     alertMessage += (objKeys.filter(key => formDataPayment.get(key) === value) + "\n");
//   }


//   if (alertMessage === "") {
//     return formDataPayment;
//   } else {
//     alert(alertMessage + "를 입력해주세요");
//     return false;
//   }

// }
let form = 1;
let data = 2;
async function sendPaymentData() {
  
  let formData = 0;
  await investRequest().then(res => (formData = res));
  console.log("investRequest 문제 없음")
  // 여기까지는 문제 없음 데이터 잘 넘어감

  let checkData = 0;
  await checkEmptyKey(formData).then(res => checkData = res);
  console.log("checkEmptyKey 문제 없음")
  
  let result = new FormData();
  async function convertToFormData(obj) {
    for (const key of obj.keys()) {
      result.append(key, obj.get(key))
    }
    return JSON.stringify(result);
  }

  await convertToFormData(checkData).then(res => result = res);

  // 여기까지 문제 없음

  // body 에 빈 배열 들어감
  let resolve = await fetch(`/api/invest/post/${productNumber}`, {method: "POST", headers: {'Content-Type': 'application/json'}, body: result })
  .then((res) => res.json())
  .then(alert("투자가 완료 되었습니다"));
}

//  const postForm = (body) => {
//   return fetch(`/api/invest/post/${productNumber}`, {
//     method: "POST",
//     headers: {
//       'Content-Type': 'application/json',
//     },
//     body: JSON.stringify(Object.fromEntries(new FormData(checkData))
//     ),
//   })
//   .then((res) => res.json())
//   .then(alert("투자가 완료 되었습니다"));
//  }




   let resolve = await fetch(`/api/invest/post/${productNumber}`, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(
      {
      checkData
     }
    ),
  })
  .then((res) => res.json())
  .then(alert("투자가 완료 되었습니다"));

}




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
.then(LinkToInvestComplete())
.then(checkEmptyKey())
.then((res) => res.json())
.then(alert("투자가 완료 되었습니다"));
