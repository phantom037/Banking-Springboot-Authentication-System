package com.example.auth_service.controller;

import com.example.auth_service.dto.request.UserCreationRequest;
import com.example.auth_service.dto.request.UserUpdateRequest;
import com.example.auth_service.dto.response.ApiResponse;
import com.example.auth_service.dto.response.UserResponse;
import com.example.auth_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping("{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(id));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll(){
        ApiResponse<List<UserResponse>> apiResponse  = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }

    @PostMapping("{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(id, request));
        return apiResponse;
    }

    @GetMapping("/myinfo")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }

    @DeleteMapping("{id}")
    public ApiResponse deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return ApiResponse.builder().code(200).message("Successful delete user!").build();
    }

    


}
