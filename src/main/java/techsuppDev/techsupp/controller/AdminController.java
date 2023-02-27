package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.service.AdminProductService;
import techsuppDev.techsupp.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminProductService adminProductService;

    @GetMapping("/")
    public String index() {
        return "redirect:/admin/Product/list";
    }

    @GetMapping("/list")
    public void list(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", adminProductService.getList(pageRequestDTO));
    }
}
