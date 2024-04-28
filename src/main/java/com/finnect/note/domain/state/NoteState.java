package com.finnect.note.domain.state;

import java.time.LocalDateTime;

public interface NoteState {

    Long getNoteId();
    Long getDealId();
    String getTitle();
    String getBodyText();
    LocalDateTime getCreatedDate();
    Long getLastEditor();
}
