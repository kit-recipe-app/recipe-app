package com.kit.recipe.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IngredientsWithAmountDTO {
    private IngredientDTO ingredient;
    private AmountInformationDTO amount;
}
