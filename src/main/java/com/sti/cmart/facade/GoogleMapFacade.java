package com.sti.cmart.facade;

import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.service.GoogleMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GoogleMapFacade {

    private final GoogleMapService googleMapService;

    public String getDistance(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException {
        DistanceMatrix distanceMatrixElement = googleMapService.getDistance(distanceRequest);
        return "Khoảng cách: " + distanceMatrixElement.rows[0].elements[0].distance.humanReadable + "\n" +
                "Thời gian: " + distanceMatrixElement.rows[0].elements[0].duration.humanReadable + "\n" +
                "Điểm đón: " + distanceMatrixElement.originAddresses[0] + "\n" + "Điểm đến: " + distanceMatrixElement.destinationAddresses[0] + "\n" +
                "Thành phố: " + googleMapService.getCity(distanceRequest)
                ;
    }

    public GeocodingResult[] getGeocoding(String address) throws InterruptedException, ApiException, IOException {
        return googleMapService.getGeocoding(address);
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
