package hu.elte.templateshop.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin extends Customer {

    private int adminLevel;

    public Admin() {

    }

    public Admin(String name, String password) {
        super(name, password);
    }

}
