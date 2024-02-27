package hu.elte.templateshop.webdomains.mappers;

import hu.elte.templateshop.webdomains.RegisterRequest;
import hu.elte.templateshop.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {

    public Customer map(RegisterRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        return customer;
    }
}
