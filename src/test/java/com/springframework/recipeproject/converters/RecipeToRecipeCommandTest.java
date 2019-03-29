package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.RecipeCommand;
import com.springframework.recipeproject.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {
    public static final Long RECIPE_ID = 1L;
    public static final String RECIPE_DESCRIPTION = "sample recipe description";
    public static final Integer PREP_TIME = Integer.valueOf(30);
    public static final Integer COOK_TIME = Integer.valueOf(30);
    public static final Integer SERVINGS = Integer.valueOf(5);
    public static final String SOURCE = "www.samplesource.com";
    public static final String URL = "www.sampleurl.com";
    public static final String DIRECTIONS = "sample directions";
    private static final Difficulty DIFFICULTY = Difficulty.HARD;

    public static final Long INGREDIENT_ID_1 = 10L;
    public static final Long INGREDIENT_ID_2 = 20L;

    public static final Long CAT_ID_1 = 11L;
    public static final Long CAT_ID_2 = 12L;

    public static final Long NOTES_ID = 2L;

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand()
        );
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDescription(RECIPE_DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        //creating and setting categories
        Category cat1 = new Category();
        cat1.setId(CAT_ID_1);

        Category cat2 = new Category();
        cat2.setId(CAT_ID_2);

        recipe.getCategories().add(cat1);
        recipe.getCategories().add(cat2);

        //creating and setting ingredients
        Ingredient ing1 = new Ingredient();
        ing1.setId(INGREDIENT_ID_1);

        Ingredient ing2 = new Ingredient();
        ing2.setId(INGREDIENT_ID_2);

        recipe.getIngredients().add(ing1);
        recipe.getIngredients().add(ing2);

        ////creating and setting notes
        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertNotNull(command.getNotes());
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(RECIPE_DESCRIPTION, command.getDescription());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(NOTES_ID, command.getNotes().getId());
        assertEquals(2, command.getIngredients().size());
        assertEquals(2, command.getCategories().size());
    }
}