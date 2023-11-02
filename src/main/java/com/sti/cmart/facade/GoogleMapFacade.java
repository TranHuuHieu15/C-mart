package com.sti.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
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

    public DistanceMatrixElement getDistanceAndDuration(String originAddress, String destinationAddress) throws InterruptedException, ApiException, IOException {
        // Gọi phương thức đã tạo trước đó để lấy DistanceMatrix
        DistanceMatrix distanceMatrix = googleMapService.getDistanceAndDuration(originAddress, destinationAddress);
        // Trả về chuỗi kết quả
        return distanceMatrix.rows[0].elements[0];
    }

//    public Double getDistanceAndDuration(String originAddress, String destinationAddress) throws InterruptedException, ApiException, IOException {
//        // Gọi phương thức đã tạo trước đó để lấy DistanceMatrix
//        DistanceMatrix distanceMatrix = googleMapService.getDistanceAndDuration(originAddress, destinationAddress);
//        // Lấy khoảng cách từ DistanceMatrix
//        Distance distance = distanceMatrix.rows[0].elements[0].distance;
//        // Tạo chuỗi đại diện cho khoảng cách và thời gian
//        double distanceString = Double.parseDouble(distance.humanReadable);
//
//        // Trả về chuỗi kết quả
//        return distanceString;
//    }

}
