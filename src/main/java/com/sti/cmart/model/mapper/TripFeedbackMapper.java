package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Trip;
import com.sti.cmart.entity.TripFeedback;
import com.sti.cmart.model.dto.TripFeedbackDTO;
import com.sti.cmart.repository.TripFeedbackRepository;
import com.sti.cmart.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TripFeedbackMapper implements Function<TripFeedback, TripFeedbackDTO> {
    private final TripRepository tripRepository;

    @Override
    public TripFeedbackDTO apply(TripFeedback tripFeedback) {
        return TripFeedbackDTO.builder()
                .id(tripFeedback.getId())
                .tripId(tripFeedback.getTrip().getId())
                .content(tripFeedback.getContent())
                .star(tripFeedback.getStar())
                .attitude(tripFeedback.getAttitude())
                .safety(tripFeedback.getSafety())
                .speed(tripFeedback.getSpeed())
                .efficiency(tripFeedback.getEfficiency())
                .build();
    }

    public TripFeedback applyToTripFeedback(TripFeedbackDTO tripFeedbackDTO) {
        Trip tripFeedback = tripRepository.findById(tripFeedbackDTO.getTripId()).get();
        return TripFeedback.builder()
                .id(tripFeedbackDTO.getId())
                .trip(tripFeedback)
                .content(tripFeedbackDTO.getContent())
                .star(tripFeedbackDTO.getStar())
                .attitude(tripFeedbackDTO.getAttitude())
                .safety(tripFeedbackDTO.getSafety())
                .speed(tripFeedbackDTO.getSpeed())
                .efficiency(tripFeedbackDTO.getEfficiency())
                .build();
    }
}
