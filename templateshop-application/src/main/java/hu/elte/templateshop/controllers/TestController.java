package hu.elte.templateshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class TestController {

    @GetMapping("/test")
    public String greeting(Model model) {
        String now = LocalDateTime.now().toString();
        model.addAttribute("now", now);
        return "test";
    }

}