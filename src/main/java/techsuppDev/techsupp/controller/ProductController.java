package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/productMain")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/*")
    public ModelAndView LinkToProductMain() {
        ModelAndView productMain = new ModelAndView("/product/productMain");
        return productMain;
    }


}

