package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.StaffDTO;
import com.sti.cmart.service.StaffService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffFacade {

    private final StaffService staffService;

    //find by Id
    public StaffDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        StaffDTO staffDTO = staffService.findById(id);
        if (staffDTO == null)
            throw new EntityNotFoundException();
        return  staffDTO;
    }

    //find by username
    public StaffDTO findByUsername(String username) throws ArchitectureException {
        if (username == null)
            throw new InvalidParamException();
        StaffDTO staffDTO = staffService.findByUsername(username);
        if (staffDTO == null)
            throw new EntityNotFoundException();
        return  staffDTO;
    }

    //find by email
    public StaffDTO findByEmail(String email) throws ArchitectureException {
        if (email == null)
            throw new InvalidParamException();
        StaffDTO staffDTO = staffService.findByEmail(email);
        if (staffDTO == null)
            throw new EntityNotFoundException();
        return  staffDTO;
    }

    //save
    public StaffDTO save(StaffDTO staff) throws ArchitectureException {
        if (staff == null)
            throw new InvalidParamException();
        StaffDTO dto = staffService.findById(staff.getId());
        StaffDTO email = staffService.findByEmail(staff.getEmail());
        StaffDTO username = staffService.findByUsername(staff.getUsername());
        StaffDTO phone = staffService.findByPhone(staff.getPhone());
        if (dto != null || email != null || username != null || phone != null)
            throw new EntityAlreadyExistException();
        return staffService.save(staff);
    }

    //update
    public StaffDTO update(StaffDTO staffDTO) throws ArchitectureException {
        if (staffDTO == null)
            throw new InvalidParamException();
        checkIdIsExists(staffDTO.getId());
        StaffDTO email = staffService.findByEmail(staffDTO.getEmail());
        StaffDTO username = staffService.findByUsername(staffDTO.getUsername());
        StaffDTO phone = staffService.findByPhone(staffDTO.getPhone());
        if (email == null || username == null || phone == null)
            throw new EntityAlreadyExistException();
        return staffService.save(staffDTO);
    }

    public StaffDTO checkIdIsExists(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        StaffDTO staffDTO = staffService.findById(id);
        if (staffDTO == null)
            throw new EntityNotFoundException();
        return staffDTO;
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        checkIdIsExists(id);
        staffService.delete(id);
    }

    //find all admin
    public Page<StaffDTO> findAllAdmin(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<StaffDTO> list = staffService.findAllStaff(searchCriteria);
        if (list == null || list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //find All admin by fullname
    public Page<StaffDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) throws ArchitectureException {
        if (fullname == null)
            throw new InvalidParamException();
        Page<StaffDTO> list = staffService.findStaffByFullname(fullname, searchCriteria);
        if (list == null || list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //find by phone
    public StaffDTO findByPhone(String phone) throws ArchitectureException {
        if (phone == null)
            throw new InvalidParamException();
        StaffDTO staffDTO = staffService.findByPhone(phone);
        if (staffDTO == null)
            throw new EntityNotFoundException();
        return  staffDTO;
    }
}
