package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.IngredientCommand;
import com.springframework.recipeproject.domain.Ingredient;
import com.springframework.recipeproject.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long INGREDIENT_ID = 1L;
    public static final String INGREDIENT_DESCRIPTION = "sample ingredient description";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = 2L;
    public static final String UOM_DESCRIPTION = "sample uom description";

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        uom.setDescription(UOM_DESCRIPTION);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUom(uom);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertNotNull(command);
        assertNotNull(command.getUom());
        assertEquals(INGREDIENT_ID, command.getId());
        assertEquals(INGREDIENT_DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(UOM_ID, command.getUom().getId());
        assertEquals(UOM_DESCRIPTION, command.getUom().getDescription());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID);
        ingredient.setDescription(INGREDIENT_DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertNotNull(command);
        assertNull(command.getUom());
        assertEquals(INGREDIENT_ID, command.getId());
        assertEquals(INGREDIENT_DESCRIPTION, command.getDescription());
        assertEquals(AMOUNT, command.getAmount());
    }
}