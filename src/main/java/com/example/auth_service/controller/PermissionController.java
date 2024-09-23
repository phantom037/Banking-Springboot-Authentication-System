package com.example.auth_service.controller;

import com.example.auth_service.dto.request.PermissionRequest;
import com.example.auth_service.dto.response.ApiResponse;
import com.example.auth_service.dto.response.PermissionResponse;
import com.example.auth_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder().result(permissionService.createPermission(request)).build();
    }

    @GetMapping
    public ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder().result(permissionService.getAllPermissions()).build();
    }

    @DeleteMapping("/{permission}")
    public ApiResponse<Void> delete(String permission){
        permissionService.deletePermission(permission);
        return ApiResponse.<Void>builder().build();
    }

}
