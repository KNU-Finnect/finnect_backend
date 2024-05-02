package com.finnect.note.application.port.in;

import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;

public interface SaveNoteUseCase {

    NoteState saveNewNote(Note note);

    NoteState patchNote(Note note);
}
