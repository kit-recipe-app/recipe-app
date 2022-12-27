package com.kit.recipe.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AmountInformationDTO {
    private double amount;
    private String unit;
}
