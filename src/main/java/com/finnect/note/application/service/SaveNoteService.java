package com.finnect.note.application.service;

import com.finnect.note.application.port.in.SaveNoteUseCase;
import com.finnect.note.application.port.out.SaveNotePort;
import com.finnect.note.domain.Note;
import com.finnect.note.domain.state.NoteState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveNoteService implements SaveNoteUseCase {
    private final SaveNotePort saveNotePort;
    @Override
    public NoteState saveNewNote(Note note) {
        return saveNotePort.saveNote(note);
    }
}
