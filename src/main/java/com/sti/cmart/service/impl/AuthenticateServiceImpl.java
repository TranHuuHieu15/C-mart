package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.other.request.RegisterRequest;
import com.sti.cmart.other.response.AccountResponse;
import com.sti.cmart.other.response.RegisterResponse;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> register(RegisterRequest registerDto) {
        List<Role> roleList = roleRepository.findAllByAccountUsername(registerDto.getUsername());
        Account account;
        RegisterResponse registerResponse;
        boolean hasUserRole = false;
        for (Role role : roleList) {
            // Trường hợp account đã có role USER
            if (role.getRoleName().contains("USER")) {
                hasUserRole = true;
                break;
            }
        }

        if (hasUserRole) {
            return new ResponseEntity<>("Username already role USER exists", HttpStatus.BAD_REQUEST);
        } else {
            account = roleList.get(0).getAccount();
            roleList.add(roleRepository.save(new Role(RoleName.USER, account)));
            registerResponse = RegisterResponse.builder()
                    .username(account.getUsername())
                    .fullname(account.getFullname())
                    .email(account.getEmail())
                    .accessToken(jwtService.generateAccessToken(account, getIndex(roleList)))
                    .refreshToken(jwtService.generateRefreshToken(account, getIndex(roleList)))
                    .build();

        }
        if (roleList.isEmpty()) {
            account = Account.builder()
                    .username(registerDto.getUsername())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .fullname(registerDto.getFullname())
                    .phone(registerDto.getPhone())
                    .email(registerDto.getEmail())
                    .isActive(true)
                    .status((short) 1)
                    .build();

            account = accountRepository.save(account);

            Role newUserRole = new Role(RoleName.USER, account);
            roleList.add(roleRepository.save(newUserRole));
        }
        registerResponse = RegisterResponse.builder()
                .username(account.getUsername())
                .fullname(account.getFullname())
                .email(account.getEmail())
                .accessToken(jwtService.generateAccessToken(account, getIndex(roleList)))
                .refreshToken(jwtService.generateRefreshToken(account, getIndex(roleList)))
                .build();

        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest, List<Role> roles) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sai tài khoản hoặc mật khẩu!", HttpStatus.SEE_OTHER);
        }
        List<Role> allRoles = roleRepository.findAllByAccountUsername(authentication.getName());
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        // Lấy danh sách các Role thuộc về tài khoản có ID là accountId
        List<Role> accountRoles = getRolesByAccountId(allRoles, account.getId());

        // Tạo danh sách các ID từ danh sách các Role
        List<Integer> rolesIds = getIndex(accountRoles);

        // Tiếp tục xử lý và trả về kết quả
        account = accountRoles.get(0).getAccount();
        String accessToken = jwtService.generateAccessToken(account, rolesIds);
        String refreshToken = jwtService.generateRefreshToken(account, rolesIds);
        AccountResponse accountResponse = AccountResponse.builder()
                .username(account.getUsername())
                .fullname(account.getFullname())
                .email(account.getEmail())
                .image(account.getImage())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

    public static List<Integer> getIndex(List<Role> roles) {
        List<Integer> index = new ArrayList<>();

        // Lấy danh sách các ID từ danh sách các Role
        for (Role role : roles) {
            index.add(Math.toIntExact(role.getId()));
        }

        return index;
    }

    public static List<Role> getRolesByAccountId(List<Role> allRoles, Long accountId) {
        List<Role> accountRoles = new ArrayList<>();

        // Lọc ra danh sách các Role thuộc về tài khoản có ID là accountId
        for (Role role : allRoles) {
            if (role.getAccount().getId().equals(accountId)) {
                accountRoles.add(role);
            }
        }

        return accountRoles;
    }


}
