


// 주석 단위로 함수가 나뉘어져 있습니다.
// 각 함수들은 선언-호출로 구성 되어 있고
// console.log에 나오는 값을 직접 db에 넣어주셔야합니다.







// 
// 
// 


function createPayment(a, b) {
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
    let poi = "insert into payment (detail_addr, payment_date, payment_method, payment_price, product_id, street_addr, zip_code) values "
    poi += result
    console.log(poi)
  }
  createPayment(new Date('2022.12.10'), new Date());






// 
// 
// 



// product image create

function createTestproductimaget() {
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
  let poi = "insert into image (img_name, origin_img_name, img_url, rep_img, id) values ";
  poi += result;
  console.log(poi);
}
createTestproductimaget();






// 
// 
// 


// create default image for feedback;
function insertfeedbackimaget() {
  let productId = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]
  let imgname = "default";
  let imgtype = ".png";

  let originimgname = "default_";

  let imgurl = "/file/default"

  let repimg = "Y";


  let result = ""
  let k = 0;
    for (let i = 1; i < 401; i++) {
      let string = "";
      id = i;
      string += "('" + imgname + imgtype + "', " +
                "'" + originimgname + (i - 1) + imgtype + "', " +
                "'" + imgurl + "/" +imgname + imgtype + "', " +
                "'" + repimg + "', " +
                "'" + Math.floor(Math.random() * (productId.length - 1)) + "', ";
      result += string;
      result += "'" + i + "'), "
    }
  let poi = "insert into feedback_image (img_name, img_origin_img_name, img_url, rep_img, feedback_id, id) values ";
  poi += result;
  console.log(poi);
}
insertfeedbackimaget();







// 
// 
// 

// create test feedback; feedback 

function createFeedback() {
  let status = ["O", "X"];
  let score = [1,2,3,4,5];
  let text = "feedback test text";
  let productId = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100]
  let userId = "testpay";
  let eamil = "@techsupp.com"
  let result = "";

  Math.floor(Math.random() * (status.length - 1))
  Math.floor(Math.random() * (score.length - 1))
  Math.floor(Math.random() * (productId.length - 1))
  for (let i = 1; i <= 400; i++) {
    result += "('" + status[Math.floor(Math.random() * (status.length + 1 - 1))] + "', " + score[Math.floor(Math.random() * (score.length - 1))] + ", '" + text + i + "', " + productId[Math.floor(Math.random() * (productId.length - 1))] + ", " + i + "), "
  }
  let poi = "insert into feedback (feedback_status, score, feedback_text, product_id, user_id) values ";
  poi += result;
console.log(poi)

}
createFeedback();






// 
// 
// 

// create test user 

function createTestUserForPaylog() {
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

  let poi = "insert into user (user_email, user_name, user_password, user_phone, role) values "
  poi += result;
  console.log(poi);
}
createTestUserForPaylog()






// 
// 
// 


// paylog create

function createTestPaylog() {
  let result = ""
  let start = "("
  let end = "), "
  let user = "testpay"
  let emailform = "@techsupp.com";
  let piddid = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100];
  let paystat = ["PAY", "REFUND"];

  for (let i = 0; i < 400; i++) {
    let userdata = user + i + emailform;
    let statata = paystat[Math.floor(Math.random() * (paystat.length - 1))];
    result += start + "'" + userdata + "'" + ", " + (i + 1 ) + ", " + "'" +statata + "'" + end;
    userdata = 0;
    payid = 0;
    statata = 0;
  }
  let poi = "insert into user (user_email, payment_id, paylod_status) values "
  poi += result;
  console.log(poi);
}
createTestPaylog()