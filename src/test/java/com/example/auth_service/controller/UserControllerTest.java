//package com.example.auth_service.controller;
//
//import com.example.auth_service.dto.request.UserCreationRequest;
//import com.example.auth_service.dto.response.UserResponse;
//import com.example.auth_service.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.extern.slf4j.Slf4j;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//@Slf4j
//@AutoConfigureMockMvc
//@TestPropertySource("/test.properties")
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    private UserCreationRequest request;
//    private UserResponse response;
//
//    @BeforeEach
//    private void initData(){
//        request = UserCreationRequest.builder()
//                .username("leotran")
//                .firstName("Leo")
//                .lastName("Tran")
//                .password("123456789")
//                .dob(LocalDate.of(1999,9,18))
//                .build();
//
//        response = UserResponse.builder()
//                .id("xxxxxxxxxxxx")
//                .username("leotran")
//                .firstName("Leo")
//                .lastName("Tran")
//                .dob(LocalDate.of(1999,9,18))
//                .build();
//
//    }
//
//    /**
//     * Happy Test Case
//     */
//    @Test
//    void createUser_validRequest_success() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(response);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/users")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("xxxxxxxxxxxx"))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.username").value(request.getUsername()))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.firstName").value(request.getFirstName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.lastName").value(request.getLastName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.dob").value(request.getDob().toString()));
//    }
//
//    /**
//     * Creation error due to invalid value of response
//     * */
//    @Test
//    void createUser_responseInvalid_error() throws Exception {
//        request.setUsername("leooooo");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(response);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/users").contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
//                .andExpect(MockMvcResultMatchers.jsonPath("result.username").value(Matchers.not(request.getUsername())));
//    }
//
//    @Test
//    void createUser_usernameInvalid_fail() throws Exception{
//        request.setUsername("le");
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/users")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(content))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400))
//                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 3 characters"));
//    }
//
//    @Test
//    void createUser_dobInvalid_fail() throws Exception{
//        request.setDob(LocalDate.of(2020, 9, 18));
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        String content = objectMapper.writeValueAsString(request);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post("/users")
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(content))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("code").value(400))
//                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Invalid date of birth. You must be at least 18"));
//    }
//
//
//    @Test
//    void updateUser_validRequest_success(){
//
//    }
//    @Test
//    void updateUser_dobInvalid_fail(){}
//
//    @Test
//    void deleteUser_validRequest_success(){}
//
//    @Test
//    void getAllUser_validRequest_success(){}
//
//    @Test
//    void getAllUser_missingData_fail(){}
//
//    @Test
//    void getUserById_validRequest_success(){}
//
//}
