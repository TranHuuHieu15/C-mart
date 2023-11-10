package com.sti.cmart.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.sti.cmart.facade.GoogleMapFacade;
import com.sti.cmart.other.request.AddressRequest;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.other.request.MapRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.sti.cmart.util.Constants.Paths.APP_PATH;

@Tag(name = "GoogleMapController", description = "GoogleMap controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(APP_PATH + "/google-map")
public class GoogleMapController {

    private final GoogleMapFacade googleMapFacade;

    @PostMapping("/distance")
    public ResponseEntity<Object> getDistance(@RequestBody DistanceRequest distanceRequest) {
        LatLng origin = new LatLng(distanceRequest.getStartLat(), distanceRequest.getStartLng());
        LatLng destination = new LatLng(distanceRequest.getFinishLat(), distanceRequest.getFinishLng());
        try {
            String distance = googleMapFacade.getDistance(distanceRequest);
            return ResponseEntity.ok(distance);
        } catch (IOException | InterruptedException | ApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi");
        }
    }

    @PostMapping("/geocode")
    public ResponseEntity<Object> getGeocoding(@RequestBody AddressRequest addressRequest) {
        String address = addressRequest.getAddress();
        try {
            GeocodingResult[] geocodingResults = googleMapFacade.getGeocoding(address);
            System.out.println(geocodingResults[0].formattedAddress); // Lấy ra được địa chị cụ thể
            System.out.println(geocodingResults[0].geometry.location.lat); // Lấy ra được vị trí địa lý
            System.out.println(geocodingResults[0].geometry.location.lng); // Lấy ra được vị trí địa lý
            System.out.println(geocodingResults[0].geometry.location); // Lấy ra được vị trí địa lý
            return ResponseEntity.ok(geocodingResults);
        } catch (InterruptedException | ApiException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi");
        }
    }

    //Nhập 2 địa chỉ đến và hiện tại
    @PostMapping("/distance-and-duration2")
    public ResponseEntity<Object> getDistanceAndDuration(@RequestBody MapRequest mapRequest) {
        String originAddress = mapRequest.getOriginAddress();
        String destinationAddress = mapRequest.getDestAddress();
        try {
            Distance distanceAndDuration = googleMapFacade.getDistanceAndDuration(originAddress, destinationAddress).distance;
            return ResponseEntity.ok(distanceAndDuration);
        } catch (InterruptedException | ApiException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi");
        }
    }

}
