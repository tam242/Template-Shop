package hu.elte.templateshop.services.authentication;

import hu.elte.templateshop.domain.Customer;
import hu.elte.templateshop.repository.AdminRepository;
import hu.elte.templateshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public boolean existsByEmail(String email) {
        return (customerRepository.existsByEmail(email) || adminRepository.existsByEmail(email));
    }

    public boolean existsByUsername(String username) {
        return (customerRepository.existsByUsername(username) || adminRepository.existsByUsername(username));
    }

}
