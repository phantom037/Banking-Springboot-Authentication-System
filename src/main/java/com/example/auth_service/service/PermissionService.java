package com.example.auth_service.service;

import com.example.auth_service.dto.request.PermissionRequest;
import com.example.auth_service.dto.response.PermissionResponse;
import com.example.auth_service.entity.Permission;
import com.example.auth_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    ModelMapper modelMapper;

    public PermissionResponse createPermission(PermissionRequest request){
        Permission permission = modelMapper.map(request, Permission.class);
        permissionRepository.save(permission);
        return modelMapper.map(permission, PermissionResponse.class);
    }

    public List<PermissionResponse> getAllPermissions(){
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(permission -> modelMapper.map(permission, PermissionResponse.class)).toList();
    }

    public void deletePermission(String permission){
        permissionRepository.deleteById(permission);
    }
}
