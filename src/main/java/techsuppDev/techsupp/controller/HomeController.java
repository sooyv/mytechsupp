package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.config.UserDetailsimpl;

import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.AdminProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private AdminProductService adminProductService;

    @Autowired
    public HomeController(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }


    @GetMapping("/")
    public String main(Model model) {

        // product 5개 random
        List<ProductDTO> randomProducts = adminProductService.getRandomProduct();
        model.addAttribute("products", randomProducts);

        return "/main/main";
    }

    // 서비스 소개
    @GetMapping("/introduce/service")
    public String introduceService() {
        return "/introduceService/introduceService";
    }
}
