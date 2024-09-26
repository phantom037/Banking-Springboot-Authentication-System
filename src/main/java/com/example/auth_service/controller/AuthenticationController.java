package com.example.auth_service.controller;

import com.example.auth_service.dto.request.AuthenticationRequest;
import com.example.auth_service.dto.request.IntrospectRequest;
import com.example.auth_service.dto.request.LogoutRequest;
import com.example.auth_service.dto.request.RefreshableRequest;
import com.example.auth_service.dto.response.ApiResponse;
import com.example.auth_service.dto.response.AuthenticationResponse;
import com.example.auth_service.dto.response.IntrospectResponse;
import com.example.auth_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>();
        response.setResult(authenticationService.authenticate(request));
        return response;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> response = new ApiResponse<>();
        response.setResult(authenticationService.introspect(request));
        return response;
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshableRequest request) throws ParseException, JOSEException {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        log.info("Token: " + request.getToken());
        apiResponse.setResult(authenticationService.refreshToken(request));
        return apiResponse;
    }

}
