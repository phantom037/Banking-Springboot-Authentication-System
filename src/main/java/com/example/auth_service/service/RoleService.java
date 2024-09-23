package com.example.auth_service.service;

import com.example.auth_service.dto.request.RoleRequest;
import com.example.auth_service.dto.response.RoleResponse;
import com.example.auth_service.entity.Role;
import com.example.auth_service.repository.PermissionRepository;
import com.example.auth_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    ModelMapper modelMapper;

    public RoleResponse createRole(RoleRequest request){
        var role = modelMapper.map(request, Role.class);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
        return modelMapper.map(role, RoleResponse.class);
    }

    public List<RoleResponse> getAllRole(){
        var roles = roleRepository.findAll();
        return roles.stream().map(role -> modelMapper.map(role, RoleResponse.class)).toList();
    }

    public void deleteRole(String role){
        roleRepository.deleteById(role);
    }
}
