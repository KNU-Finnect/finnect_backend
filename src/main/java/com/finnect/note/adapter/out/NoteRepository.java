package com.finnect.note.adapter.out;

import com.finnect.note.adapter.out.persistence.NoteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findNoteEntitiesByDealId(Long dealId);

    Optional<NoteEntity> findNoteEntityByDealIdAndNoteId(Long dealId, Long noteId);
}
