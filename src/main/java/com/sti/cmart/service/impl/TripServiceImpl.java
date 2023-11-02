package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Trip;
import com.sti.cmart.model.dto.TripDTO;
import com.sti.cmart.model.mapper.TripMapper;
import com.sti.cmart.repository.TripRepository;
import com.sti.cmart.service.TripService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    //findById
    public TripDTO findById(Long id) {
        Optional<Trip> dto = tripRepository.findById(id);
        return dto.map(tripMapper::apply).orElse(null);
    }

    //findAll
    public Page<TripDTO> findAll(SearchCriteria searchCriteria) {
        Page<Trip> dto = tripRepository.findAll(Search.getPageable(searchCriteria));
        return dto.map(tripMapper::apply);
    }

    //save
    public TripDTO save(TripDTO tripDTO) {
        Trip trip = tripMapper.applyToTrip(tripDTO);
        return tripMapper.apply(tripRepository.save(trip));
    }

    //delete
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }
}
