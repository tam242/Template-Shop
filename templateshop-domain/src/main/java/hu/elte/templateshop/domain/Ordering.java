package hu.elte.templateshop.domain;

import hu.elte.templateshop.domain.enums.OrderStatus;
import hu.elte.templateshop.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Ordering {
    @Id
    @GeneratedValue
    private int id;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;

    @ManyToOne
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
