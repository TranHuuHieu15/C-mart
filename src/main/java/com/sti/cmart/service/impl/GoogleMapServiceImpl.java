package com.sti.cmart.service.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.service.GoogleMapService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapServiceImpl implements GoogleMapService {

    private final GeoApiContext geoContext;


    //* tìm vị trị dựa trên điểm đón và điểm đến
    @Override
    public DistanceMatrix getDistance(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException {
        LatLng origin = new LatLng(distanceRequest.getStartLat(), distanceRequest.getStartLng());
        LatLng destination = new LatLng(distanceRequest.getFinishLat(), distanceRequest.getFinishLng());
        // Tính toán khoảng cách
        return  DistanceMatrixApi
                .newRequest(geoContext)
                .origins(origin) // điểm xuất phát
                .destinations(destination) // điểm đến
                .mode(TravelMode.DRIVING) //tính toán bằng phương tiện gì
                .await();

    }

    //* Lấy thông tin về địa chỉ dựa trên địa chỉ cung cấp
    @Override
    public GeocodingResult[] getGeocoding(@NonNull String address) throws InterruptedException, ApiException, IOException {
        return GeocodingApi
                .geocode(geoContext, address)
                .await();
    }

    //* lấy thông tin địa chỉ dựa trên một vị trí địa lý(latLng)
    @Override
    public GeocodingResult[] getAddress(LatLng latLng) throws IOException, InterruptedException, ApiException {
        return GeocodingApi
                .reverseGeocode(geoContext, latLng)
                .await(); // Lấy thông tin địa chỉ
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

    //Lấy ra thành phố dựa trên địa chỉ
    @Override
    public String getCity(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException {
        GeocodingResult[] results = GeocodingApi.newRequest(geoContext)
                .latlng(new LatLng(distanceRequest.getStartLat(), distanceRequest.getStartLng()))
                .resultType(AddressType.ADMINISTRATIVE_AREA_LEVEL_1)
                .await();
        return results[0].formattedAddress;
    }

}
