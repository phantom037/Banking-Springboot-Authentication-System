package com.example.auth_service.service;

import com.example.auth_service.exception.AppException;
import com.example.auth_service.dto.request.UserCreationRequest;
import com.example.auth_service.dto.request.UserUpdateRequest;
import com.example.auth_service.dto.response.UserResponse;
import com.example.auth_service.entity.User;
import com.example.auth_service.enums.ErrorCode;
import com.example.auth_service.repository.RoleRepository;
import com.example.auth_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User createdUser = modelMapper.map(request , User.class);
        createdUser.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        createdUser.setRoles(new HashSet<>(roles));
        userRepository.save(createdUser);
        return modelMapper.map(createdUser, UserResponse.class);
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        User foundUser = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        foundUser.setFirstName(request.getFirstName());
        foundUser.setLastName(request.getLastName());
        foundUser.setPassword(request.getPassword());
        userRepository.save(foundUser);
        return modelMapper.map(foundUser, UserResponse.class);
    }

    @PostAuthorize(("returnObject.username == authentication.name"))
    public UserResponse getUser(String id){
        User foundUser = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        return modelMapper.map(foundUser, UserResponse.class);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser(){
        List<User> allUser = userRepository.findAll();
        return allUser.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    public void deleteUser(String id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        userRepository.delete(user);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return modelMapper.map(user, UserResponse.class);
    }
}
