package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import techsuppDev.techsupp.domain.FeedbackImage;
import techsuppDev.techsupp.service.ProductService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;

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
