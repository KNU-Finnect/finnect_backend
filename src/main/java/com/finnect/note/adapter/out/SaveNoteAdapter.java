package com.finnect.note.adapter.out;

import com.finnect.note.adapter.out.persistence.NoteEntity;
import com.finnect.note.adapter.out.persistence.NoteHistoryEntity;
import com.finnect.note.application.port.out.SaveNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveNoteAdapter implements SaveNotePort {
    private final NoteRepository noteRepository;
    private final NoteHistoryRepository noteHistoryRepository;
    @Override
    public Note saveNote(NoteState noteState) {
        NoteEntity noteEntity = NoteEntity.toEntity(noteState);
        noteRepository.save(noteEntity);
        return noteEntity.toDomain();
    }

    @Override
    public Note patchNote(NoteState noteState) {
        NoteEntity existNote = noteRepository.findNoteEntityByDealIdAndNoteId(noteState.getDealId(), noteState.getNoteId())
                .orElseThrow(IllegalAccessError::new);
        NoteEntity patchedNote = NoteEntity.patchEntity(noteState, existNote);
        NoteHistoryEntity history = new NoteHistoryEntity(existNote);
        noteHistoryRepository.save(history);
        noteRepository.save(patchedNote);
        return patchedNote.toDomain();
    }
}
