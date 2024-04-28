package com.finnect.note.adapter.in.web.res;

import com.finnect.note.domain.state.NoteState;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NoteListResponse {

    private Long dealId;
    private List<SimpleNoteResponse> notes;

    public NoteListResponse(Long dealId, List<NoteState> noteStates) {
        this.dealId = dealId;
        this.notes = noteStates.stream()
                .map(noteState ->
                    SimpleNoteResponse.builder()
                            .noteId(noteState.getNoteId())
                            .title(noteState.getTitle())
                            .bodyText(noteState.getBodyText())
                            .createdDate(noteState.getCreatedDate())
                            .build()
                ).toList();
    }
}
@Getter
class SimpleNoteResponse{
    private Long noteId;
    private String title;
    private String bodyText;
    private LocalDateTime createdDate;
    @Builder
    public SimpleNoteResponse(Long noteId, String title, String bodyText, LocalDateTime createdDate) {
        this.noteId = noteId;
        this.title = title;
        this.bodyText = bodyText;
        this.createdDate = createdDate;
    }
}