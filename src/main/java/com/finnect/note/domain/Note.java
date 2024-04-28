package com.finnect.note.domain;

import com.finnect.note.domain.state.NoteState;
import java.time.LocalDateTime;
import lombok.Builder;

public class Note implements NoteState {
    private Long noteId;

    private Long dealId;
    private String title;
    private String bodyText;
    private LocalDateTime createdDate;
    private Long lastEditor;

    @Builder
    public Note(Long noteId, Long dealId, String title, String bodyText, LocalDateTime createdDate, Long lastEditor) {
        this.noteId = noteId;
        this.dealId = dealId;
        this.title = title;
        this.bodyText = bodyText;
        this.createdDate = createdDate;
        this.lastEditor = lastEditor;
    }

    @Override
    public Long getNoteId() {
        return this.noteId;
    }

    @Override
    public Long getDealId() {
        return this.dealId;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getBodyText() {
        return this.bodyText;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public Long getLastEditor() {
        return this.lastEditor;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", dealId=" + dealId +
                ", title='" + title + '\'' +
                ", bodyText='" + bodyText + '\'' +
                ", createdDate=" + createdDate +
                ", lastEditor=" + lastEditor +
                '}';
    }
}
