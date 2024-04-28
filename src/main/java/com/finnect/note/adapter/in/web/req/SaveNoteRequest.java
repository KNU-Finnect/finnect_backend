package com.finnect.note.adapter.in.web.req;

import com.finnect.note.domain.Note;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SaveNoteRequest {
    private Long dealId;
    private String title;
    private String bodyText;

    public Note toDomain(Long userId){
        return Note.builder()
                .dealId(dealId)
                .createdDate(ZonedDateTime
                        .now
                            (ZoneId.of("Asia/Seoul"))
                        .toLocalDateTime())
                .title(this.title)
                .bodyText(this.bodyText)
                .lastEditor(userId)
                .build();
    }
}
