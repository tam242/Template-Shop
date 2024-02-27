package hu.elte.templateshop.controllers.admin;

import hu.elte.templateshop.services.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsContoller {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "admin/products";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(name="id") int productId, Model model) {
        productService.deleteById(productId);
        return "redirect:/products";
    }
}
