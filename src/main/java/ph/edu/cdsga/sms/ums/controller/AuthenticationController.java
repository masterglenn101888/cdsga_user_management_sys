package ph.edu.cdsga.sms.ums.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.LoginDto;
import ph.edu.cdsga.sms.ums.service.AuthenticationService;
import ph.edu.cdsga.sms.ums.service.GenerateUUIDService;
import ph.edu.cdsga.sms.ums.service.LoggingService;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("${endpoint.url}")
@Tag(name = "Login/Authentication Endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final GenerateUUIDService generateUUIDService;
    private final LoggingService loggingService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    GenerateUUIDService generateUUIDService,
                                    LoggingService loggingService) {
        this.authenticationService = authenticationService;
        this.generateUUIDService = generateUUIDService;
        this.loggingService = loggingService;
    }

    @Operation(summary = "Health Check")
    @GetMapping(path = "/healthCheck")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    /** This endpoint accepts and authenticate the incoming request from user.
     *  It returns a SuccessResponse payload with status and token once the account is successfully login.
     *
     * @param loginDto Login data transfer object
     * @param request HttpServletRequest
     * @return SuccessResponse
     * @see #authenticate(LoginDto, HttpServletRequest)
     */
    @PostMapping("/auth")
    public SuccessResponse authenticate(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request){
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_CONTROLLER_AUTHENTICATE,
                "", "LoginDto : " + loginDto.toString());

        return authenticationService.authenticate(uuid, loginDto.getUsername(),
                loginDto.getPassword(), request.getRequestURL().toString());
    }
}
