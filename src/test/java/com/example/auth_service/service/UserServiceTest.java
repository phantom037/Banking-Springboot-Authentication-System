package com.example.auth_service.service;

import com.example.auth_service.dto.request.UserCreationRequest;
import com.example.auth_service.dto.response.UserResponse;
import com.example.auth_service.entity.Role;
import com.example.auth_service.entity.User;
import com.example.auth_service.repository.RoleRepository;
import com.example.auth_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    private UserCreationRequest creationRequest;
    private UserResponse expectedResponse;
    private User user;

    @BeforeEach
    private void initData(){
        creationRequest = UserCreationRequest.builder()
                .username("leotran")
                .firstName("Leo")
                .lastName("Tran")
                .password("123456789")
                .dob(LocalDate.of(1999,9,18))
                .build();

        expectedResponse = UserResponse.builder()
                .id("xxxxxxxxxxxx")
                .username("leotran")
                .firstName("Leo")
                .lastName("Tran")
                .dob(LocalDate.of(1999,9,18))
                .build();

        user = User.builder()
                .id("xxxxxxxxxxxx")
                .username("leotran")
                .firstName("Leo")
                .lastName("Tran")
                .password("123456789")
                .dob(LocalDate.of(1999,9,18))
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(roleRepository.findAllById(ArgumentMatchers.any())).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        var createResponse = userService.createUser(creationRequest);
        log.info("createResponse: " + createResponse.getId() + "\t" + createResponse.getUsername() + "\t" + createResponse.getDob());

//        Assertions.assertThat(createResponse.getId()).isEqualTo(expectedResponse.getId());
//        Assertions.assertThat(createResponse.getUsername()).isEqualTo(expectedResponse.getUsername());
//        Assertions.assertThat(createResponse.getFirstName()).isEqualTo(expectedResponse.getFirstName());
//        Assertions.assertThat(createResponse.getLastName()).isEqualTo(expectedResponse.getLastName());
//        Assertions.assertThat(createResponse.getDob()).isEqualTo(expectedResponse.getDob());
    }
}
