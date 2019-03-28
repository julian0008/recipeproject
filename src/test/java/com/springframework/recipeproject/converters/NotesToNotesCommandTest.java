package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.NotesCommand;
import com.springframework.recipeproject.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final Long LONG_VALUE = 1L;
    public static final String RECIPE_NOTES = "sample recipe notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setId(LONG_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(LONG_VALUE, command.getId());
        assertEquals(RECIPE_NOTES, command.getRecipeNotes());
    }
}