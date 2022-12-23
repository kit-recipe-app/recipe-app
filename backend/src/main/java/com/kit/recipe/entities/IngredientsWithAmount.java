package com.kit.recipe.entities;


import javax.persistence.*;

@Entity
public class IngredientsWithAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredients_with_amount", nullable = false)
    private Long id;


    @OneToOne(targetEntity = AmountInformation.class, cascade = CascadeType.ALL)
    private AmountInformation amountInformation;


    @OneToOne(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    private Ingredient ingredient;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
