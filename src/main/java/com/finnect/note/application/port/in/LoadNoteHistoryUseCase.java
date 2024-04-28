package com.finnect.note.application.port.in;

import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteHistoryState;
import java.util.List;

public interface LoadNoteHistoryUseCase {

    List<NoteHistoryState> loadNoteHistory(Note note);
}
