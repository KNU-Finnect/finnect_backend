package com.finnect.note.application.service;

import com.finnect.note.application.port.in.LoadNoteUseCase;
import com.finnect.note.application.port.out.LoadNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoadNoteService implements LoadNoteUseCase {
    private final LoadNotePort loadNotePort;
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
}
