package hu.elte.templateshop.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    private List<Product> product;
}
