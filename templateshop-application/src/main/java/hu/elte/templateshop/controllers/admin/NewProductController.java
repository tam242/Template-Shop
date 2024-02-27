package hu.elte.templateshop.controllers.admin;

import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.repository.ProductRepository;
import hu.elte.templateshop.services.admin.ProductService;
import hu.elte.templateshop.webdomains.ProductRequest;
import hu.elte.templateshop.webdomains.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NewProductController {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;

    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("productRequest", new ProductRequest());
        return "admin/newProduct";
    }

    @PostMapping("/newProduct")
    public String newProduct(Model model, @Valid ProductRequest productRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/newProduct";
        }

        Product product = productMapper.map(productRequest);
        productService.saveProduct(product);
        model.addAttribute("msg", "New product with name " + productRequest.getName() + " added.");
        return "shared/notify";
    }
}
