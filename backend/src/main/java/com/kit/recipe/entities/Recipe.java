package com.kit.recipe.entities;


import javax.persistence.*;
import java.util.List;


@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "recipe_id")
    private Long id;

    private String name;


    @OneToMany(targetEntity = IngredientsWithAmount.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_foreign_key", referencedColumnName = "recipe_id")
    private List<IngredientsWithAmount> ingredients;


    public String getName() {
        return name;
    }
}
