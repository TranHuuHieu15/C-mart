package com.sti.cmart.service.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.sti.cmart.service.GoogleMapService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapServiceImpl implements GoogleMapService {

    private final GeoApiContext geoContext;


    //Tính khỏa cách giữa 2 điểm
    public DistanceMatrixElement getDistance(@NonNull LatLng origin, @NonNull LatLng destination) throws IOException, InterruptedException, ApiException {

        // Tính toán khoảng cách
        DistanceMatrix distanceMatrix = DistanceMatrixApi
                .newRequest(geoContext)
                .origins(origin)
                .destinations(destination)
                .mode(TravelMode.DRIVING)
                .await();

        // Lấy khoảng cách
        return distanceMatrix.rows[0].elements[0];

    }

    public GeocodingResult[] getGeocoding(@NonNull String address) throws InterruptedException, ApiException, IOException {

        return GeocodingApi
                .geocode(geoContext, address)
                .await(); // Lấy thông tin địa chỉ
    }

    public GeocodingResult[] reverseGeocode(LatLng latLng) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .reverseGeocode(geoContext, latLng)
                .await()
                ; // Lấy thông tin địa chỉ
    }


}
