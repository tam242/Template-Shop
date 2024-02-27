package hu.elte.templateshop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TestEntity {
    @Id
    @GeneratedValue
    private int Id;

    private String name;
}
