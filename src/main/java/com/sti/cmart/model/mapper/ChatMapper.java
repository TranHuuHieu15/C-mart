package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.Trip;
import com.sti.cmart.model.dto.ChatDTO;
import com.sti.cmart.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

//@Service
//@RequiredArgsConstructor
//public class ChatMapper implements Function<Chat, ChatDTO> {
//
//    private final CustomerRepository customerRepository;
//    private final DriverRepository driverRepository;
//    private final TripRepository tripRepository;
//
//    @Override
//    public ChatDTO apply(Chat chat) {
//        return ChatDTO.builder()
//                .id(chat.getId())
//                .content(chat.getContent())
//                .date(chat.getDate())
//                .customerId(chat.getCustomer().getId())
//                .driverId(chat.getDriver().getId())
//                .tripId(chat.getTrip().getId())
//                .build();
//    }
//
//    public Chat applyToChat(ChatDTO chatDTO) {
//        Trip ttrip =  tripRepository.findById(chatDTO.getTripId()).get();
//        Customer customer = customerRepository.findById(chatDTO.getCustomerId()).get();
//        Driver driver = driverRepository.findById(chatDTO.getDriverId()).get();
//        return Chat.builder()
//                .id(chatDTO.getId())
//                .content(chatDTO.getContent())
//                .date(chatDTO.getDate())
//                .customer(customer)
//                .driver(driver)
//                .trip(ttrip)
//                .build();
//    }
//}
