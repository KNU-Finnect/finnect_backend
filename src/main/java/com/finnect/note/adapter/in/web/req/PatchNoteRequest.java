package com.finnect.note.adapter.in.web.req;

import com.finnect.note.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PatchNoteRequest {
    private Long dealId;
    private Long noteId;
    private String title;
    private String bodyText;

    public Note toDomain(Long userId){
        return Note.builder()
                .dealId(this.dealId)
                .noteId(this.noteId)
                .title(this.title)
                .bodyText(this.bodyText)
                .lastEditor(userId)
                .build();
    }
}
