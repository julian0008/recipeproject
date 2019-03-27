package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.IngredientCommand;
import com.springframework.recipeproject.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    UnitOfMeasureToUnitOfMeasureCommand uomConverter;
    RecipeToRecipeCommand recipeConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter, RecipeToRecipeCommand recipeConverter) {
        this.uomConverter = uomConverter;
        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null) {
            return null;
        }

        IngredientCommand command = new IngredientCommand();

        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setAmount(source.getAmount());
        command.setUom(uomConverter.convert(source.getUom()));
        command.setRecipe(recipeConverter.convert(source.getRecipe()));
        return command;
    }
}
