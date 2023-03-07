package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.PaymentService;
import techsuppDev.techsupp.service.ProductService;
import techsuppDev.techsupp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;
    private final PaymentService paymentService;

    private final UserService userService;


//    로그인 여부 확인
    @RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
    public ResponseEntity apiLogin(HttpServletRequest req) {
        HttpSession loginSession = req.getSession();

        JSONObject checkLogin = new JSONObject();
        if(loginSession.getAttribute("userEmail") == null &&
                loginSession.getAttribute("userName") == null) {
            checkLogin.put("login", "fail");
        } else {
            checkLogin.put("login", "success");
        }
        System.out.println("======");
        System.out.println(checkLogin.get("login"));
        return ResponseEntity.ok().body(checkLogin);
    }



//    productMain 에서 5개 보여주기

    @RequestMapping(value = "/products/*", method = RequestMethod.GET)
    public ResponseEntity findFiveProduct(
            HttpServletRequest req
            ) throws IOException {
        int orderNumber = Integer.parseInt(req.getParameter("order"));
        String keyword = req.getParameter("keyword");
        if (orderNumber != 0) {
            orderNumber = orderNumber * 5;
        }
        return ResponseEntity.ok().body(productService.findFiveProduct(orderNumber, keyword));
    }

//    페이징을 위해서 product table 상품 갯수 가져오기
    @RequestMapping(value = "/productPaging/*", method = RequestMethod.GET)
    public ResponseEntity numberOfProductsSend(
            HttpServletRequest req) {
        int pagingNumber =  Integer.parseInt(req.getParameter("page"));
        String keyword = req.getParameter("keyword");

        pagingNumber = pagingNumber * 50;

        return ResponseEntity.ok().body((productService.getNumberOfProduct(pagingNumber, keyword)));
    }

////    상품 선택 후 가져오는 하나의 상품 정보
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseEntity productOne(HttpServletRequest request) {
        String productId = request.getParameter("num");
        Long value = Long.parseLong(productId);
        return ResponseEntity.ok().body(productService.findOneProduct(value));
    }

//    상품 투자를 위해 유저 정보를 검색 후 json으로 보내주는 것
    @RequestMapping(value = "/userinformation", method = RequestMethod.GET)
    public ResponseEntity getUserInformationForInvest(
            HttpServletRequest req) {
        HttpSession loginSession = req.getSession();
        String userId = loginSession.getAttribute("userEmail").toString();

        return ResponseEntity.ok().body(userService.getUserByEmail(userId));
    }


// 투자 폼 받아서 db에 저장
    @RequestMapping(value = "/invest/post/*", method = RequestMethod.POST)
    public ResponseEntity saveInvestLog(
            @RequestBody JSONObject object) {

        PaymentForm payment = new PaymentForm();
        Long productId = Long.parseLong(object.get("productId").toString());
        payment.setProductId(productId);
        payment.setStreetAddr(object.get("streetAddr").toString());
        payment.setDetailAddr(object.get("detailAddr").toString());
        payment.setZipCode(object.get("zipCode").toString());
        payment.setPaymentPrice(Integer.parseInt(object.get("paymentPrice").toString()));

        String YMD = Timestamp.valueOf(LocalDateTime.now()).toLocalDateTime().toString();

        String[] array = YMD.split("T");
        String dateTime = array[0] + " " + array[1];

        payment.setPaymentDate(dateTime);

        payment.setPaymentMethod(object.get("paymentMethod").toString());

        paymentService.savePay(payment);

        return null;
    }




//    feedback api request
    @RequestMapping(value = "/feedbacks/*", method = RequestMethod.GET)
    public ResponseEntity findFiveProductFeedback(
            HttpServletRequest req
    ) {
        int orderNumber = Integer.parseInt(req.getParameter("order"));
        String keyword = req.getParameter("keyword");
        if (orderNumber != 0) {
            orderNumber = orderNumber * 5;
        }

        return ResponseEntity.ok().body(productService.findFiveProductFeedback(orderNumber, keyword));
    }

    @RequestMapping(value = "/feedbackPaging/*", method = RequestMethod.GET)
    public ResponseEntity numberOfFeedbackPaging(
            HttpServletRequest req) {
        int pagingNumber =  Integer.parseInt(req.getParameter("page"));
        String keyword = req.getParameter("keyword");

        pagingNumber = pagingNumber * 50;

        return ResponseEntity.ok().body((productService.getNumberOfFeedback(pagingNumber, keyword)));
    }


    @RequestMapping(value = "/feedback/post", method = RequestMethod.POST)
    public void postFeedback(
            MultipartHttpServletRequest req
    ) throws IOException {

        String a = req.getParameter("score");
        String b = req.getParameter("text");

        MultipartFile files = req.getFile("image");

        String downPath = "/Users/mk/Desktop/team project/techsupp/src/main/resources/static/file/feedback";

        File fileDir = new File(downPath);

        if(!fileDir.exists()) {
            fileDir.mkdir();
        }


        String saveFileName = "save222.png";

        File saveFile = new File(downPath, saveFileName);

        System.out.println(saveFile);
        System.out.println(files);


        files.transferTo(saveFile);
    }

}
