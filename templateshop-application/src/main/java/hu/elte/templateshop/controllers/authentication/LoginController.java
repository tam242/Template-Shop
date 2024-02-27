package hu.elte.templateshop.controllers.authentication;

import hu.elte.templateshop.domain.Admin;
import hu.elte.templateshop.domain.Customer;
import hu.elte.templateshop.services.authentication.LoginService;
import hu.elte.templateshop.webdomains.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginRequest", loginRequest);
            return "login";
        }

        if (!loginService.existsByEmail(loginRequest.getEmail())) {
            model.addAttribute("loginRequest", loginRequest);
            model.addAttribute("incorrectLogin", "Login failed. Incorrect email or password");
            return "login";
        }

        String role = loginService.getAccountTypeByEmail(loginRequest.getEmail());
        HttpSession session = request.getSession();

        if (role.equals("Customer")) {
            Customer customer = loginService.findCustomerByEmail(loginRequest.getEmail());
            if (!loginRequest.getPassword().equals(customer.getPassword())) {
                model.addAttribute("loginRequest", loginRequest);
                model.addAttribute("incorrectLogin", "Login failed. Incorrect email or password");
                return "login";
            } else {
                session.setAttribute("id", customer.getId());
                session.setAttribute("username", customer.getUsername());
                session.setAttribute("role", "Customer");
                session.setAttribute("email", customer.getEmail());
            }
        } else if (role.equals("Admin")) {
            Admin admin = loginService.findAdminByEmail(loginRequest.getEmail());
            if (!loginRequest.getPassword().equals(admin.getPassword())) {
                model.addAttribute("loginRequest", loginRequest);
                model.addAttribute("incorrectLogin", "Login failed. Incorrect email or password");
                return "login";
            } else {
                session.setAttribute("id", admin.getId());
                session.setAttribute("username", admin.getUsername());
                session.setAttribute("role", "Admin");
                session.setAttribute("email", admin.getEmail());
            }
        }

        model.addAttribute("msg", "You are logged in successfully");
        return "shared/notify";
    }
}
