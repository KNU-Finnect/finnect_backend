package com.finnect.note.adapter.out.persistence;

import com.finnect.note.domain.NoteHistory;
import com.finnect.note.domain.state.NoteHistoryState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


@Entity(name = "note_history")
public class NoteHistoryEntity implements NoteHistoryState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteHistoryId;

    private Long noteId;
    private String historyTitle;
    @Column(length = 50000)
    private String historyBodyText;
    private LocalDateTime editedDate;
    private Long editor;
    @Builder
    public NoteHistoryEntity(NoteEntity note) {
        this.noteId = note.getNoteId();
        this.historyTitle = note.getTitle();
        this.historyBodyText = note.getBodyText();
        this.editedDate = note.getCreatedDate();
        this.editor = note.getLastEditor();
    }

    protected NoteHistoryEntity() {
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
    public NoteHistory toDomain(){
        return NoteHistory.builder()
                .noteHistoryId(this.noteHistoryId)
                .noteId(this.noteId)
                .historyTitle(this.historyTitle)
                .historyBodyText(this.historyBodyText)
                .editedDate(this.editedDate)
                .editor(this.editor)
                .build();
    }
}
