package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import techsuppDev.techsupp.DTO.PageRequestDTO;
import techsuppDev.techsupp.DTO.ProductDTO;
import techsuppDev.techsupp.service.AdminProductService;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminProductService adminProductService;



    @GetMapping("/list")
    public String list(@ModelAttribute PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", adminProductService.getList(pageRequestDTO));
        return "admin/Product/list";
    }



    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("productForm", new ProductDTO());
        return "admin/Product/create";
    }

    @PostMapping("/register")
    public String registerPost(ProductDTO productDTO,
                               RedirectAttributes redirectAttributes,
                               @RequestParam MultipartFile file) throws IOException {

        Long productId = adminProductService.register(productDTO);
        redirectAttributes.addFlashAttribute("msg", productId);

        if (!file.isEmpty()) {
            String filename = file.getOriginalFilename();
            log.info("file.getOriginalFilename = {}", filename);

            String fullPath = "C:/Temp/Upload" + filename;
            file.transferTo(new File(fullPath));

        }

        return "redirect:/admin/Product/list";
    }
}
