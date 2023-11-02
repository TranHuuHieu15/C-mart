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


    //todo: Tính khỏa cách giữa 2 điểm origin và destination (LatLng: là vị trí địa lý)
    @Override
    public DistanceMatrixElement getDistance(@NonNull LatLng origin, @NonNull LatLng destination) throws IOException, InterruptedException, ApiException {

        // Tính toán khoảng cách
        DistanceMatrix distanceMatrix = DistanceMatrixApi
                .newRequest(geoContext)
                .origins(origin) // điểm xuất phát
                .destinations(destination) // điểm đến
                .mode(TravelMode.DRIVING) //tính toán bằng phương tiện gì
                .await();

        // Lấy khoảng cách
        return distanceMatrix.rows[0].elements[0];
    }

    //TODO: Lấy thông tin về địa chỉ dựa trên địa chỉ cung cấp
    @Override
    public GeocodingResult[] getGeocoding(@NonNull String address) throws InterruptedException, ApiException, IOException {
        return GeocodingApi
                .geocode(geoContext, address)
                .await();
    }

    //FIXME: lấy thông tin địa chỉ dựa trên một vị trí địa lý(latLng)
    @Override
    public GeocodingResult[] reverseGeocode(LatLng latLng) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .reverseGeocode(geoContext, latLng)
                .await()
                ; // Lấy thông tin địa chỉ
    }

    @Override
    public DistanceMatrix getDistanceAndDuration(@NonNull String originAddress, @NonNull String destinationAddress) throws InterruptedException, ApiException, IOException {
        // Lấy thông tin vị trí từ địa chỉ
        GeocodingResult[] originResults = getGeocoding(originAddress);
        GeocodingResult[] destinationResults = getGeocoding(destinationAddress);

        // Lấy vị trí địa lý từ kết quả
        LatLng originLatLng = originResults[0].geometry.location;
        System.out.println("Điểm đón: " + originLatLng);
        LatLng destinationLatLng = destinationResults[0].geometry.location;
        System.out.println("Điểm trả: " + destinationLatLng);

        // Tính toán khoảng cách và thời gian
        return DistanceMatrixApi
                .newRequest(geoContext)
                .origins(originLatLng)
                .destinations(destinationLatLng)
                .mode(TravelMode.DRIVING)
                .await();
    }

}
