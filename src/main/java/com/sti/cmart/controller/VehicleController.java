package com.sti.cmart.controller;

import com.sti.cmart.facade.VehicleFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.util.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sti.cmart.util.Constants.Paths.STAFF_PATH;

@Tag(name = "VehicleController", description = "Vehicle controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/vehicles")
public class VehicleController {

    private final VehicleFacade vehicleFacade;

    //findById
    @SneakyThrows
    @GetMapping("/findById/{id}")
    @Operation(summary = "find by id vehicle", description = "find by id vehicle")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ResponseHandler.response(HttpStatus.OK, vehicleFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all vehicle", description = "find all vehicle")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseHandler.response(HttpStatus.OK, vehicleFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //Save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create vehicle", description = "create vehicle")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> save(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseHandler.response(HttpStatus.OK, vehicleFacade.save(vehicleDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update vehicle", description = "update vehicle")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        return ResponseHandler.response(HttpStatus.OK, vehicleFacade.update(id, vehicleDTO), true);
    }

    //delete
    @SneakyThrows
    @GetMapping("/delete/{id}")
    @Operation(summary = "delete vehicle", description = "delete vehicle")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        vehicleFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa thành công!", true);
    }

}
