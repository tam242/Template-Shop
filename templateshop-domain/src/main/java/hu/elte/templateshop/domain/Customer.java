package hu.elte.templateshop.domain;

import hu.elte.templateshop.domain.enums.UserState;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private String password;

    private BigDecimal balance;
    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private Ordering order;

    @Enumerated(EnumType.STRING)
    private UserState userState;


    public Customer() {
    }

    public Customer(String email, String password)
    {
        this.email = email;
        this.password = password;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

}