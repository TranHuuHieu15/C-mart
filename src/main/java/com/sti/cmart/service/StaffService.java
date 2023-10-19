package com.sti.cmart.service;

import com.sti.cmart.model.dto.StaffDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface StaffService {
    StaffDTO findById(Long id);

    StaffDTO findByUsername(String username);

    StaffDTO findByEmail(String email);

    StaffDTO save(StaffDTO StaffDTO);

    void delete(Long id);

    Page<StaffDTO> findAllStaff(SearchCriteria searchCriteria);

    Page<StaffDTO> findStaffByFullname(String fullname, SearchCriteria searchCriteria);

    StaffDTO findByPhone(String phone);
}
