package hu.elte.templateshop.repository;

import hu.elte.templateshop.domain.Ordering;
import org.springframework.data.repository.CrudRepository;

public interface OrderingRepository extends CrudRepository<Ordering, Integer> {
}
