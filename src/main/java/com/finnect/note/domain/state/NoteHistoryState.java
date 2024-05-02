package com.finnect.note.domain.state;

import java.time.LocalDateTime;

public interface NoteHistoryState {
    Long noteHistoryId();
    Long noteId();
    String historyTitle();
    String historyBodyText();
    LocalDateTime editedDate();
    Long editor();
}
