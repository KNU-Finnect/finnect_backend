package com.finnect.note.adapter.in.web.res;

import com.finnect.note.domain.state.NoteState;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
@Getter
public class DetailNoteResponse {
    private final Long noteId;
    private final String title;
    private final String bodyText;
    private final LocalDateTime createdDate;
    private final EditorResponse lastEditor;

    public DetailNoteResponse(NoteState noteState) {
        this.noteId = noteState.getNoteId();
        this.title = noteState.getTitle();
        this.bodyText = noteState.getBodyText();
        this.createdDate = noteState.getCreatedDate();
        this.lastEditor = null;
    }
}
@Getter
class EditorResponse{
    private final Long userId;
    private final String nickName;
    private final String role;
    @Builder
    public EditorResponse(Long userId, String nickName, String role) {
        this.userId = userId;
        this.nickName = nickName;
        this.role = role;
    }
}
