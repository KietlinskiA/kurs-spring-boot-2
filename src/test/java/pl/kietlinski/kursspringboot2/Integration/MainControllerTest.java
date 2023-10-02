package pl.kietlinski.kursspringboot2.Integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kietlinski.kursspringboot2.Note;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnAllNotesAnd200() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080"))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Note[] actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Note[].class);
        Assertions.assertTrue(actual.length > 0);
    }

    @Test
    void shouldReturnNoteByIdAnd200() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/2"))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Note actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Note.class);
        Assertions.assertEquals(2L, actual.getId());
    }

    @Test
    void shouldNoReturnNoteByIdAnd404() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/1000"))
                .andExpect(MockMvcResultMatchers.status().is(404)).andReturn();
        assertEquals(404 , mvcResult.getResponse().getStatus());
    }

    @Test
    void shouldAddNewNoteAndReturn201AndReturnNewNote() throws Exception {
        Note newNote = new Note("test123", "test123123");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080")
                .content(objectMapper.writeValueAsString(newNote))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
        Note actual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Note.class);

        assertEquals(newNote.getTitle(), actual.getTitle());
        assertEquals(newNote.getDescription(), actual.getDescription());
    }

    @Test
    void shouldReturn400() throws Exception {
        Note newNote = new Note("", "");
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080")
                .content(objectMapper.writeValueAsString(newNote))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(400)).andReturn();
    }


    @Test
    void updateNote() {
    }

    @Test
    void deleteNoteById() {
    }
}