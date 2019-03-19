package com.springframework.recipeproject.bootstrap;

import com.springframework.recipeproject.domain.*;
import com.springframework.recipeproject.repositories.CategoryRepository;
import com.springframework.recipeproject.repositories.RecipeRepository;
import com.springframework.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
         recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        //get categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected UOM Not Found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Sample Direction");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Sample Guacamole Notes");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avocado", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaSpoonUom))
                .addIngredient(new Ingredient("fresh lemon juice", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("thinly sliced green onion", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("serrano chiles", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom))
                .addIngredient(new Ingredient("grated black pepper", new BigDecimal(2), dashUom))
                .addIngredient(new Ingredient("ripe tomato", new BigDecimal(0.5), eachUom));

        guacRecipe.addCategory(americanCategory)
                .addCategory(mexicanCategory);

        // adding to return list
        recipes.add(guacRecipe);

        return recipes;
    }
}
