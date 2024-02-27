package hu.elte.templateshop.repository;

import hu.elte.templateshop.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
