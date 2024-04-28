package com.finnect.note.application.port.out;

import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import java.util.List;

public interface LoadNotePort {

    List<Note> loadNoteByDealId(NoteState noteState);
}
