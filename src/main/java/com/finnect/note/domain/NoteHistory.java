package com.finnect.note.domain;

import com.finnect.note.domain.state.NoteHistoryState;
import java.time.LocalDateTime;
import lombok.Builder;

public class NoteHistory implements NoteHistoryState {
    private Long noteHistoryId;
    private Long noteId;
    private String historyTitle;
    private String historyBodyText;
    private LocalDateTime editedDate;
    private Long editor;

    @Builder
    public NoteHistory(Long noteHistoryId, Long noteId, String historyTitle, String historyBodyText,
                       LocalDateTime editedDate, Long editor) {
        this.noteHistoryId = noteHistoryId;
        this.noteId = noteId;
        this.historyTitle = historyTitle;
        this.historyBodyText = historyBodyText;
        this.editedDate = editedDate;
        this.editor = editor;
    }

    @Override
    public Long noteHistoryId() {
        return this.noteHistoryId;
    }
    @Override
    public Long noteId() {
        return this.noteId;
    }

    @Override
    public String historyTitle() {
        return this.historyTitle;
    }

    @Override
    public String historyBodyText() {
        return this.historyBodyText;
    }

    @Override
    public LocalDateTime editedDate() {
        return this.editedDate;
    }

    @Override
    public Long editor() {
        return this.editor;
    }

    @Override
    public String toString() {
        return "NoteHistory{" +
                "noteHistoryId=" + noteHistoryId +
                ", noteId=" + noteId +
                ", historyTitle='" + historyTitle + '\'' +
                ", historyBodyText='" + historyBodyText + '\'' +
                ", editedDate=" + editedDate +
                ", editor=" + editor +
                '}';
    }
}
