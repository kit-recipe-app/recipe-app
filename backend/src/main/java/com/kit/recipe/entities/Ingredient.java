package com.kit.recipe.entities;


import javax.persistence.*;


@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredient_id")
    private Long id;

    private String name;


}
