package hu.elte.templateshop.controllers;

import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.exceptions.BusinessException;
import hu.elte.templateshop.services.EmailSenderService;
import hu.elte.templateshop.services.admin.ProductService;
import hu.elte.templateshop.webdomains.CartItems;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@Controller
public class ShoppingCartController {
    private static final long serialVersionUID = 1L;

    private Cookie getCartCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("shoppingcart")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    @GetMapping("/removeProduct")
    public String deleteProduct(@RequestParam(name="id") int productId, Model model) {
        //ToDo: removes template from users cookies
        return "index";
    }


    @GetMapping("/shoppingcart")
    public String doGet(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        Cookie cartCookie = getCartCookies(request);
        if (cartCookie != null) {
            String cookieString = cartCookie.getValue();
            String[] cartElements = cookieString.split("-");
            Iterable<Product> products = productService.getProducts();
            List<Product> giveBacks = new ArrayList<Product>();
            for (String product : cartElements) {
                for (Product item : products) {
                    if (Integer.parseInt(product) == item.getId()) {
                       giveBacks.add(item);
                    }
                }
            }
            request.setAttribute("products", giveBacks);
        }
        return"shoppingcart";
    }

    @PostMapping("/shoppingcart")
    public void doPost(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException {
        String newElement = req.getParameter("element");
        Cookie cartCookie = getCartCookies(req);
        if (cartCookie == null){
            cartCookie = new Cookie("shoppingcart",newElement);
        }
        else {
            String currentCookieString = cartCookie.getValue();
            List<String> currentElements = new ArrayList<>(Arrays.asList(currentCookieString.split("-")));
            currentElements.add(newElement);
            StringBuilder newCookieStringBuilder = new StringBuilder();
            for (Iterator<String> iter = currentElements.iterator(); iter.hasNext(); ) {
                String element = iter.next();
                newCookieStringBuilder.append(element);
                if (iter.hasNext()) {
                    newCookieStringBuilder.append("-");
                }
            }
            cartCookie.setValue(newCookieStringBuilder.toString());
        }
        resp.addCookie(cartCookie);
        resp.sendRedirect("templates");
    }

    @Autowired
    private ProductService productService;
}



