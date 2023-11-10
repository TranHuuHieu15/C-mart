package com.sti.cmart.controller;

import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.facade.AccountFacade;
import com.sti.cmart.model.common.ResponseHandler;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.dto.RegisterDTO;
import com.sti.cmart.util.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sti.cmart.util.Constants.Paths.APP_PATH;

@Tag(name = "AccountController", description = "Account controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(APP_PATH + "/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    @SneakyThrows
    @PutMapping("/update/{id}")
    @Operation(summary = "update account", description = "update account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDTO.class),
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
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.update(id, accountDTO), true);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete account", description = "delete account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDTO.class),
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
    public ResponseEntity<Object> delete(@PathVariable Long id) throws ArchitectureException {
        accountFacade.delete(id);
        return ResponseHandler.response(HttpStatus.OK, "Xóa thành công!", true);
    }


    @GetMapping("/findAll")
    @Operation(summary = "find all account", description = "find all account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDTO.class),
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
    public ResponseEntity<Object> findAll
            (
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "id") String sortBy
            ) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.findAllCustomer(new SearchCriteria(page, size, sortBy)), true);
    }

    @GetMapping("/findById/{id}")
    @Operation(summary = "find by id account", description = "find by id account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = AccountDTO.class),
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
    public ResponseEntity<Object> findById(@PathVariable Long id) throws ArchitectureException {
        return ResponseHandler.response(HttpStatus.OK, accountFacade.findById(id), true);
    }

}
