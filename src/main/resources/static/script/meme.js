let a = {

  testname: "anameOK",
  testphone: 1234,
  testaddress: "addressOK"

}

let b = {

  testname: "bnameOK",
  testphone: 12345,
  testaddress: "baddressOK"

}

let c = { a:
  {

    testname: "anameOK",
    testphone: 1234,
    testaddress: "addressOK"
  
  },
b:
  {

  testname: "bnameOK",
  testphone: 12345,
  testaddress: "baddressOK"

}}
let paging = document.querySelector("asdf");
paging.innerHTML = `
<ul class="PagingList">
  <li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + 0}'" value="${"<"}">
  </li>
`;
for (let i = 1; i < pagingLimit; i++) {
  paging.innerHTML += `
  <li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + (i-1)}'" value="${i}">
  </li>
  `
}
paging.innerHTML += `
<li class="Page">
    <input type="button" class="PageButton" onclick="location.href='${urlString + pagingLimit}'" value="${">"}">
  </li>
</ul>`;


let li = document.createElement("li");

li.classList.add('Page');

let input = document.createElement("input");
input.classList.add('PageButton');
input.setAttribute('type', button);
input.setAttribute('onclick', "location.href='${urlString + 0}'");
input.setAttribute('value', `${"<"}`);


b.appendChild(li);
li.appendChild(input);

let b = document.querySelector('.PagingList');



create table feedback (
  seq int not Null auto_increment,
  picture varchar(20),
  product varchar(20),
  information varchar(20),
  score varchar(20),
  limitdate varchar(20),
  status varchar(20)
)

create table userfeedback (
  seq int not null auto_increment,
  userpicture varchar(20),
  userscore varchar(20),
  userid varchar(20),
  usertext varchar(20),
  product varchar(20)
)



// async 비동기 요청이 없는데 굳이 async 로 작성할 필요가 없음 코드 전면 수정

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
