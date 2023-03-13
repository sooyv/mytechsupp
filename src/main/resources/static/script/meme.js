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


function create(a, b) {
// detail addresss
  let result = ""
  let detailaddr = "testdetailaddr"
  let streetaddr = "teststreetaddr"
  let form = ""

  // random date
  let startDate = a.getTime();
  let endDate = b.getTime();
    
  let aresult = new Date(startDate + Math.random() * (endDate - startDate));

  function dateFormat(date) {
    let year = date.getFullYear()
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    hour = hour >= 10 ? hour : '0' + hour;
    minute = minute >= 10 ? minute : '0' + minute;
    second = second >= 10 ? second : '0' + second;

    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}
  // 이거는 for ans
    aresult = new Date(startDate + Math.random() * (endDate - startDate));
    let dateform = dateFormat(aresult);

   
// paymethod
    const apaymethod = ["card", "cash"];
    const randomValue = apaymethod[Math.floor(Math.random() * (apaymethod.length - 1))];
    // product id 배열 필요함

    // zipcode
    let zip = ""
    let resultzpij = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
    let randomdd = Math.floor(Math.random() * 10);
    // for 
    let zzzz = ""
    zzzz += resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)];

  let start = "('"
  let starta = "', '"
  let end = "'), "

  let piddid = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

let paymetprict = [57184, 38561, 35896, 86622, 77243, 54479, 26083, 55074, 55760, 37767, 81800, 72782, 108204, 28660, 18918, 38175, 80818, 62766, 80002, 20722, 66333, 90248, 28099, 73757, 85490, 109181, 107129, 68312, 17091, 88193, 34555, 101604, 86268, 40377, 36279, 40329, 88463, 23145, 97021, 108688, 70757, 27804, 97527, 97279, 107630, 69505, 35933, 41505, 69568, 22466, 91080, 72156, 92274, 86295, 88407, 53423, 103534, 66033, 109029, 96907, 14068, 83187, 22471, 12529, 38923, 74501, 94352, 98982, 67699, 69553, 39861, 33680, 27605, 97097, 21651, 72453, 46057, 61534, 52562, 80255, 87706, 40582, 94049, 58476, 100535, 109223, 54025, 108014, 47306, 90189, 72124, 56766, 64217, 101769, 107303, 33601, 15390, 97337, 46484, 61333, 38610, 0, 10000, 123456, 411111, 34534, 123456, 12345, 12345, 10000]

  for (let i = 0; i < 400; i++) {
    let detailaddrr = "testdetailaddr";
    let datearesult = new Date(startDate + Math.random() * (endDate - startDate));
    let datearesultform = dateFormat(datearesult);
    let paymethodran = apaymethod[Math.floor(Math.random() * (apaymethod.length - 1))];
    let zipzip = ""
    zipzip += resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)] + resultzpij[Math.floor(Math.random() * 10)];
    let randon = Math.floor(Math.random() * (100 - 1))
    
    let payapal = paymetprict[randon];
    let iddid = piddid[randon]
    
    result += start + detailaddrr + i + starta + datearesultform + starta + paymethodran + starta + payapal + starta + iddid + starta + streetaddr + i + starta + zipzip + end;

    randon = 0
  }
  
  console.log(result)
}
create(new Date('2022.12.10'), new Date());
// 작동 함수


function getRandomDate(a, b) {

  let answer = ""

  let start = "('"
  let end = "')"

  

  for (let i = 0; i < 101; i++) {
    
    answer += start + qwer + end + ", ";
    
  }

  console.log(answer);
}

// 시작 날짜 ~ 종료 날짜


function qwer() {


  let answer = ""
  let result = ""
  let start = "'"
  let end = "'"

  for (let i = 0; i < 101; i++) {
    result = randomValue;
    answer += start + result + end + ", ";
    result = "";
  }
  console.log(answer);
}
qwer()


function createa() {
  let answer = ""
  let result = ""
  let start = "'"
  let end = "'"

  for (let i = 1; i < 111; i++){
    result = String(i);
    answer += start + result + end + ", ";
    result = "";
  }
  console.log(answer);
}
createa()


function createa() {
  let answer = ""
  
  let zzzz = ""
  let start = "'"
  let end = "'"
  

  for (let i = 1; i < 111; i++){
    
    answer += start + zzzz + end + ", ";
    zzzz = "";
  }
  console.log(answer);
}
createa()

function vv() {
  let a = "["
  let b = "]"
  let c = ", "
  let result = "["

  for (let i = 1; i < 101; i++) {
    result += i + c;
  }
  result + b;
  console.log(result)
}

vv()

let piddid = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]

let paymetprict = [57184, 38561, 35896, 86622, 77243, 54479, 26083, 55074, 55760, 37767, 81800, 72782, 108204, 28660, 18918, 38175, 80818, 62766, 80002, 20722, 66333, 90248, 28099, 73757, 85490, 109181, 107129, 68312, 17091, 88193, 34555, 101604, 86268, 40377, 36279, 40329, 88463, 23145, 97021, 108688, 70757, 27804, 97527, 97279, 107630, 69505, 35933, 41505, 69568, 22466, 91080, 72156, 92274, 86295, 88407, 53423, 103534, 66033, 109029, 96907, 14068, 83187, 22471, 12529, 38923, 74501, 94352, 98982, 67699, 69553, 39861, 33680, 27605, 97097, 21651, 72453, 46057, 61534, 52562, 80255, 87706, 40582, 94049, 58476, 100535, 109223, 54025, 108014, 47306, 90189, 72124, 56766, 64217, 101769, 107303, 33601, 15390, 97337, 46484, 61333, 38610, 0, 10000, 123456, 411111, 34534, 123456, 12345, 12345, 10000]





function rrrr() {
  let result = ""
  let start = "("
  let end = "), "
  let user = "testpay"
  let emailform = "@techsupp.com";
  let piddid = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100];
  let paystat = ["PAY", "REFUND"];

  for (let i = 0; i < 400; i++) {
    let userdata = user + i + emailform;
    let payid = piddid[Math.floor(Math.random() * (piddid.length - 1))];
    let statata = paystat[Math.floor(Math.random() * (paystat.length - 1))];
    result += start + "'" + userdata + "'" + ", " + payid + ", " + "'" +statata + "'" + end;
    userdata = 0;
    payid = 0;
    statata = 0;
  }

  console.log(result);
}
rrrr()


function usercreate() {
  let result = "";
  let start = "('";
  let user = "testpay";
  let emailform = "@techsupp.com";
  let username = "techsupptester";
  let password = "testpassword!!!";
  let userPhonehead = "0";
  let userPhonebody = "-";
  let userPhonefoot = "-";
  let role = "ROLE_USER";

  for (let i = 0; i < 400; i++) {
    let userid = user + i + emailform;
    let usernamevalue = username + i;
    let passwordvalue = password + i;
    let piddid = [1, 2, 3, 4, 5, 6, 7, 8, 9];
    let phone = userPhonehead + piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))] + userPhonebody + piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))] + userPhonefoot + piddid[Math.floor(Math.random() * (piddid.length - 1))] +  piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))] + piddid[Math.floor(Math.random() * (piddid.length - 1))];
    result += start + userid + "', '" + usernamevalue  + "', '" + passwordvalue + "', '" + phone + "', '" + role + "'), ";
    phone = 0;
  }
  console.log(result);
}
usercreate()


function insertproductimaget() {
  let imgname = "product_";
  let imgtype = ".png";

  let originimgname = "product_";

  let imgurl = "/file/product/"

  let repimg = "Y";

  let id = 0;
  let result = ""

  for (let i = 1; i < 101; i++) {
    let string = "";
    string += "('" + imgname + i + imgtype + "', " +
               "'" + originimgname + i + imgtype + "', " +
               "'" + imgurl + imgname + i + imgtype + "', " +
               "'" + repimg + "', " +
               "" + i + "), ";
    result += string;
  }
  console.log(result);
}
insertproductimaget();

img_name
product_00.png

origin_img_name
product_00.png

img_url
/file/product/product_00.png


rep_img
Y

id
1 ~ 100


function wishinsert() {
  let userid = 22;
  let result = ""
  for (let i = 1; i < 101; i++) {
    result += "(" + i + ", " + userid + "), "
  }
  console.log(result)
}
wishinsert();