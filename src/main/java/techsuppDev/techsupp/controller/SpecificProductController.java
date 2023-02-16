package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productSelect")
public class SpecificProductController {
    @GetMapping("/**")
    public ModelAndView LinkToSpecific() {
        ModelAndView specific = new ModelAndView("/product/productSelect");
        return specific;
    }
}
