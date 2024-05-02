package com.finnect.note.application.port.out;

import com.finnect.deal.application.DealState;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;

public interface SaveNotePort {

    Note saveNote(NoteState noteState);
    Note patchNote(NoteState noteState);
}
