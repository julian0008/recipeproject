package com.springframework.recipeproject.services;

import com.springframework.recipeproject.commands.RecipeCommand;
import com.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long idToDelete);

}
