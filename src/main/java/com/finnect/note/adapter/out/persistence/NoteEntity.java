package com.finnect.note.adapter.out.persistence;

import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "note")
public class NoteEntity implements NoteState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    private Long dealId;
    private String title;
    @Column(length = 50000)
    private String bodyText;
    private LocalDateTime createdDate;
    private Long lastEditor;

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

    public Note toDomain(){
        return Note.builder()
                .noteId(this.noteId)
                .dealId(this.dealId)
                .title(this.title)
                .bodyText(this.bodyText)
                .createdDate(this.createdDate)
                .lastEditor(this.lastEditor)
                .build();
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "noteId=" + noteId +
                ", dealId=" + dealId +
                ", title='" + title + '\'' +
                ", bodyText='" + bodyText + '\'' +
                ", createdDate=" + createdDate +
                ", lastEditor=" + lastEditor +
                '}';
    }
}
