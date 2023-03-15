package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@RequestMapping("/productMain")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/*")
    public ModelAndView LinkToProductMain(HttpServletRequest req) {

        ModelAndView productMain = new ModelAndView("/product/productMain");
        return productMain;
    }

    @GetMapping("/createtest")
    public void createtestcon() {
        productService.createtestdataproduct();
        System.out.println("======");
        System.out.println("controller");
    }


}

