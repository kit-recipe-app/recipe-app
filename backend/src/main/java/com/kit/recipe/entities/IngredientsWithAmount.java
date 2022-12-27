package com.kit.recipe.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class IngredientsWithAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredients_with_amount", nullable = false)
    private Long id;


    @OneToOne(targetEntity = AmountInformation.class, cascade = CascadeType.ALL)
    private AmountInformation amountInformation;


    @OneToOne(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    private Ingredient ingredient;


}
