package com.finnect.note.application.port.out;

import com.finnect.note.domain.NoteHistory;
import com.finnect.note.domain.state.NoteState;
import java.util.List;

public interface LoadNoteHistoryPort {

    List<NoteHistory> loadHistory(NoteState noteState);
}
