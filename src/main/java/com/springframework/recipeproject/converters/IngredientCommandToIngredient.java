package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.IngredientCommand;
import com.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    RecipeCommandToRecipe recipeConverter;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter, RecipeCommandToRecipe recipeConverter) {
        this.uomConverter = uomConverter;
        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {

        if(source == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setId(source.getId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUom(uomConverter.convert(source.getUom()));
        ingredient.setRecipe(recipeConverter.convert(source.getRecipe()));
        return ingredient;
    }
}