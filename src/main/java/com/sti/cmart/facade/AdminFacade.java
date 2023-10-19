package com.sti.cmart.facade;

import com.sti.cmart.entity.Admin;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.AdminDTO;
import com.sti.cmart.service.AdminService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminService adminService;

    //find by Id
    public AdminDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        AdminDTO adminDTO = adminService.findById(id);
        if (adminDTO == null)
            throw new EntityNotFoundException();
        return  adminDTO;
    }

    //find by username
    public AdminDTO findByUsername(String username) throws ArchitectureException {
        if (username == null)
            throw new InvalidParamException();
        AdminDTO adminDTO = adminService.findByUsername(username);
        if (adminDTO == null)
            throw new EntityNotFoundException();
        return  adminDTO;
    }

    //find by email
    public AdminDTO findByEmail(String email) throws ArchitectureException {
        if (email == null)
            throw new InvalidParamException();
        AdminDTO adminDTO = adminService.findByEmail(email);
        if (adminDTO == null)
            throw new EntityNotFoundException();
        return  adminDTO;
    }

    //save
    public AdminDTO save(AdminDTO adminDTO) throws ArchitectureException {
        if (adminDTO == null)
            throw new InvalidParamException();
        AdminDTO dto = adminService.findById(adminDTO.getId());
        AdminDTO email = adminService.findByEmail(adminDTO.getEmail());
        AdminDTO username = adminService.findByUsername(adminDTO.getUsername());
        AdminDTO phone = adminService.findByPhone(adminDTO.getPhone());
        if (dto != null || email != null || username != null || phone != null)
            throw new EntityAlreadyExistException();
        return adminService.save(adminDTO);
    }

    //update
    public AdminDTO update(AdminDTO adminDTO) throws ArchitectureException {
        if (adminDTO == null)
            throw new InvalidParamException();
        checkIdIsExists(adminDTO.getId());
        AdminDTO email = adminService.findByEmail(adminDTO.getEmail());
        AdminDTO username = adminService.findByUsername(adminDTO.getUsername());
        AdminDTO phone = adminService.findByPhone(adminDTO.getPhone());
        if (email == null || username == null || phone == null)
            throw new EntityAlreadyExistException();
        return adminService.save(adminDTO);
    }

    public AdminDTO checkIdIsExists(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        AdminDTO adminDTO = adminService.findById(id);
        if (adminDTO == null)
            throw new EntityNotFoundException();
        return adminDTO;
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        checkIdIsExists(id);
        adminService.delete(id);
    }

    //find all admin
    public Page<AdminDTO> findAllAdmin(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<AdminDTO> admins = adminService.findAllAdmin(searchCriteria);
        if (admins == null || admins.isEmpty())
            throw new EntityNotFoundException();
        return admins;
    }

    //find All admin by fullname
    public Page<AdminDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) throws ArchitectureException {
        if (fullname == null)
            throw new InvalidParamException();
        Page<AdminDTO> admins = adminService.findAdminByFullname(fullname, searchCriteria);
        if (admins == null || admins.isEmpty())
            throw new EntityNotFoundException();
        return admins;
    }

    //find by phone
    public AdminDTO findByPhone(String phone) throws ArchitectureException {
        if (phone == null)
            throw new InvalidParamException();
        AdminDTO adminDTO = adminService.findByPhone(phone);
        if (adminDTO == null)
            throw new EntityNotFoundException();
        return  adminDTO;
    }
}
