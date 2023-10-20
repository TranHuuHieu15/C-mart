package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.entity.Staff;
import com.sti.cmart.model.dto.CustomerDTO;
import com.sti.cmart.repository.CustomerRepository;
import com.sti.cmart.repository.StaffRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CustomerMapper implements Function<Customer, CustomerDTO> {

    private final StaffRepository staffRepository;

    @Override
    public CustomerDTO apply(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .fullname(customer.getFullname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .isActive(customer.getIsActive())
                .address(customer.getAddress())
                .image(customer.getImage())
                .status(customer.getStatus())
                .staffId(customer.getStaff().getId())
                .build();
    }

    public Customer applyToCustomer(CustomerDTO customerDTO) {
        Staff staff = staffRepository.findById(customerDTO.getStaffId()).get();
        return Customer.builder()
                .id(customerDTO.getId())
                .username(customerDTO.getUsername())
                .password(customerDTO.getPassword())
                .fullname(customerDTO.getFullname())
                .phone(customerDTO.getPhone())
                .email(customerDTO.getEmail())
                .isActive(customerDTO.getIsActive())
                .address(customerDTO.getAddress())
                .image(customerDTO.getImage())
                .status(customerDTO.getStatus())
                .staff(staff)
                .build();
    }
}
