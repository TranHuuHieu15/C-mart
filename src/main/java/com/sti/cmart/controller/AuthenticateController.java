package com.sti.cmart.controller;

import com.sti.cmart.facade.AccountFacade;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.dto.LoginDTO;
import com.sti.cmart.model.dto.RegisterDTO;
import com.sti.cmart.other.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sti.cmart.util.Constants.Paths.APP_PATH;

@Tag(name = "AuthenticateController", description = "Authenticate controller")
@RestController
@RequiredArgsConstructor
@RequestMapping(APP_PATH)
public class AuthenticateController {

    private final AccountFacade accountFacade;

    @SneakyThrows
    @PostMapping("/register")
    @Operation(summary = "register account", description = "create account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = RegisterDTO.class),
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
    public ResponseEntity<?> register(@RequestBody AccountDTO registerResponse) {
        return accountFacade.save(registerResponse);
    }

    @SneakyThrows
    @PostMapping("/login")
    @Operation(summary = "login account", description = "login account")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "response success",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = LoginDTO.class),
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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return accountFacade.login(loginRequest);
    }


}
