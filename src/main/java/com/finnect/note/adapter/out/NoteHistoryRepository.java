package com.finnect.note.adapter.out;

import com.finnect.note.adapter.out.persistence.NoteHistoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteHistoryRepository extends JpaRepository<NoteHistoryEntity, Long> {
    List<NoteHistoryEntity> findNoteHistoryEntitiesByNoteId(Long noteId);
}
