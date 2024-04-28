package com.finnect.note.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.note.adapter.in.web.res.NoteListResponse;
import com.finnect.note.application.port.in.LoadNoteUseCase;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoteController {
    private final LoadNoteUseCase loadNoteUseCase;
    @GetMapping("/workspaces/deals/{dealId}/notes")
    public ResponseEntity<ApiResult<NoteListResponse>> loadNotes(@PathVariable Long dealId){
        List<NoteState> notes = loadNoteUseCase.loadNotes(
                Note.builder()
                        .dealId(dealId)
                        .build()
        );

        return ResponseEntity.ok
                (ApiUtils.success(HttpStatus.OK
                        , new NoteListResponse(dealId, notes)));
    }
}
