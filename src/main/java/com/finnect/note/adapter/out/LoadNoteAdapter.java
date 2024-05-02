package com.finnect.note.adapter.out;

import com.finnect.note.adapter.out.persistence.NoteEntity;
import com.finnect.note.adapter.out.persistence.NoteHistoryEntity;
import com.finnect.note.application.port.out.LoadNoteHistoryPort;
import com.finnect.note.application.port.out.LoadNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.NoteHistory;
import com.finnect.note.domain.state.NoteState;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoadNoteAdapter implements LoadNotePort, LoadNoteHistoryPort {
    private final NoteRepository noteRepository;
    private final NoteHistoryRepository noteHistoryRepository;
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

    @Override
    public List<NoteHistory> loadHistory(NoteState noteState) {
        List<NoteHistoryEntity> noteHistoryEntities = noteHistoryRepository
                .findNoteHistoryEntitiesByNoteId(noteState.getNoteId());
        log.info(noteHistoryEntities.toString());
        return noteHistoryEntities.stream()
                .map(NoteHistoryEntity::toDomain)
                .toList();
    }
}
