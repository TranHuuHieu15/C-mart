package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Trip;
import com.sti.cmart.entity.Voucher;
import com.sti.cmart.model.dto.VoucherDTO;
import com.sti.cmart.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class VoucherMapper implements Function<Voucher, VoucherDTO> {

    private final TripRepository tripRepository;

    @Override
    public VoucherDTO apply(Voucher voucher) {
        return VoucherDTO.builder()
                .id(voucher.getId())
                .name(voucher.getName())
                .description(voucher.getDescription())
                .discount(voucher.getDiscount())
                .maxDiscount(voucher.getMaxDiscount())
                .minDiscount(voucher.getMinDiscount())
                .status(voucher.getStatus())
                .tripId(voucher.getTrip().getId())
                .build();
    }

    public Voucher applyToVoucher(VoucherDTO dto) {
        Trip trip = tripRepository.findById(dto.getTripId()).get();
        return Voucher.builder()
                .id(dto.getId())
                .name(dto.getName())
                .discount(dto.getDiscount())
                .description(dto.getDescription())
                .minDiscount(dto.getMinDiscount())
                .maxDiscount(dto.getMaxDiscount())
                .status(dto.getStatus())
                .trip(trip)
                .build();
    }
}
