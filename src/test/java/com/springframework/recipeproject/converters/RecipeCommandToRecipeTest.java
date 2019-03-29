package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.CategoryCommand;
import com.springframework.recipeproject.commands.IngredientCommand;
import com.springframework.recipeproject.commands.NotesCommand;
import com.springframework.recipeproject.commands.RecipeCommand;
import com.springframework.recipeproject.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

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

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory()
        );
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDescription(RECIPE_DESCRIPTION);
        command.setPrepTime(PREP_TIME);
        command.setCookTime(COOK_TIME);
        command.setServings(SERVINGS);
        command.setSource(SOURCE);
        command.setUrl(URL);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);

        //creating and setting categories
        CategoryCommand cat1 = new CategoryCommand();
        cat1.setId(CAT_ID_1);

        CategoryCommand cat2 = new CategoryCommand();
        cat2.setId(CAT_ID_2);

        command.getCategories().add(cat1);
        command.getCategories().add(cat2);

        //creating and setting ingredients
        IngredientCommand ing1 = new IngredientCommand();
        ing1.setId(INGREDIENT_ID_1);

        IngredientCommand ing2 = new IngredientCommand();
        ing2.setId(INGREDIENT_ID_2);

        command.getIngredients().add(ing1);
        command.getIngredients().add(ing2);

        ////creating and setting notes
        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        command.setNotes(notes);

        //when
        Recipe recipe = converter.convert(command);

        //then
        assertNotNull(recipe);
        assertNotNull(recipe.getNotes());
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(RECIPE_DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
    }
}