package com.sti.cmart.controller;

import com.sti.cmart.facade.AddressFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.AddressDTO;
import com.sti.cmart.model.dto.DistrictDTO;
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

@Tag(name = "AddressController", description = "Address controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/addresses")
public class AddressController {

    private final AddressFacade addressFacade;

    @SneakyThrows
    @GetMapping("/{id}")
    @Operation(summary = "find by id address", description = "find by id address")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AddressDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, addressFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all address", description = "find all address")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AddressDTO.class),
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
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "size", defaultValue = "10") Integer size,
        @RequestParam(value = "sort", defaultValue = "id") String sort
    ) {
        return ResponseHandler.response(HttpStatus.OK, addressFacade.findAll(new SearchCriteria(page, size, sort)), true);
    }

    //save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create address", description = "create address")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AddressDTO.class),
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
    public ResponseEntity<Object> save(@RequestBody AddressDTO addressDTO) {
        return ResponseHandler.response(HttpStatus.OK, addressFacade.save(addressDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update address", description = "update address")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AddressDTO.class),
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
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return ResponseHandler.response(HttpStatus.OK, addressFacade.update(id, addressDTO), true);
    }

    //delete
    @SneakyThrows
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete address", description = "delete address")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AddressDTO.class),
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
        addressFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa địa chỉ thành công!", true);
    }

}
