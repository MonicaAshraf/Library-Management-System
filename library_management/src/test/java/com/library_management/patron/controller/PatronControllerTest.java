//package com.library_management.patron.controller;
//import java.util.Optional;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.library_management.patron.models.request.PatronRequestModel;
//import com.library_management.patron.models.response.PatronResModel;
//import com.library_management.patron.service.PatronService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(PatronController.class)
//public class PatronControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @MockBean
//    private PatronService patronService;
//
//
//    
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    private PatronRequestModel patronRequest;
//    
////    @BeforeEach
////    public void setup() {
////        MockitoAnnotations.openMocks(this);
////    }
//    
//    
//    @BeforeEach
//    void setUp() {
//        // Setup a mock Patron object
//        PatronRequestModel patronRequest = new PatronRequestModel();
//        patronRequest.setFirstName("John");
//        patronRequest.setSecondName("Doe");
//        patronRequest.setThirdName("Smith");
//        patronRequest.setNationalityId(123L);
//        patronRequest.setGender("Male");
//        patronRequest.setEmail("john.doe@example.com");
//        patronRequest.setUserName("johndoe");
//        patronRequest.setPassword("password123");
//        patronRequest.setPhone("1234567890");
//        patronRequest.setCountry("Country");
//        patronRequest.setCity("City");
//        patronRequest.setStreet("Street");
//        patronRequest.setPositionId(1);
//
//        // Mock the service call for finding a patron by ID
//        //swhen(patronService.getPatronById(1)).thenReturn(Optional.of(patronRequest));
//        when(patronService.getPatronById(1)).thenReturn(patronRequest);
//
//    }
//    
//    @Test
//    public void testCreatePatron() throws Exception {
//        PatronRequestModel requestModel = new PatronRequestModel();
//        requestModel.setFirstName("John");
//        requestModel.setSecondName("Doe");
//        requestModel.setThirdName("Smith");
//        requestModel.setNationalityId(123L);
//        requestModel.setGender("Male");
//        requestModel.setEmail("john.doe@example.com");
//        requestModel.setUserName("johndoe");
//        requestModel.setPassword("password123");
//        requestModel.setPhone("1234567890");
//        requestModel.setCountry("Country");
//        requestModel.setCity("City");
//        requestModel.setStreet("Street");
//        requestModel.setPositionId(1);
//
//        PatronResModel responseModel = new PatronResModel();
//        responseModel.setSuccess(true);
//        responseModel.setMessage("Patron created successfully.");
//
//        when(patronService.createpatron(any(PatronRequestModel.class))).thenReturn(responseModel);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/patrons")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(requestModel)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patron created successfully."))
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(patronService, times(1)).createpatron(any(PatronRequestModel.class));
//    }
//
//    @Test
//    public void testGetAllPatrons() throws Exception {
//        PatronRequestModel patron = new PatronRequestModel();
//        patron.setFirstName("John");
//        // Set other fields as needed
//        when(patronService.getAllPatrons()).thenReturn(Collections.singletonList(patron));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/patrons")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(patronService, times(1)).getAllPatrons();
//    }
//
//    @Test
//    public void testGetPatronById() throws Exception {
//        PatronRequestModel patron = new PatronRequestModel();
//        patron.setFirstName("John");
//        // Set other fields as needed
//        when(patronService.getPatronById(1)).thenReturn(patron);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/patrons/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(patronService, times(1)).getPatronById(1);
//    }
//
////    @Test
////    public void testUpdatePatron() throws Exception {
////        PatronRequestModel requestModel = new PatronRequestModel();
////        requestModel.setFirstName("John");
////        // Set other fields as needed
////        PatronResModel responseModel = new PatronResModel();
////        responseModel.setSuccess(true);
////        responseModel.setMessage("Patron updated successfully.");
////
////        when(patronService.updatePatron(eq(1), any(PatronRequestModel.class))).thenReturn(responseModel);
////
////        mockMvc.perform(MockMvcRequestBuilders.put("/patrons/1")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(objectMapper.writeValueAsString(requestModel)))
////                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patron updated successfully."))
////                .andDo(MockMvcResultHandlers.print());
////
////        verify(patronService, times(1)).updatePatron(eq(1), any(PatronRequestModel.class));
////    }
//    
//    
////    @Test
////    public void testUpdatePatron() throws Exception {
////        // Create a PatronRequestModel with valid data
////        PatronRequestModel patronRequest = new PatronRequestModel();
////        patronRequest.setFirstName("John");
////        patronRequest.setSecondName("Doe");
////        patronRequest.setThirdName("Smith");
////        patronRequest.setNationalityId(123L);
////        patronRequest.setGender("Male");
////        patronRequest.setEmail("john.doe@example.com");
////        patronRequest.setUserName("johndoe");
////        patronRequest.setPassword("password123");
////        patronRequest.setPhone("1234567890");
////        patronRequest.setCountry("Country");
////        patronRequest.setCity("City");
////        patronRequest.setStreet("Street");
////        patronRequest.setPositionId(1);
////
////        // Convert the object to JSON
////        String jsonRequest = objectMapper.writeValueAsString(patronRequest);
////
////        // Perform the PUT request
////        ResultActions resultActions = mockMvc.perform(put("/patrons/1")
////                .contentType("application/json")
////                .content(jsonRequest));
////
////        // Verify the response status
////        resultActions.andExpect(status().isOk());
////    }
//
//    
//    @Test
//    public void testUpdatePatron() throws Exception {
//        // Convert the object to JSON
//        String jsonRequest = objectMapper.writeValueAsString(patronRequest);
//
//        // Perform the PUT request
//        mockMvc.perform(put("/patrons/1")
//                .contentType("application/json")
//                .content(jsonRequest))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeletePatron() throws Exception {
//        PatronResModel responseModel = new PatronResModel();
//        responseModel.setSuccess(true);
//        responseModel.setMessage("Patron deleted successfully.");
//
//        when(patronService.deletePatron(1)).thenReturn(responseModel);
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/patrons/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Patron deleted successfully."))
//                .andDo(MockMvcResultHandlers.print());
//
//        verify(patronService, times(1)).deletePatron(1);
//    }
//}
