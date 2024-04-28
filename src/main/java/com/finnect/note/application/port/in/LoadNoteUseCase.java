package com.finnect.note.application.port.in;

import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import java.util.List;

public interface LoadNoteUseCase {

    List<NoteState> loadNotes(Note note);
}
