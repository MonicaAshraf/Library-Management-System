package com.library_management.book;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library_management.book.controller.BookController;
import com.library_management.book.models.request.BookRequestModel;
import com.library_management.book.models.response.BookResModel;
import com.library_management.book.service.BookService;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBook() throws Exception {
        String jsonRequest = "{ \"title\": \"The Great Book\", \"author\": \"John Doe\", \"publicationYear\": 2024, \"isbn\": \"1234567890123\", \"numberOfPages\": 250, \"statusDescription\": \"New\", \"category\": \"Fiction\", \"readerAge\": 12, \"language\": \"English\" }";

        BookResModel mockResponse = new BookResModel();
        mockResponse.setSuccess(true);
        mockResponse.setMessage("Book created successfully");
        when(bookService.createBook(any(BookRequestModel.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Book created successfully"));
    }

    @Test
    public void testRetrieveBooks() throws Exception {
        BookRequestModel mockBook = new BookRequestModel();
        mockBook.setTitle("Book Title");
        mockBook.setAuthor("Book Author");
        mockBook.setPublicationYear(2024);
        mockBook.setIsbn("1234567890123");
        mockBook.setNumberOfPages(250);
        mockBook.setStatusDescription("Available");
        mockBook.setCategory("Fiction");
        mockBook.setReaderAge(12);
        mockBook.setLanguage("English");

        List<BookRequestModel> mockBooks = List.of(mockBook);
        when(bookService.retrievebooks()).thenReturn(mockBooks);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book Title"))
                .andExpect(jsonPath("$[0].author").value("Book Author"));
    }

    @Test
    public void testGetBookById() throws Exception {
        BookRequestModel mockBook = new BookRequestModel();
        mockBook.setTitle("The Great Book");
        mockBook.setAuthor("John Doe");
        mockBook.setPublicationYear(2024);
        mockBook.setIsbn("1234567890123");
        mockBook.setNumberOfPages(250);
        mockBook.setStatusDescription("New");
        mockBook.setCategory("Fiction");
        mockBook.setReaderAge(12);
        mockBook.setLanguage("English");

        when(bookService.getBookById(1)).thenReturn(mockBook);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Great Book"))
                .andExpect(jsonPath("$.author").value("John Doe"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        String jsonRequest = "{ \"title\": \"Updated Book Title\", \"author\": \"John Doe\", \"publicationYear\": 2024, \"isbn\": \"1234567890123\", \"numberOfPages\": 250, \"statusDescription\": \"Updated\", \"category\": \"Non-Fiction\", \"readerAge\": 15, \"language\": \"Spanish\" }";

        BookRequestModel updatedBook = new BookRequestModel();
        updatedBook.setTitle("Updated Book Title");
        updatedBook.setAuthor("John Doe");
        updatedBook.setPublicationYear(2024);
        updatedBook.setIsbn("1234567890123");
        updatedBook.setNumberOfPages(250);
        updatedBook.setStatusDescription("Updated");
        updatedBook.setCategory("Non-Fiction");
        updatedBook.setReaderAge(15);
        updatedBook.setLanguage("Spanish");

        when(bookService.updateBook(eq(1), any(BookRequestModel.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book Title"))
                .andExpect(jsonPath("$.statusDescription").value("Updated"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        BookResModel mockResponse = new BookResModel();
        mockResponse.setSuccess(true);
        mockResponse.setMessage("Book deleted successfully");
        when(bookService.deleteBook(1)).thenReturn(mockResponse);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Book deleted successfully"));
    }
}
