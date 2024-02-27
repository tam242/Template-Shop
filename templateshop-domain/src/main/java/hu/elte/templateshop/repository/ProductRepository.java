package hu.elte.templateshop.repository;

import hu.elte.templateshop.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
