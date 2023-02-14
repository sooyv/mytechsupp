package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestController {

    private final ProductService productService;

//    productMain 에서 5개 보여주기
    @RequestMapping("/products/{pageNumber}")
    public ResponseEntity findFiveProduct(@PathVariable("pageNumber") int pagingNumber) {
        if (pagingNumber != 0) {
            pagingNumber = pagingNumber * 5;
        }
        return ResponseEntity.ok().body(productService.findFiveProduct(pagingNumber));
    }

//    페이징을 위해서 product table 상품 갯수 가져오기
    @RequestMapping("/paging")
    public ResponseEntity numberOfProductsSend() {
        return ResponseEntity.ok().body((productService.getNumberOfProduct()));
    }
////    상품 선택 후 가져오는 하나의 상품 정보
    @RequestMapping("/product")
    public ResponseEntity productOne(HttpServletRequest request) {
        String productId = request.getParameter("num");
        int value = Integer.parseInt(productId);
        return ResponseEntity.ok().body(productService.findOneProduct(value));
    }

}
