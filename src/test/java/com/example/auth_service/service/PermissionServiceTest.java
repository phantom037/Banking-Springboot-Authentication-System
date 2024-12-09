//package com.example.auth_service.service;
//
//import com.example.auth_service.dto.request.PermissionRequest;
//import com.example.auth_service.dto.response.PermissionResponse;
//import com.example.auth_service.entity.Permission;
//import com.example.auth_service.exception.AppException;
//import com.example.auth_service.repository.PermissionRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//@SpringBootTest
//@TestPropertySource("/test.properties")
//@Slf4j
//public class PermissionServiceTest {
//    @Autowired
//    private PermissionService permissionService;
//
//    @MockBean
//    private PermissionRepository permissionRepository;
//    private PermissionRequest request;
//    private PermissionResponse expectedResponse;
//    private Permission permission;
//
//    @BeforeEach
//    private void initData(){
//        request = PermissionRequest.builder().name("READ_DATA").description("Read data").build();
//        expectedResponse = PermissionResponse.builder().name("READ_DATA").description("Read data").build();
//        permission = Permission.builder().name("READ_DATA").description("Read data").build();
//    }
//
//    @Test
//    void createPermission_validRequest_success(){
//        Mockito.when(permissionRepository.existsById(ArgumentMatchers.any())).thenReturn(false);
//        Mockito.when(permissionRepository.save(ArgumentMatchers.any())).thenReturn(permission);
//        var response = permissionService.createPermission(request);
//
//        Assertions.assertThat(response.getName()).isEqualTo(expectedResponse.getName());
//        Assertions.assertThat(response.getDescription()).isEqualTo(expectedResponse.getDescription());
//    }
//
//    @Test
//    void createPermission_invalidRequest_fail(){
//        Mockito.when(permissionRepository.existsById(ArgumentMatchers.any())).thenReturn(true);
//        var exception = org.junit.jupiter.api.Assertions.assertThrows(AppException.class,() -> permissionService.createPermission(request));
//        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(400);
//    }
//
//    @Test
//    void deletePermission_invalidRequest_fail(){
//        Mockito.when(permissionRepository.existsById(ArgumentMatchers.any())).thenReturn(true);
//        var exception = org.junit.jupiter.api.Assertions.assertThrows(AppException.class,() -> permissionService.createPermission(request));
//        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(400);
//    }
//
//    @Test
//    void deletePermission_validRequest_success(){
//        Mockito.when(permissionRepository.existsById(ArgumentMatchers.any())).thenReturn(false);
//        Mockito.when(permissionRepository.save(ArgumentMatchers.any())).thenReturn(permission);
//        permissionService.deletePermission(request.getName());
//        log.info("Deleted Successfully!");
//    }
//
//    @Test
//    void getAllPermission_validRequest_success(){
//        List<Permission> permissionList = new ArrayList<>();
//        Permission p1 = Permission.builder().name("WRITE_DATA").description("Write data").build();
//        Permission p2 = Permission.builder().name("UPDATE_DATA").description("Update data").build();
//        Permission p3 = Permission.builder().name("DELETE_DATA").description("Delete data").build();
//        permissionList.add(permission);
//        permissionList.add(p1);
//        permissionList.add(p2);
//        permissionList.add(p3);
//        List<PermissionResponse> permissionResponses = new ArrayList<>();
//        permissionResponses.add(PermissionResponse.builder().name(p1.getName()).description(p1.getDescription()).build());
//        permissionResponses.add(PermissionResponse.builder().name(p2.getName()).description(p2.getDescription()).build());
//        permissionResponses.add(PermissionResponse.builder().name(p3.getName()).description(p3.getDescription()).build());
//        permissionResponses.add(expectedResponse);
//
//        Mockito.when(permissionRepository.findAll()).thenReturn(permissionList);
//        var response = permissionService.getAllPermissions();
//
//        response.stream().forEach(r -> Assertions.assertThat(permissionResponses.contains(r)).isEqualTo(response.size() == permissionResponses.size()));
//
//    }
//
//    @Test
//    void getAllPermission_invalidRequest_fail(){
//        List<Permission> permissionList = new ArrayList<>();
//        Permission p1 = Permission.builder().name("WRITE_DATA").description("Write data").build();
//        Permission p2 = Permission.builder().name("UPDATE_DATA").description("Update data").build();
//        Permission p3 = Permission.builder().name("DELETE_DATA").description("Delete data").build();
//        permissionList.add(p1);
//        permissionList.add(p2);
//        permissionList.add(p3);
//        List<PermissionResponse> permissionResponses = new ArrayList<>();
//        permissionResponses.add(PermissionResponse.builder().name(p1.getName()).description(p1.getDescription()).build());
//        permissionResponses.add(PermissionResponse.builder().name(p2.getName()).description(p2.getDescription()).build());
//        permissionResponses.add(PermissionResponse.builder().name(p3.getName()).description(p3.getDescription()).build());
//        permissionResponses.add(expectedResponse);
//
//        Mockito.when(permissionRepository.findAll()).thenReturn(permissionList);
//        var response = permissionService.getAllPermissions();
//
//        response.stream().forEach(r -> Assertions.assertThat(permissionResponses.contains(r)).isEqualTo(response.size() == permissionResponses.size()));
//
//    }
//
//
//}
