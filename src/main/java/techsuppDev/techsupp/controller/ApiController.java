package techsuppDev.techsupp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import techsuppDev.techsupp.service.ProductService;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

//@org.springframework.web.bind.annotation.RestController
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final ProductService productService;

//    productMain 에서 5개 보여주기
    @RequestMapping("/products/{pageNumber}")
    public ResponseEntity findFiveProduct(@PathVariable("pageNumber") int pageNumber) {
        if (pageNumber != 0) {
            pageNumber = pageNumber * 5;
        }
        return ResponseEntity.ok().body(productService.findFiveProduct(pageNumber));
    }

//    페이징을 위해서 product table 상품 갯수 가져오기
    @RequestMapping("/paging/{pagingNumber}")
    public ResponseEntity numberOfProductsSend(@PathVariable("pagingNumber") int pagingNumber) {
        pagingNumber = pagingNumber * 50;

        return ResponseEntity.ok().body((productService.getNumberOfProduct(pagingNumber)));
    }
////    상품 선택 후 가져오는 하나의 상품 정보
    @RequestMapping("/product")
    public ResponseEntity productOne(HttpServletRequest request) {
        String productId = request.getParameter("num");
        Long value = Long.parseLong(productId);
        return ResponseEntity.ok().body(productService.findOneProduct(value));
    }

}
