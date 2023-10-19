package com.sti.cmart.service;

import com.sti.cmart.model.dto.AdminDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface AdminService {
    //find by Id
    AdminDTO findById(Long id);

    //find by username
    AdminDTO findByUsername(String username);

    //find by email
    AdminDTO findByEmail(String email);

    //save
    AdminDTO save(AdminDTO adminDTO);

    //delete
    void delete(Long id);

    //find all admin
    Page<AdminDTO> findAllAdmin(SearchCriteria searchCriteria);

    //find all admin by fullname
    Page<AdminDTO> findAdminByFullname(String fullname, SearchCriteria searchCriteria);

    //findByPhone
    AdminDTO findByPhone(String phone);
}
