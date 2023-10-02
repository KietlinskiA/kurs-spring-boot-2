package pl.kietlinski.kursspringboot2.Unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kietlinski.kursspringboot2.MainService;
import pl.kietlinski.kursspringboot2.Note;
import pl.kietlinski.kursspringboot2.NoteRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    private MainService mainService;

    @BeforeEach
    public void setUpRepository() {
        NoteRepository noteRepository = mock(NoteRepository.class);
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note(1L, "test1", "test1test1"));
        noteList.add(new Note(2L, "test2", "test2test2"));
        noteList.add(new Note(3L, "test3", "test3test3"));

        doReturn(noteList).when(noteRepository).findAll();

        mainService = new MainService(noteRepository);
    }

    @Test
    void shouldReturnNoteById() {
        Note actual = mainService.getNoteById(1L);

        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals("test1", actual.getTitle());
        Assertions.assertEquals("test1test1", actual.getDescription());
        Assertions.assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                actual.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Assertions.assertEquals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                actual.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    void shouldNoReturnNoteById() {
        Note actual = mainService.getNoteById(5L);

        Assertions.assertNull(actual.getId());
        Assertions.assertEquals("", actual.getTitle());
        Assertions.assertEquals("", actual.getDescription());
        Assertions.assertEquals(
                LocalDateTime.of(1990, 1, 1, 0, 0, 0)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                actual.getCreateDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Assertions.assertEquals(
                LocalDateTime.of(1990, 1, 1, 0, 0, 0)
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                actual.getUpdateDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    void shouldUpdateNoteAndReturnTrue() {
        Note note = new Note(3L, "test33", "test33test33");

        boolean actual = mainService.updateNote(note);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldNoUpdateNoteAndReturnFalse() {
        Note note = new Note(5L, "test5", "test5test5");

        boolean actual = mainService.updateNote(note);

        Assertions.assertFalse(actual);
    }

    @Test
    void shouldDeleteNoteByIdAndReturnTrue() {
        long toDeleteNoteId = 1L;

        boolean actual = mainService.deleteNoteById(toDeleteNoteId);

        Assertions.assertTrue(actual);
    }

    @Test
    void shouldNoDeleteNoteByIdAndReturnFalse() {
        long toDeleteNoteId = 6L;

        boolean actual = mainService.deleteNoteById(toDeleteNoteId);

        Assertions.assertFalse(actual);
    }
}