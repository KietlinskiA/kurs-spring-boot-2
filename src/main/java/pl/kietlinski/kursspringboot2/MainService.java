package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    private final NoteRepository noteRepository;

    @Autowired
    public MainService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
//        init();

    }

    public void init() {
        Note note1 = new Note("Zakupy", "Masło, chleb, bułki, papier, słodycze");
        Note note2 = new Note("Hasła", "fb: test123, ig: test123");
        Note note3 = new Note("Książki", "Mit przedsiębiorczości, Bogaty ojciec - biedny ojciec");
        noteRepository.saveAll(List.of(note1, note2, note3));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public Note getNoteById(Long toGetNoteId) {
        Optional<Note> note = noteRepository.findById(toGetNoteId);
        return note.orElseGet(() -> {
            Note emptyNote = new Note("", "");
            emptyNote.setCreateDate(LocalDateTime.of(1990, 1, 1, 0, 0, 0));
            emptyNote.setUpdateDate(LocalDateTime.of(1990, 1, 1, 0, 0, 0));
            return emptyNote;
        });
    }

    public Note addNewNote(Note toAddNote) {
        Note note = new Note(toAddNote.getTitle(), toAddNote.getDescription());
        noteRepository.save(note);
        return note;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean updateNote(Note toUpdateNote) {
        if (toUpdateNote.getId() == null) return false;
        Note noteById = getNoteById(toUpdateNote.getId());
        if (noteById.getId() == null) {
            return false;
        } else {
            toUpdateNote.setId(noteById.getId());
            toUpdateNote.setCreateDate(noteById.getCreateDate());
            noteRepository.save(toUpdateNote);
            return true;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public boolean deleteNoteById(Long toDeleteNoteId) {
        Note noteById = getNoteById(toDeleteNoteId);
        if (noteById.getId() == null || noteById.getId() == 0L) {
            return false;
        } else {
            noteRepository.delete(noteById);
            return true;
        }
    }


}
