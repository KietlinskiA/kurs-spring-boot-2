package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(mainService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note noteById = mainService.getNoteById(id);
        if (noteById.getId() != null && noteById.getId() != 0L) {
            return new ResponseEntity<>(noteById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Note> addNewNote(@RequestBody Note toAddNote) {
        if (toAddNote.getTitle() != null && !toAddNote.getTitle().isEmpty() &&
                toAddNote.getDescription() != null && !toAddNote.getDescription().isEmpty()) {
            Note note = mainService.addNewNote(toAddNote);
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note toUpdateNote) {
        if (mainService.updateNote(toUpdateNote)) {
            return new ResponseEntity<>(toUpdateNote, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{toDeleteNoteId}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long toDeleteNoteId) {
        if (toDeleteNoteId != null && mainService.deleteNoteById(toDeleteNoteId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
