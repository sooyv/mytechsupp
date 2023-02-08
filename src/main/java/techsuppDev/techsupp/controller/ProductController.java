package techsuppDev.techsupp.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;



//    localhost:8080으로 접속 하면 뜨는 화면
    // url로 접속은 됨 근데 api 받아오는 것이 현재 안되는 상황
    @GetMapping("/")
    public String itemList(Model model) {
        return "product/productSelect";
    }

    // db에서 검색해서 json 형식으로 client 에게 보내주기 성공
    @GetMapping("/api")
    @ResponseBody
    public List<Product> apiSend() {
        List<Product> products = productService.findProduct();
        return products;
    }




}
