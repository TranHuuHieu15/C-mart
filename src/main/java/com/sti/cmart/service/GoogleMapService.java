package com.sti.cmart.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.sti.cmart.other.request.DistanceRequest;
import lombok.NonNull;

import java.io.IOException;

public interface GoogleMapService {

    //* tìm vị trị dựa trên điểm đón và điểm đến
    DistanceMatrix getDistance(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException;

    //* Lấy thông tin về địa chỉ dựa trên địa chỉ cung cấp
    GeocodingResult[] getGeocoding(@NonNull String address) throws InterruptedException, ApiException, IOException;

    //* lấy thông tin địa chỉ dựa trên một vị trí địa lý(latLng)
    GeocodingResult[] getAddress(LatLng latLng) throws IOException, InterruptedException, ApiException;

    DistanceMatrix getDistanceAndDuration(@NonNull String originAddress, @NonNull String destinationAddress) throws InterruptedException, ApiException, IOException;

    //Lấy ra thành phố dựa trên địa chỉ
    String getCity(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException;
}
