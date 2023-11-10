package com.sti.cmart;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CMartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMartApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(AccountService iUserService, RoleRepository iRoleRepository, AccountRepository iUserRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//
//            if (!iRoleRepository.existsByRoleName(RoleName.ADMIN)) {
//                iRoleRepository.save(new Role(RoleName.ADMIN));
//            }
//            if (!iRoleRepository.existsByRoleName(RoleName.STAFF)) {
//                iRoleRepository.save(new Role(RoleName.STAFF));
//            }
//            if (!iRoleRepository.existsByRoleName(RoleName.DRIVER)) {
//                iRoleRepository.save(new Role(RoleName.DRIVER));
//            }
//            if (!iRoleRepository.existsByRoleName(RoleName.USER)) {
//                iRoleRepository.save(new Role(RoleName.USER));
//            }
////            iUserService.saveRole(new Role(RoleName.USER));
////            iUserService.saveRole(new Role(RoleName.ADMIN));
////            iUserService.saveRole(new Role(RoleName.STAFF));
////            iUserService.saveRole(new Role(RoleName.DRIVER));
//
//            // Kiểm tra xem tài khoản admin đã tồn tại chưa
//            if (!iUserRepository.existsByUsername("admin@gmail.com")) {
//                Account user = iUserService.saverUser(
//                        new Account(
//                                "admin@gmail.com",
//                                "Trần Hữu Hiếu",
//                                "admin@gmail.com",
//                                passwordEncoder.encode("adminPassword"),
//                                "0123123123",
//                                true, // Truyền trực tiếp giá trị boolean
//                                (short) 1,
//                                new ArrayList<>()
//                        )
//                );
//                Role role = iRoleRepository.findByRoleName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));;
////                User user = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
//                user.getRoles().add(role);
//                iUserService.saverUser(user);
//            }
//        };
//    }
}
