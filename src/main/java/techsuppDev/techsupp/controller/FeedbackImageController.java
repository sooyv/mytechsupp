package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class FeedbackImageController {


//    reponse body 하면 json으로 그냥 보내줌
    @RequestMapping("/image")
    @JsonIgnore
    @ResponseBody
    public ResponseEntity testajax(HttpServletRequest req) {
//    public ResponseBody testajax(HttpServletRequest req) {
        System.out.println("test image uploac");
        System.out.println("========");
        System.out.println("============");
        System.out.println(req);
        System.out.println(req.getParameterValues("fileObj"));
        System.out.println(req.getParameterValues("fileObj2"));
       return ResponseEntity.ok().body(req.toString());
//        Object asdf = req;
//       return asdf;

        
    }
}
