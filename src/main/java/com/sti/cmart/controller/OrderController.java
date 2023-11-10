package com.sti.cmart.controller;

import com.google.maps.errors.ApiException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.facade.ServiceFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.other.request.DistanceRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.sti.cmart.util.Constants.Paths.APP_PATH;

@Tag(name = "OrderController", description = "Order controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(APP_PATH + "/order")
public class OrderController {

    private final ServiceFacade serviceFacade;

    //Nhập 2 địa chỉ đến và hiện tại
    @PostMapping("/distance-and-duration")
    public ResponseEntity<Object> getDistanceAndDuration(@RequestBody DistanceRequest distanceRequest) throws ArchitectureException, IOException, InterruptedException, ApiException {
        return ResponseHandler.response(HttpStatus.OK, serviceFacade.findByCityByAddress(distanceRequest), true);
    }
}
