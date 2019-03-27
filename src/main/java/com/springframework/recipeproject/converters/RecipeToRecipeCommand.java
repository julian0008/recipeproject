package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.RecipeCommand;
import com.springframework.recipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    IngredientToIngredientCommand ingredientConverter;
    NotesToNotesCommand notesConverter;
    CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();

        command.setId(source.getId());
        command.setDescription(source.getDescription());
        command.setPrepTime(source.getPrepTime());
        command.setCookTime(source.getCookTime());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setDirections(source.getDirections());
        command.setDifficulty(source.getDifficulty());
        command.setNotes(notesConverter.convert(source.getNotes()));

        //setting ingredients
        if(source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(
                    ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient))
            );
        }

        //setting categories
        if(source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(
                    category -> command.getCategories().add(categoryConverter.convert(category))
            );
        }

        return command;
    }
}
