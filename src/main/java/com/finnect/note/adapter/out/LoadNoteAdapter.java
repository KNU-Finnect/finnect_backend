package com.finnect.note.adapter.out;

import com.finnect.note.adapter.out.persistence.NoteEntity;
import com.finnect.note.application.port.out.LoadNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadNoteAdapter implements LoadNotePort {
    private final NoteRepository noteRepository;

    @Override
    public List<Note> loadNoteByDealId(NoteState noteState) {
        log.info(noteState.getDealId().toString());
        List<NoteEntity> noteEntities = noteRepository.findNoteEntitiesByDealId(noteState.getDealId());
        log.info(noteEntities.toString());
        return noteEntities.stream()
                .map(NoteEntity::toDomain)
                .toList();
    }

    @Override
    public Note loadNoteDetail(NoteState noteState) {
        NoteEntity note = noteRepository.findNoteEntityByDealIdAndNoteId(noteState.getDealId(), noteState.getNoteId())
                .orElseThrow(IllegalAccessError::new);
        return note.toDomain();
    }
}
