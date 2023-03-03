package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import techsuppDev.techsupp.domain.Feedback;
import techsuppDev.techsupp.domain.FeedbackImage;
import techsuppDev.techsupp.service.ProductService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;

//    productMain 에서 5개 보여주기
    @RequestMapping("/products/*")
    public ResponseEntity findFiveProduct(
            HttpServletRequest req
            ) {
        int orderNumber = Integer.parseInt(req.getParameter("order"));
        String keyword = req.getParameter("keyword");
        if (orderNumber != 0) {
            orderNumber = orderNumber * 5;
        }
        return ResponseEntity.ok().body(productService.findFiveProduct(orderNumber, keyword));
    }

//    페이징을 위해서 product table 상품 갯수 가져오기
    @RequestMapping(value = "/productPaging/*")
    public ResponseEntity numberOfProductsSend(
            HttpServletRequest req) {
        int pagingNumber =  Integer.parseInt(req.getParameter("page"));
        String keyword = req.getParameter("keyword");

        pagingNumber = pagingNumber * 50;

        return ResponseEntity.ok().body((productService.getNumberOfProduct(pagingNumber, keyword)));
    }

////    상품 선택 후 가져오는 하나의 상품 정보
    @RequestMapping("/product")
    public ResponseEntity productOne(HttpServletRequest request) {
        String productId = request.getParameter("num");
        Long value = Long.parseLong(productId);
        return ResponseEntity.ok().body(productService.findOneProduct(value));
    }





//    feedback api request
    @RequestMapping("/feedbacks/*")
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

    @RequestMapping(value = "/feedbackPaging/*")
    public ResponseEntity numberOfFeedbackPaging(
            HttpServletRequest req) {
        int pagingNumber =  Integer.parseInt(req.getParameter("page"));
        String keyword = req.getParameter("keyword");

        pagingNumber = pagingNumber * 50;

        return ResponseEntity.ok().body((productService.getNumberOfFeedback(pagingNumber, keyword)));
    }


    @RequestMapping(value = "/feedback/post", method = RequestMethod.POST)
    public FeedbackImage postFeedback(
            MultipartHttpServletRequest req
    ) throws IOException {

        MultipartFile files = req.getFile("image");
        System.out.println("=======");
        System.out.println(files);
        String downPath = "/Users/mk/Desktop";


        File fileDir = new File(downPath);

        if(!fileDir.exists()) {
            fileDir.mkdir();
        }

        String saveFileName = "save111.png";

        File saveFile = new File(downPath, saveFileName);
        files.transferTo(saveFile);



        return null;
    }

}
