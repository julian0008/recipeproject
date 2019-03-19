package com.springframework.recipeproject.services;

import com.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
