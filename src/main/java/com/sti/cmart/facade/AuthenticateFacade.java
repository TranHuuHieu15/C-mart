package com.sti.cmart.facade;

import com.sti.cmart.entity.Role;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.other.request.RegisterRequest;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticateFacade {

    private final AuthenticateService authenticateService;
    private final RoleRepository repository;

    public ResponseEntity<?> login(LoginRequest loginRequest) throws ArchitectureException {
        if (loginRequest == null)
            throw new InvalidParamException();
        List<Role> roles = repository.findAll();
        return authenticateService.login(loginRequest, roles);
    }

    public ResponseEntity<?> register(RegisterRequest registerRequest) throws ArchitectureException {
        if (registerRequest == null)
            throw new InvalidParamException();
        return authenticateService.register(registerRequest);
    }

}
