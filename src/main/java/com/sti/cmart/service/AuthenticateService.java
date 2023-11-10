package com.sti.cmart.service;

import com.sti.cmart.entity.Role;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.other.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthenticateService {
    ResponseEntity<?> register(RegisterRequest registerDto);

    ResponseEntity<?> login (LoginRequest loginRequest, List<Role> roles);
}
