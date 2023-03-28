


// 주석 단위로 함수가 나뉘어져 있습니다.
// 각 함수들은 선언-호출로 구성 되어 있고
// console.log에 나오는 값을 직접 db에 넣어주셔야합니다.


//  test product 생성은 페이지에서 생성 가능
//  productMain/createtest
// url로 요청을 하면 intellij 콘솔 창에 string 값이 출력 됩니다 
// 






// 
// 
// 

// create test payment

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



// product image create --------- no create  XXX

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
createTestPaylog();

//insert into user -> paylog 변경한 후
//(seq_id, product_name, information, total_price, invest_price, period, product_status, regdate, click_count) values (1, "testProduct1", "testInformation1", 119841, 96165, 20230314071836, 'PROGRESS', 20230210174353, 53), (2, "testProduct2", "testInformation2", 116411, 10942, 20231020104859, 'FAIL', 20240423070624, 83), (3, "testProduct3", "testInformation3", 173860, 74296, 20230814013347, 'FAIL', 20240601065358, 72), (4, "testProduct4", "testInformation4", 452184, 109871, 20240422141424, 'SUCCESS', 20240127022641, 36), (5, "testProduct5", "testInformation5", 571000, 73441, 20240115031842, 'FAIL', 20230811115901, 65), (6, "testProduct6", "testInformation6", 849173, 86743, 20240809123841, 'SUCCESS', 20230812151738, 72), (7, "testProduct7", "testInformation7", 401716, 25554, 20240802094943, 'PROGRESS', 20230123213256, 20), (8, "testProduct8", "testInformation8", 516088, 56693, 20241107191922, 'FAIL', 20240711100510, 27), (9, "testProduct9", "testInformation9", 587131, 71152, 20230420064013, 'SUCCESS', 20240119221349, 47), (10, "testProduct10", "testInformation10", 424927, 80746, 20241123173427, 'FAIL', 20230619194449, 37), (11, "testProduct11", "testInformation11", 948715, 22399, 20240323011413, 'FAIL', 20230509235039, 74), (12, "testProduct12", "testInformation12", 809051, 20706, 20230405170635, 'SUCCESS', 20230212050739, 15), (13, "testProduct13", "testInformation13", 1047878, 39764, 20230410062047, 'PROGRESS', 20240113182414, 36), (14, "testProduct14", "testInformation14", 643116, 16446, 20230619234327, 'SUCCESS', 20240303224920, 37), (15, "testProduct15", "testInformation15", 215433, 87379, 20230404055322, 'PROGRESS', 20240826214701, 69), (16, "testProduct16", "testInformation16", 347562, 35689, 20240521045754, 'PROGRESS', 20240905211002, 97), (17, "testProduct17", "testInformation17", 644136, 108144, 20240409162621, 'SUCCESS', 20240216011553, 28), (18, "testProduct18", "testInformation18", 1028224, 99239, 20240620234931, 'FAIL', 20241119015627, 33), (19, "testProduct19", "testInformation19", 812079, 100240, 20230805055708, 'SUCCESS', 20230420022556, 54), (20, "testProduct20", "testInformation20", 242121, 31104, 20240717102941, 'FAIL', 20230111071834, 42), (21, "testProduct21", "testInformation21", 780437, 42054, 20240911022726, 'FAIL', 20240406194606, 1), (22, "testProduct22", "testInformation22", 421180, 98886, 20231009075511, 'PROGRESS', 20230609053737, 28), (23, "testProduct23", "testInformation23", 550040, 23323, 20231014210324, 'PROGRESS', 20230803185922, 85), (24, "testProduct24", "testInformation24", 481192, 33863, 20230923155302, 'FAIL', 20241019155243, 82), (25, "testProduct25", "testInformation25", 507812, 38708, 20231125171359, 'SUCCESS', 20231006223359, 6), (26, "testProduct26", "testInformation26", 876167, 83248, 20241021182846, 'FAIL', 20230312020750, 47), (27, "testProduct27", "testInformation27", 151257, 74090, 20231017183428, 'FAIL', 20240724143126, 63), (28, "testProduct28", "testInformation28", 610545, 21379, 20230423020404, 'SUCCESS', 20231120190140, 59), (29, "testProduct29", "testInformation29", 1067491, 70732, 20230213192420, 'PROGRESS', 20230821080354, 46), (30, "testProduct30", "testInformation30", 549529, 105742, 20230621035544, 'FAIL', 20231026143825, 29), (31, "testProduct31", "testInformation31", 271492, 54168, 20240503031308, 'SUCCESS', 20230503232754, 40), (32, "testProduct32", "testInformation32", 414292, 46565, 20240510122405, 'SUCCESS', 20230516111713, 22), (33, "testProduct33", "testInformation33", 481603, 76180, 20240224232519, 'SUCCESS', 20230619160838, 76), (34, "testProduct34", "testInformation34", 587173, 21347, 20231123011423, 'PROGRESS', 20240404112239, 39), (35, "testProduct35", "testInformation35", 282994, 100171, 20240304211226, 'SUCCESS', 20240310015426, 73), (36, "testProduct36", "testInformation36", 187351, 92298, 20230114024729, 'SUCCESS', 20240213060706, 18), (37, "testProduct37", "testInformation37", 165433, 29944, 20240411152943, 'SUCCESS', 20231110162208, 61), (38, "testProduct38", "testInformation38", 1052513, 40459, 20230709191918, 'PROGRESS', 20240509153611, 39), (39, "testProduct39", "testInformation39", 164829, 69302, 20230303014650, 'SUCCESS', 20230326093843, 81), (40, "testProduct40", "testInformation40", 429687, 71844, 20240619073201, 'PROGRESS', 20230812080439, 59), (41, "testProduct41", "testInformation41", 989778, 93398, 20230519095111, 'SUCCESS', 20240218153222, 11), (42, "testProduct42", "testInformation42", 165983, 83495, 20230801233645, 'FAIL', 20240409152234, 75), (43, "testProduct43", "testInformation43", 1007036, 90102, 20240805153139, 'PROGRESS', 20240612182939, 19), (44, "testProduct44", "testInformation44", 273451, 29967, 20240603201857, 'SUCCESS', 20241106183128, 44), (45, "testProduct45", "testInformation45", 298885, 107453, 20230206145039, 'PROGRESS', 20240224090601, 70), (46, "testProduct46", "testInformation46", 1018065, 20185, 20230918120934, 'FAIL', 20240622065557, 57), (47, "testProduct47", "testInformation47", 249387, 31792, 20230107204559, 'PROGRESS', 20241113081228, 76), (48, "testProduct48", "testInformation48", 305715, 56352, 20230724175439, 'PROGRESS', 20240314130244, 82), (49, "testProduct49", "testInformation49", 896131, 33914, 20240717184720, 'PROGRESS', 20230802111303, 73), (50, "testProduct50", "testInformation50", 885594, 72258, 20240811192056, 'FAIL', 20231009230817, 28), (51, "testProduct51", "testInformation51", 213344, 66824, 20240725172114, 'FAIL', 20240526060844, 20), (52, "testProduct52", "testInformation52", 782740, 22536, 20230801143001, 'SUCCESS', 20230911202708, 46), (53, "testProduct53", "testInformation53", 656378, 47989, 20230115123655, 'SUCCESS', 20230121121636, 93), (54, "testProduct54", "testInformation54", 910827, 54764, 20241108190127, 'PROGRESS', 20230907230827, 88), (55, "testProduct55", "testInformation55", 815188, 96873, 20240104211924, 'FAIL', 20231009090331, 65), (56, "testProduct56", "testInformation56", 637448, 39939, 20240817124449, 'SUCCESS', 20230502055024, 14), (57, "testProduct57", "testInformation57", 125361, 54164, 20231025121349, 'FAIL', 20240910235947, 87), (58, "testProduct58", "testInformation58", 924718, 35356, 20240123210354, 'PROGRESS', 20230214110302, 61), (59, "testProduct59", "testInformation59", 779128, 63275, 20230421190927, 'SUCCESS', 20240719105106, 9), (60, "testProduct60", "testInformation60", 125950, 37209, 20230626121528, 'FAIL', 20240605191644, 46), (61, "testProduct61", "testInformation61", 396086, 108192, 20230924073437, 'PROGRESS', 20240626112118, 76), (62, "testProduct62", "testInformation62", 564417, 83114, 20240209202142, 'SUCCESS', 20240705071012, 95), (63, "testProduct63", "testInformation63", 423447, 14039, 20230726032338, 'SUCCESS', 20240216063305, 66), (64, "testProduct64", "testInformation64", 188797, 34092, 20230506053403, 'PROGRESS', 20231023143334, 47), (65, "testProduct65", "testInformation65", 706977, 55364, 20230710023612, 'PROGRESS', 20230517071713, 39), (66, "testProduct66", "testInformation66", 882668, 60602, 20241123235010, 'SUCCESS', 20230116225304, 85), (67, "testProduct67", "testInformation67", 351975, 97027, 20230306212748, 'SUCCESS', 20240510091221, 48), (68, "testProduct68", "testInformation68", 387868, 53488, 20240112181615, 'FAIL', 20230101064114, 41), (69, "testProduct69", "testInformation69", 989942, 109821, 20230910061643, 'PROGRESS', 20241006055623, 56), (70, "testProduct70", "testInformation70", 953072, 86039, 20240427044640, 'PROGRESS', 20230505053717, 44), (71, "testProduct71", "testInformation71", 187226, 100948, 20241023205059, 'SUCCESS', 20230313102059, 16), (72, "testProduct72", "testInformation72", 418882, 30678, 20240121114024, 'SUCCESS', 20230301092050, 16), (73, "testProduct73", "testInformation73", 747266, 48201, 20230901190805, 'FAIL', 20231011010434, 30), (74, "testProduct74", "testInformation74", 764466, 70278, 20240619073107, 'SUCCESS', 20231105093333, 24), (75, "testProduct75", "testInformation75", 711150, 41743, 20240719074514, 'PROGRESS', 20240120021647, 93), (76, "testProduct76", "testInformation76", 808842, 75486, 20230223035638, 'SUCCESS', 20230327092932, 49), (77, "testProduct77", "testInformation77", 617261, 69223, 20241112121453, 'FAIL', 20230808153630, 92), (78, "testProduct78", "testInformation78", 941647, 98719, 20230210072833, 'FAIL', 20230902165302, 57), (79, "testProduct79", "testInformation79", 406142, 56358, 20240815071624, 'FAIL', 20230111112513, 61), (80, "testProduct80", "testInformation80", 660575, 16066, 20230207194655, 'FAIL', 20230412185503, 48), (81, "testProduct81", "testInformation81", 419335, 63493, 20230727223856, 'PROGRESS', 20240425182302, 62), (82, "testProduct82", "testInformation82", 499830, 52931, 20240417233102, 'SUCCESS', 20240411235220, 86), (83, "testProduct83", "testInformation83", 989440, 105798, 20240518202810, 'FAIL', 20230823012458, 8), (84, "testProduct84", "testInformation84", 566603, 61993, 20230627010331, 'FAIL', 20240404025624, 48), (85, "testProduct85", "testInformation85", 420552, 59930, 20240923030128, 'SUCCESS', 20240610124248, 80), (86, "testProduct86", "testInformation86", 857081, 30448, 20240206033713, 'PROGRESS', 20240403180604, 59), (87, "testProduct87", "testInformation87", 412365, 103297, 20231108192833, 'FAIL', 20230203022822, 94), (88, "testProduct88", "testInformation88", 922745, 101425, 20231118041337, 'PROGRESS', 20241007042257, 63), (89, "testProduct89", "testInformation89", 705438, 26181, 20231022113754, 'PROGRESS', 20230410033958, 76), (90, "testProduct90", "testInformation90", 416944, 55438, 20240526034925, 'SUCCESS', 20230213144553, 57), (91, "testProduct91", "testInformation91", 534291, 21752, 20230112011228, 'SUCCESS', 20231109131726, 48), (92, "testProduct92", "testInformation92", 126414, 17150, 20230904203336, 'PROGRESS', 20230607212701, 53), (93, "testProduct93", "testInformation93", 850274, 20617, 20230209060303, 'PROGRESS', 20230101171115, 62), (94, "testProduct94", "testInformation94", 138562, 20671, 20241016225804, 'PROGRESS', 20230211222425, 24), (95, "testProduct95", "testInformation95", 302633, 29514, 20230525064540, 'PROGRESS', 20240220224245, 46), (96, "testProduct96", "testInformation96", 536973, 29937, 20240418182758, 'SUCCESS', 20240317095430, 37), (97, "testProduct97", "testInformation97", 1011470, 99995, 20240115225730, 'FAIL', 20240712181949, 18), (98, "testProduct98", "testInformation98", 736116, 24901, 20240625175732, 'FAIL', 20230227082704, 50), (99, "testProduct99", "testInformation99", 373813, 62823, 20240901074553, 'PROGRESS', 20240108131505, 68), (100, "testProduct100", "testInformation100", 409833, 79521, 20240811224116, 'FAIL', 20240302234338, 98);

// 안되는 것은 status를 변경
// 안되는 컬럼 확인 : show column from feedback;
// alter table paylog modify column paylog_status varchar(10);
// alter table feedback modify column feedback_status varchar(10);