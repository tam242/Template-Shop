package hu.elte.templateshop.domain;

import hu.elte.templateshop.domain.enums.PaymentStatus;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    //Elementcollection
}
