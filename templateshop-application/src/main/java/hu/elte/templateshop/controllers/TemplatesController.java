package hu.elte.templateshop.controllers;

import hu.elte.templateshop.services.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class TemplatesController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private ProductService productService;

    @GetMapping("/templates")
    public String templates(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        model.addAttribute("templates", productService.getProducts());
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect("/login");
        }
        return "templates";
    }
}
