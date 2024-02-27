package hu.elte.templateshop.controllers.authentication;

import hu.elte.templateshop.domain.Customer;
import hu.elte.templateshop.webdomains.RegisterRequest;
import hu.elte.templateshop.webdomains.mappers.RegisterMapper;
import hu.elte.templateshop.services.authentication.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private RegisterMapper registerMapper;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("RegisterRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterRequest registerRequest, Model model, BindingResult bindingResult, HttpServletRequest request) {
        Customer customer = registerMapper.map(registerRequest);

        Boolean error = false;
        if (registerService.existsByEmail(customer.getEmail())) {
            model.addAttribute("RegisterRequest", registerRequest);
            model.addAttribute("emailTaken", "Email has already been taken");
            error = true;
        }

        if (registerService.existsByUsername(customer.getUsername())) {
            model.addAttribute("RegisterRequest", registerRequest);
            model.addAttribute("usernameTaken", "Username has already been taken");
            error = true;
        }

        if (error) {
            return "register";
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", customer.getId());
        session.setAttribute("username", customer.getUsername());
        session.setAttribute("email", customer.getEmail());

        registerService.saveCustomer(customer);
        model.addAttribute("msg", "Registration successful");
        return "shared/notify";
    }
}
