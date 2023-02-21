package techsuppDev.techsupp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainControllerDelete {
    @GetMapping("/")
    public String LinkToMain() {

        return "/main/main";
    }
}
