package com.finnect.note.adapter.in.web.res;

import com.finnect.note.domain.state.NoteHistoryState;
import java.util.List;
import lombok.Getter;

@Getter
public class SimpleNoteHistoryListResponse {
    private final List<SimpleNoteHistory> noteHistories;

    public SimpleNoteHistoryListResponse(List<NoteHistoryState> noteHistoryStates){
        this.noteHistories = noteHistoryStates.stream()
                .map(SimpleNoteHistory::new)
                .toList();
    }
}
@Getter
class SimpleNoteHistory{
    private Long noteHistoryId;
    private String historyTitle;

    public SimpleNoteHistory(NoteHistoryState noteHistoryState) {
        this.noteHistoryId = noteHistoryState.noteHistoryId();
        this.historyTitle = noteHistoryState.historyTitle();
    }
}
