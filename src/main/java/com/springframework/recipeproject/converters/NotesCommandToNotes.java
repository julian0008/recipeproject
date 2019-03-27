package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.NotesCommand;
import com.springframework.recipeproject.commands.RecipeCommand;
import com.springframework.recipeproject.domain.Notes;
import com.springframework.recipeproject.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    RecipeCommandToRecipe recipeConverter;

    public NotesCommandToNotes(RecipeCommandToRecipe recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        notes.setRecipe(recipeConverter.convert(source.getRecipe()));
        return notes;
    }
}