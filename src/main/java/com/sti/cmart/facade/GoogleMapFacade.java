package com.sti.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.sti.cmart.service.GoogleMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapFacade {

    private final GoogleMapService googleMapService;

    public String getDistance(LatLng origin, LatLng destination) throws IOException, InterruptedException, ApiException {
        DistanceMatrixElement distanceMatrixElement = googleMapService.getDistance(origin, destination);

        return "Khoảng cách: " + distanceMatrixElement.distance;
    }

    public GeocodingResult[] getGeocoding(String address) throws InterruptedException, ApiException, IOException {
        return googleMapService.getGeocoding(address);
    }

    public String reverseGeocode(LatLng latLng) throws IOException, InterruptedException, ApiException {
        return googleMapService.reverseGeocode(latLng)[0].formattedAddress;
    }

}
