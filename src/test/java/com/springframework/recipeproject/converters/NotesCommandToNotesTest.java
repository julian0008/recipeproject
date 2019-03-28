package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.NotesCommand;
import com.springframework.recipeproject.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long LONG_VALUE = 1L;
    public static final String RECIPE_NOTES = "sample recipe notes";

    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesCommand command = new NotesCommand();
        command.setId(LONG_VALUE);
        command.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(LONG_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}