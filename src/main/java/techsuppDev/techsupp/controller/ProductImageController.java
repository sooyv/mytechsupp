package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import techsuppDev.techsupp.service.ProductImageService;

@Controller
@AllArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

}
