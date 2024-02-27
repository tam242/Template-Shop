package hu.elte.templateshop.services;

import hu.elte.templateshop.domain.Admin;
import hu.elte.templateshop.domain.Customer;
import hu.elte.templateshop.domain.Product;
import hu.elte.templateshop.repository.AdminRepository;
import hu.elte.templateshop.repository.CustomerRepository;
import hu.elte.templateshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataGenerator {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createTestData() {
        /*Customer customer1 = createCustomer("user1@test.org");
        customerRepository.save(customer1);

        Customer customer2 = createCustomer("user2@test.org");
        customerRepository.save(customer2);

        Admin admin = new Admin("admin@ts.org", "admin");
        adminRepository.save(admin);*/

        for (Product product : createProducts()) {
            productRepository.save(product);
        }

    }

    private Customer createCustomer(String email) {
        return new Customer(email, "test");
    }

    private List<Product> createProducts() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product("Beauty shop", new BigDecimal(100), "beauty");
        productList.add(product);

        product = new Product("Traversal ", new BigDecimal(300), "traversal_starter");
        productList.add(product);

        product = new Product("Creative Studio", new BigDecimal(700), "creative_studio");
        productList.add(product);

        product = new Product("Dance Classes", new BigDecimal(500), "dance_classes");
        productList.add(product);

        product = new Product("Driving Test Appointment", new BigDecimal(200), "driving_test_appointment");
        productList.add(product);

        return productList;
    }
}
