package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Admin;
import com.sti.cmart.model.dto.AdminDTO;
import com.sti.cmart.model.mapper.AdminMapper;
import com.sti.cmart.repository.AdminRepository;
import com.sti.cmart.service.AdminService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    //find by Id
    @Override
    public AdminDTO findById(Long id) {
        Optional<Admin> dto = adminRepository.findById(id);
        return dto.map(adminMapper::apply).orElse(null);
    }

    //find by username
    @Override
    public AdminDTO findByUsername(String username) {
        Optional<Admin> dto = adminRepository.findByUsername(username);
        return dto.map(adminMapper::apply).orElse(null);
    }

    //find by email
    @Override
    public AdminDTO findByEmail(String email) {
        Optional<Admin> dto = adminRepository.findByEmail(email);
        return dto.map(adminMapper::apply).orElse(null);
    }

    //save
    @Override
    public AdminDTO save(AdminDTO adminDTO) {
        Admin admin = adminMapper.applyToEntity(adminDTO);
        return adminMapper.apply(adminRepository.save(admin));
    }

    //delete
    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    //find all admin
    @Override
    public Page<AdminDTO> findAllAdmin(SearchCriteria searchCriteria) {
        Page<Admin> admins = adminRepository.findAll(Search.getPageable(searchCriteria));
        return admins.map(adminMapper::apply);
    }

    //find all admin by fullname
    @Override
    public Page<AdminDTO> findAdminByFullname(String fullname, SearchCriteria searchCriteria) {
        Page<Admin> admins = adminRepository.findAllByFullname(fullname, Search.getPageable(searchCriteria));
        return admins.map(adminMapper::apply);
    }

    //findByPhone
    @Override
    public AdminDTO findByPhone(String phone) {
        Optional<Admin> dto = adminRepository.findByPhone(phone);
        return dto.map(adminMapper::apply).orElse(null);
    }
}
