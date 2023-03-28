package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import techsuppDev.techsupp.DTO.Paylog;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.controller.form.ProductListForm;
import techsuppDev.techsupp.controller.form.ProductListNoWishForm;
import techsuppDev.techsupp.controller.form.ProductSingleForm;
import techsuppDev.techsupp.domain.*;
import techsuppDev.techsupp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;
    private final PaymentService paymentService;
    private final UserService userService;
    private final WishService wishService;
    private final FeedbackService feedbackService;
    private final FeedbackImageService feedbackImageService;


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

        HttpSession loginSesion = req.getSession();
        String userId ="";
        System.out.println("userId : " + loginSesion.getAttribute("userId"));

        if (loginSesion.getAttribute("userId") != null) {
            userId = loginSesion.getAttribute("userId").toString();

            List<ProductListForm> fiveProduct = productService.findFiveProductOnLogin(orderNumber, keyword, userId);
            ArrayList<Long> fiveProductNumber = new ArrayList<Long>();
            for(int i = 0; i < fiveProduct.size(); i++) {
                fiveProductNumber.add(fiveProduct.get(i).getId());
            }

            ArrayList paymentNumList = paymentService.getFivePaymentNumber(fiveProductNumber);

            JSONArray form = new JSONArray();

            for(int i = 0; i < fiveProduct.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", fiveProduct.get(i).getId());
                jsonObject.put("seqId", fiveProduct.get(i).getSeqId());
                jsonObject.put("productName", fiveProduct.get(i).getProductName());
                jsonObject.put("investPrice", fiveProduct.get(i).getInvestPrice());
                jsonObject.put("period", fiveProduct.get(i).getPeriod());
                jsonObject.put("totalPrice", fiveProduct.get(i).getTotalPrice());
                jsonObject.put("paymentValue", paymentNumList.get(i));
                jsonObject.put("imgUrl", fiveProduct.get(i).getImgUrl());
                jsonObject.put("wishId", fiveProduct.get(i).getWishId());
                form.add(jsonObject);
            }

            return ResponseEntity.ok().body(form);

        } else {
            userId = "0";

            List<ProductListNoWishForm> fiveProduct = productService.findFiveProductOnNoLogin(orderNumber, keyword, userId);
            ArrayList<Long> fiveProductNumber = new ArrayList<Long>();
            for(int i = 0; i < fiveProduct.size(); i++) {
                fiveProductNumber.add(fiveProduct.get(i).getId());
            }

            ArrayList paymentNumList = paymentService.getFivePaymentNumber(fiveProductNumber);

            JSONArray form = new JSONArray();

            for(int i = 0; i < fiveProduct.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", fiveProduct.get(i).getId());
                jsonObject.put("seqId", fiveProduct.get(i).getSeqId());
                jsonObject.put("productName", fiveProduct.get(i).getProductName());
                jsonObject.put("investPrice", fiveProduct.get(i).getInvestPrice());
                jsonObject.put("period", fiveProduct.get(i).getPeriod());
                jsonObject.put("totalPrice", fiveProduct.get(i).getTotalPrice());
                jsonObject.put("paymentValue", paymentNumList.get(i));
                jsonObject.put("imgUrl", fiveProduct.get(i).getImgUrl());
                jsonObject.put("wishId", userId);
                form.add(jsonObject);
            }

            return ResponseEntity.ok().body(form);
        }

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
        String productValue = request.getParameter("num");
        Long productId = Long.parseLong(productValue);

        HttpSession loginSession = request.getSession();
        String userEmail = "";
        String userId = "";

        if(loginSession.getAttribute("userEmail") != null) {
            System.out.println("controller: userEmail != null");
            userEmail = loginSession.getAttribute("userEmail").toString();

        } else {
            System.out.println("controller: userEmail == null ");
        }

        ProductSingleForm productInformation = new ProductSingleForm();

        if (loginSession.getAttribute("userId") != null) {
            System.out.println("controller: userId != null" + loginSession.getAttribute("userId").toString());
            userId = loginSession.getAttribute("userId").toString();
        } else {
            System.out.println("controller: userId = null");
            userId = "0";
        }

//        body에 담아줄 객체 생성
        JSONObject jsonData = new JSONObject();

//        선택한 상품 정보 가져오는 것
        productInformation = (ProductSingleForm) productService.findOneProduct(productId, userId);



        if (userEmail == null || userEmail == "") {
            jsonData.put("paylog", "y");
        } else {
            if(paymentService.checkPaylogHistory(userEmail, productId).equals("log exist")) {
                jsonData.put("paylog", "n");
            } else {
                jsonData.put("paylog", "y");
            }
        }

        long paymentCount = paymentService.getSinglePaymentCount(productId);

        jsonData.put("seqId",productInformation.getSeqId());
        jsonData.put("totalPrice",productInformation.getTotalPrice());
        jsonData.put("information",productInformation.getInformation());
        jsonData.put("productName",productInformation.getProductName());
        jsonData.put("period",productInformation.getPeriod());
        jsonData.put("investPrice",productInformation.getInvestPrice());
        jsonData.put("id",productInformation.getId());
        jsonData.put("productStatus",productInformation.getProductStatus());
        jsonData.put("imgUrl", productInformation.getImgUrl());
        jsonData.put("wishId", productInformation.getWishId());
        jsonData.put("paymentCount", paymentCount);

        return ResponseEntity.ok().body(jsonData);
    }

//    즐겨찾기 post
    @RequestMapping(value = "/wish/post/*", method = RequestMethod.GET)
    public ResponseEntity wishPost( HttpServletRequest req) {
        String productNum = req.getParameter("num");
        Long productId = Long.parseLong(productNum);

        HttpSession loginSession = req.getSession();
        String userId = "";

        if(loginSession.getAttribute("userId") != null) {
            System.out.println("controller: userId != null");
            userId = loginSession.getAttribute("userId").toString();
            wishService.wishPost(userId, productId);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", "즐겨찾기에 추가 되었습니다.");
            return ResponseEntity.ok().body(jsonObject);
        } else {
            System.out.println("controller: userId == null ");
            String result = "서버연결에 실패 했습니다.";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", result.toString());
            return ResponseEntity.ok().body(jsonObject);
        }
    }
//    즐겨찾기 delete
@RequestMapping(value = "/wish/delete/*", method = RequestMethod.GET)
public ResponseEntity wishDelete( HttpServletRequest req) {
    String productNum = req.getParameter("num");
    Long productId = Long.parseLong(productNum);

    HttpSession loginSession = req.getSession();
    String userId = "";

    if(loginSession.getAttribute("userId") != null) {
        System.out.println("controller: userId != null");
        userId = loginSession.getAttribute("userId").toString();
        wishService.wishDelete(userId, productId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "즐겨찾기에서 삭제되었습니다.");
        return ResponseEntity.ok().body(jsonObject);
    } else {
        System.out.println("controller: userId == null ");
        String result = "서버연결에 실패 했습니다.";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", result.toString());
        return ResponseEntity.ok().body(jsonObject);
    }
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
    public void saveInvestLog(
            @RequestBody JSONObject object, HttpServletRequest req) {
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

        paymentService.savePayment(payment);

//        paylog

        Long savedPayment = paymentService.getSinglePaymentId();

        HttpSession loginSession = req.getSession();


        Paylog insertPaylog = new Paylog();
        String userEmail = loginSession.getAttribute("userEmail").toString();
        insertPaylog.setUserEmail(userEmail);
        insertPaylog.setPaymentId(savedPayment);
        insertPaylog.setPaylogStatus(PaylogStatus.PAY);

        paymentService.savePaylog(insertPaylog);
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

    @RequestMapping(value = "/feedback/specificlist", method = RequestMethod.GET)
    public ResponseEntity specificProductFeedback(
            HttpServletRequest req) {
        Long productId = Long.parseLong(req.getParameter("num"));
        return ResponseEntity.ok().body(feedbackService.findSpecificFeedback(productId));
    }


    @RequestMapping(value = "/feedback/post", method = RequestMethod.POST)
    public void postFeedback(
            MultipartHttpServletRequest req ,HttpSession session
    ) throws IOException {

        int feedbackScore = Integer.parseInt(req.getParameter("score"));
        String feedbackText = req.getParameter("text");
        Long productId = Long.parseLong(req.getParameter("num"));
        Long userId = Long.parseLong(session.getAttribute("userId").toString());

        MultipartFile files = req.getFile("image");

        String downPath = "/Users/leesoyoung/Desktop/Funding/techsupp/mytechsupp/src/main/resources/static/file/feedback";

        File fileDir = new File(downPath);

        if(!fileDir.exists()) {
            fileDir.mkdir();
        }
        String originalFileName = files.getOriginalFilename();
        String saveFileName = "feedback_" + System.currentTimeMillis() + "_" +originalFileName;
        File saveFile = new File(downPath, saveFileName);
        files.transferTo(saveFile);


        Feedback feedbackForm = new Feedback();
        feedbackForm.setFeedbackStatus(FeedbackStatus.O);
        feedbackForm.setFeedbackText(feedbackText);
        feedbackForm.setProductId(productId);
        feedbackForm.setScore(feedbackScore);
        feedbackForm.setUserId(userId);

        System.out.println(" complete");
        feedbackService.insertFeedback(feedbackForm);

        Long feedbackInsertedId = feedbackService.getInsertedFeedbackId();

        FeedbackImage feedbackImageForm = new FeedbackImage();
        feedbackImageForm.setImgName(originalFileName);
        feedbackImageForm.setOriginImgName(saveFileName);
        feedbackImageForm.setImgUrl("/file/feedback/" + saveFileName);
        feedbackImageForm.setRepImg("Y");
        feedbackImageForm.setId(productId);
        feedbackImageForm.setFeedbackId(feedbackInsertedId);

        feedbackImageService.insertFeedbackImageInformation(feedbackImageForm);


    }

















}
