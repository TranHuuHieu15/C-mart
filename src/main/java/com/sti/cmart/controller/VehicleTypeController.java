package com.sti.cmart.controller;

import com.sti.cmart.facade.VehicleTypeFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.model.dto.VehicleTypeDTO;
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

@Tag(name = "VehicleType", description = "VehicleType controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/vehicleTypes")
public class VehicleTypeController {

    private final VehicleTypeFacade vehicleTypeFacade;

    //findById
    @SneakyThrows
    @GetMapping("/findById/{id}")
    @Operation(summary = "find by id vehicleType", description = "find by id vehicleType")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleTypeDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, vehicleTypeFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    public ResponseEntity<Object> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseHandler.response(HttpStatus.OK, vehicleTypeFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create vehicleType", description = "create vehicleType")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleTypeDTO.class),
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
    public ResponseEntity<Object> save(@RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseHandler.response(HttpStatus.OK, vehicleTypeFacade.save(vehicleTypeDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update vehicleType", description = "update vehicleType")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleTypeDTO.class),
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
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseHandler.response(HttpStatus.OK, vehicleTypeFacade.update(id, vehicleTypeDTO), true);
    }

    //delete
    @SneakyThrows
    @GetMapping("/delete/{id}")
    @Operation(summary = "delete vehicleType", description = "delete vehicleType")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = VehicleTypeDTO.class),
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
        vehicleTypeFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK,"Xóa thành công!" , true);
    }
}
