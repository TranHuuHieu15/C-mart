package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Staff;
import com.sti.cmart.model.dto.StaffDTO;
import com.sti.cmart.model.mapper.StaffMapper;
import com.sti.cmart.repository.StaffRepository;
import com.sti.cmart.service.StaffService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    
    //find by Id
    
    @Override
    public StaffDTO findById(Long id) {
        Optional<Staff> dto = staffRepository.findById(id);
        return dto.map(staffMapper::apply).orElse(null);
    }

    //find by username
    
    @Override
    public StaffDTO findByUsername(String username) {
        Optional<Staff> dto = staffRepository.findByUsername(username);
        return dto.map(staffMapper::apply).orElse(null);
    }

    //find by email
    
    @Override
    public StaffDTO findByEmail(String email) {
        Optional<Staff> dto = staffRepository.findByEmail(email);
        return dto.map(staffMapper::apply).orElse(null);
    }

    //save
    
    @Override
    public StaffDTO save(StaffDTO StaffDTO) {
        Staff Staff = staffMapper.applyToStaff(StaffDTO);
        return staffMapper.apply(staffRepository.save(Staff));
    }

    //delete
    
    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    //find all Staff
    
    @Override
    public Page<StaffDTO> findAllStaff(SearchCriteria searchCriteria) {
        Page<Staff> Staffs = staffRepository.findAll(Search.getPageable(searchCriteria));
        return Staffs.map(staffMapper::apply);
    }

    //find all Staff by fullname
    
    @Override
    public Page<StaffDTO> findStaffByFullname(String fullname, SearchCriteria searchCriteria) {
        Page<Staff> Staffs = staffRepository.findAllByFullname(fullname, Search.getPageable(searchCriteria));
        return Staffs.map(staffMapper::apply);
    }

    //findByPhone
    @Override
    public StaffDTO findByPhone(String phone) {
        Optional<Staff> dto = staffRepository.findByPhone(phone);
        return dto.map(staffMapper::apply).orElse(null);
    }
}
