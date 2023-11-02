package com.sti.cmart.controller;

import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.facade.WardFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.model.dto.WardDTO;
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

@Tag(name = "WardController", description = "Ward controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/wards")
public class WardController {

    private final WardFacade wardFacade;

    //findById
    @SneakyThrows
    @GetMapping("/{id}")
    @Operation(summary = "find by id ward", description = "find by id ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, wardFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all ward", description = "find all ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, wardFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //findByName
    @SneakyThrows
    @GetMapping("/findByName/{name}")
    @Operation(summary = "find by name ward", description = "find by name ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
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
    public ResponseEntity<Object> findByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseHandler.response(HttpStatus.OK, wardFacade.findByName(name, new SearchCriteria(page, size, sortBy)), true);
    }

    //save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create ward", description = "create ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> save(@RequestBody WardDTO wardDTO) {
        return ResponseHandler.response(HttpStatus.OK, wardFacade.save(wardDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update ward", description = "update ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "response failed",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = String.class),
                                    mediaType = "application/json"
                            )
                    }
            )}
    )
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody WardDTO wardDTO) {
        return ResponseHandler.response(HttpStatus.OK, wardFacade.update(id, wardDTO), true);
    }

    //delete
    @SneakyThrows
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete ward", description = "delete ward")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = WardDTO.class),
                                    mediaType = "application/json"
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
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
        wardFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa thành công!", true);
    }

}
