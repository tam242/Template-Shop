package hu.elte.templateshop.repository;

import hu.elte.templateshop.domain.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
}
