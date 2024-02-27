package hu.elte.templateshop.repository;

import hu.elte.templateshop.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
