package com.sti.cmart.controller;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.sti.cmart.facade.GoogleMapFacade;
import com.sti.cmart.other.request.AddressRequest;
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
    public ResponseEntity<String> getDistance(@RequestBody MapRequest mapRequest) {
        LatLng origin = new LatLng(mapRequest.getOriginLat(), mapRequest.getOriginLng());
        LatLng destination = new LatLng(mapRequest.getDestLat(), mapRequest.getDestLng());
        try {
            String distance = googleMapFacade.getDistance(origin, destination);
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

    @PostMapping("/reverse-geocoding")
    public ResponseEntity<String> reverseGeocode(@RequestBody MapRequest mapRequest) {
        LatLng latLng = new LatLng(mapRequest.getOriginLat(), mapRequest.getOriginLng());
        try {
            String reverseGeocode = googleMapFacade.reverseGeocode(latLng);
            return ResponseEntity.ok(reverseGeocode);
        } catch (IOException | InterruptedException | ApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi");
        }
    }

}
