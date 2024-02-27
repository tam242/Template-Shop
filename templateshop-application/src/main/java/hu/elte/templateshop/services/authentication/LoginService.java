package hu.elte.templateshop.services.authentication;

import hu.elte.templateshop.domain.Admin;
import hu.elte.templateshop.domain.Customer;
import hu.elte.templateshop.repository.AdminRepository;
import hu.elte.templateshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;


    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return (customerRepository.existsByEmail(email) || adminRepository.existsByEmail(email));
    }

    public String getAccountTypeByEmail(String email) {
        if (customerRepository.existsByEmail(email)) {
            return "Customer";
        }
        return "Admin";
    }

    public void validateAccount(Customer customer, int currentUserId) {

    }

}
