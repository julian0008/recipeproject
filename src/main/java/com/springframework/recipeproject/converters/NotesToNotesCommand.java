package com.springframework.recipeproject.converters;

import com.springframework.recipeproject.commands.NotesCommand;
import com.springframework.recipeproject.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    RecipeToRecipeCommand recipeConverter;

    public NotesToNotesCommand(RecipeToRecipeCommand recipeConverter) {
        this.recipeConverter = recipeConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {

        if(source == null) {
            return null;
        }

        NotesCommand command = new NotesCommand();
        command.setId(source.getId());
        command.setRecipeNotes(source.getRecipeNotes());
        command.setRecipe(recipeConverter.convert(source.getRecipe()));
        return command;
    }
}
