package com.finnect.note.application.service;

import com.finnect.note.application.port.in.LoadNoteHistoryUseCase;
import com.finnect.note.application.port.in.LoadNoteUseCase;
import com.finnect.note.application.port.out.LoadNoteHistoryPort;
import com.finnect.note.application.port.out.LoadNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.NoteHistory;
import com.finnect.note.domain.state.NoteHistoryState;
import com.finnect.note.domain.state.NoteState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoadNoteService implements LoadNoteUseCase, LoadNoteHistoryUseCase {
    private final LoadNotePort loadNotePort;
    private final LoadNoteHistoryPort loadNoteHistoryPort;
    @Override
    public List<NoteState> loadNotesInDeal(Note note)
    {
        List<Note> notes = loadNotePort.loadNoteByDealId(note);

        return new ArrayList<>(notes);
    }

    @Override
    public NoteState loadNoteInDetail(Note note) {
        return loadNotePort.loadNoteDetail(note);
    }

    @Override
    public List<NoteHistoryState> loadNoteHistory(Note note) {
        List<NoteHistory> noteHistories = loadNoteHistoryPort.loadHistory(note);
        log.info(noteHistories.toString());
        return new ArrayList<>(noteHistories);
    }
}
