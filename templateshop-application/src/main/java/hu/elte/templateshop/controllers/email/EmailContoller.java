package hu.elte.templateshop.controllers.email;

import hu.elte.templateshop.controllers.CustomExceptionHandler;
import hu.elte.templateshop.services.EmailSenderService;
import hu.elte.templateshop.webdomains.CartItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
public class EmailContoller {
    @Autowired
    private EmailSenderService emailSenderService;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @GetMapping("/email")
    public String sendGreetingMail(Model model) {

        model.addAttribute("msg", "Email sent successfully");
        return "shared/notify";
    }
    private static final long serialVersionUID = 1L;
    private Cookie getCartCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("shoppingcart")) {
                    return cookie;
                }
            }
        }
        return null;
    }

    @PostMapping("/email")
    public String doPost(HttpServletRequest req, HttpServletResponse resp, Model model) throws ServletException, IOException, MessagingException {
        Cookie cartCookie = getCartCookies(req);
        String currentCookieString = cartCookie.getValue();
        String[] cartElements = currentCookieString.split("-");
        HttpSession session = req.getSession();
        String currentUserEmail = session.getAttribute("email").toString();
        emailSenderService.sendMessageWithAttachment(currentUserEmail, cartElements);

        model.addAttribute("msg", "Your products on delivey by email :)");
        return "shared/notify";
    }
}


