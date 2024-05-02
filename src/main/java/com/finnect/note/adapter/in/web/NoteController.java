package com.finnect.note.adapter.in.web;

import com.finnect.common.ApiUtils;
import com.finnect.common.ApiUtils.ApiResult;
import com.finnect.note.adapter.in.web.req.PatchNoteRequest;
import com.finnect.note.adapter.in.web.req.SaveNoteRequest;
import com.finnect.note.adapter.in.web.res.DetailNoteResponse;
import com.finnect.note.adapter.in.web.res.NoteListResponse;
import com.finnect.note.adapter.in.web.res.SimpleNoteHistoryListResponse;
import com.finnect.note.application.port.in.LoadNoteHistoryUseCase;
import com.finnect.note.application.port.in.LoadNoteUseCase;
import com.finnect.note.application.port.in.SaveNoteUseCase;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteHistoryState;
import com.finnect.note.domain.state.NoteState;
import java.util.List;
import javax.swing.text.html.HTML;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NoteController {
    private final LoadNoteUseCase loadNoteUseCase;
    private final LoadNoteHistoryUseCase loadNoteHistoryUseCase;
    private final SaveNoteUseCase saveNoteUseCase;

    @PostMapping("/workspace/deals/{dealId}/notes")
    public ResponseEntity<ApiResult<DetailNoteResponse>> saveNote(@RequestBody SaveNoteRequest request,
                                                                  @PathVariable Long dealId){
        NoteState noteState = saveNoteUseCase.saveNewNote(request.toDomain(1L));

        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.CREATED
                        , new DetailNoteResponse(noteState))
                , HttpStatus.CREATED
        );
    }

    @PatchMapping("/workspace/deals/{dealId}/notes/{noteId}")
    public ResponseEntity<ApiResult<DetailNoteResponse>> changeNoteInfo(@RequestBody PatchNoteRequest request,
                                                                        @PathVariable Long dealId,
                                                                        @PathVariable Long noteId){
        NoteState noteState = saveNoteUseCase.patchNote(request.toDomain(1L));

        return new ResponseEntity<>(
                ApiUtils.success(HttpStatus.OK
                        , new DetailNoteResponse(noteState))
                , HttpStatus.CREATED
        );
    }

    @GetMapping("/workspaces/deals/{dealId}/notes")
    public ResponseEntity<ApiResult<NoteListResponse>> loadNotes(@PathVariable Long dealId){
        List<NoteState> notes = loadNoteUseCase.loadNotesInDeal(
                Note.builder()
                        .dealId(dealId)
                        .build()
        );

        return ResponseEntity.ok
                (ApiUtils.success(HttpStatus.OK
                        , new NoteListResponse(dealId, notes)));
    }

    @GetMapping("/workspaces/deals/{dealId}/notes/{noteId}")
    public ResponseEntity<?> loadNoteDetail(@PathVariable Long dealId, @PathVariable Long noteId){
        NoteState noteState = loadNoteUseCase.loadNoteInDetail(
                Note.builder()
                        .dealId(dealId)
                        .noteId(noteId)
                        .build()
        );
        return ResponseEntity.ok
                (ApiUtils.success(HttpStatus.OK
                        , new DetailNoteResponse(noteState)));
    }

    @GetMapping("/workspaces/deals/{dealId}/notes/{noteId}/histories")
    public SimpleNoteHistoryListResponse loadNoteHistory(@PathVariable Long dealId, @PathVariable Long noteId){
        List<NoteHistoryState> noteHistoryStates = loadNoteHistoryUseCase.loadNoteHistory(
                Note.builder()
                        .dealId(dealId)
                        .noteId(noteId)
                        .build()
        );
        return new SimpleNoteHistoryListResponse(noteHistoryStates);
    }
}
