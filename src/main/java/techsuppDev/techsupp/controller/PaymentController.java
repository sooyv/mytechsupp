package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invest")
public class PaymentController {
    @GetMapping("/*")
    public ModelAndView LinkToSpecificInvestment() {
        ModelAndView investmentProduct = new ModelAndView("/product/productInvesting");
        return investmentProduct;
    }
}
