package com.sti.cmart.controller;


import com.sti.cmart.facade.RegionApplyFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.ProvinceDTO;
import com.sti.cmart.model.dto.RegionApplyDTO;
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

@Tag(name = "RegionApplyController", description = "RegionApply controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(STAFF_PATH + "/regionApply")
public class RegionApplyController {

    private final RegionApplyFacade regionApplyFacade;

    //findById
    @SneakyThrows
    @GetMapping("/{id}")
    @Operation(summary = "find by id regionApply", description = "find by id regionApply")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegionApplyDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, regionApplyFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all regionApply", description = "find all regionApply")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegionApplyDTO.class),
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
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseHandler.response(HttpStatus.OK, regionApplyFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //save
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "save regionApply", description = "save regionApply")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegionApplyDTO.class),
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
    public ResponseEntity<Object> save(@RequestBody RegionApplyDTO dto) {
        return ResponseHandler.response(HttpStatus.OK, regionApplyFacade.save(dto), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update regionApply", description = "update regionApply")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegionApplyDTO.class),
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
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody RegionApplyDTO dto) {
        return ResponseHandler.response(HttpStatus.OK, regionApplyFacade.update(id, dto), true);
    }

    //delete
    @SneakyThrows
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete regionApply", description = "delete regionApply")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegionApplyDTO.class),
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
        regionApplyFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa thành công!", true);
    }
}
