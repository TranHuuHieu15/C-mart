package com.sti.cmart.service;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import lombok.NonNull;

import java.io.IOException;

public interface GoogleMapService {
    //todo: Tính khỏa cách giữa 2 điểm origin và destination (LatLng: là vị trí địa lý)
    DistanceMatrixElement getDistance(@NonNull LatLng origin, @NonNull LatLng destination) throws IOException, InterruptedException, ApiException;

    //TODO: Lấy thông tin về địa chỉ dựa trên địa chỉ cung cấp
    GeocodingResult[] getGeocoding(@NonNull String address) throws InterruptedException, ApiException, IOException;

    //FIXME: lấy thông tin địa chỉ dựa trên một vị trí địa lý(latLng)
    GeocodingResult[] reverseGeocode(LatLng latLng) throws IOException, InterruptedException, ApiException;
}
