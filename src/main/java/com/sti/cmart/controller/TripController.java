package com.sti.cmart.controller;

import com.sti.cmart.facade.TripFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.dto.TripDTO;
import com.sti.cmart.service.TripService;
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

import static com.sti.cmart.util.Constants.Paths.APP_PATH;

@Tag(name = "AccountController", description = "Account controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(APP_PATH + "/trips")
public class TripController {

    private final TripFacade tripFacade;

    //findById
    @SneakyThrows
    @GetMapping("/findById/{id}")
    @Operation(summary = "find account by id", description = "find account by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TripDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, tripFacade.findById(id), true);
    }

    //findAll
    @SneakyThrows
    @GetMapping("")
    @Operation(summary = "find all account", description = "find all account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TripDTO.class),
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
        return ResponseHandler.response(HttpStatus.OK, tripFacade.findAll(new SearchCriteria(page, size, sortBy)), true);
    }

    //create
    @SneakyThrows
    @PostMapping("/create")
    @Operation(summary = "create account", description = "create account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TripDTO.class),
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
    public ResponseEntity<Object> create(@RequestBody TripDTO tripDTO) {
        return ResponseHandler.response(HttpStatus.OK, tripFacade.save(tripDTO), true);
    }

    //update
    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update account", description = "update account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = TripDTO.class),
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
    public ResponseEntity<Object> update (
            @PathVariable Long id,
            @RequestBody TripDTO tripDTO
    ) {
        return ResponseHandler.response(HttpStatus.OK, tripFacade.update(id, tripDTO), true);
    }

    //delete
    @SneakyThrows
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete account by id", description = "delete account by id")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        tripFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa thành công!", true);
    }
}
