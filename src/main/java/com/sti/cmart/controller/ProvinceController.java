package com.sti.cmart.controller;


import com.sti.cmart.facade.ProvinceFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.dto.ProvinceDTO;
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

@Tag(name = "ProvinceController", description = "Province controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/provinces")
public class ProvinceController {

    private final ProvinceFacade provinceFacade;

    //findById
    @SneakyThrows
    @GetMapping("/{id}")
    @Operation(summary = "find by id province", description = "find by id province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProvinceDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, provinceFacade.findById(id), true);
    }

    //find all
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all province", description = "find all province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProvinceDTO.class),
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
    ) {
        return ResponseHandler.response(HttpStatus.OK, provinceFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //findByName
    @SneakyThrows
    @GetMapping("/findByName/{name}")
    @Operation(summary = "find by name province", description = "find by name province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProvinceDTO.class),
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
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseHandler.response(HttpStatus.OK, provinceFacade.findByName(name, new SearchCriteria(page, size, sortBy)), true);
    }

    //save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create province", description = "create province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProvinceDTO.class),
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
    public ResponseEntity<Object> save(@RequestBody ProvinceDTO provinceDTO) {
        return ResponseHandler.response(HttpStatus.OK, provinceFacade.save(provinceDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update province", description = "update province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = ProvinceDTO.class),
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
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ProvinceDTO provinceDTO) {
        return ResponseHandler.response(HttpStatus.OK, provinceFacade.update(id, provinceDTO), true);
    }

    //delete
    @SneakyThrows
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete province", description = "delete province")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = Long.class),
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
        provinceFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Đã xóa thành công!", true);
    }
}
