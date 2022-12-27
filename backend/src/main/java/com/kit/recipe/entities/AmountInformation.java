package com.kit.recipe.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class AmountInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "amount_id", nullable = false)
    private Long id;
    private String unit;
    private double amount;

    public AmountInformation(double amount, String unit) {
        this.amount = amount;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AmountInformation otherAmountInformation = (AmountInformation) o;
        return id != null && Objects.equals(id, otherAmountInformation.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
