package ph.edu.cdsga.sms.ums.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.RegistrationDto;
import ph.edu.cdsga.sms.ums.dto.UserDto;
import ph.edu.cdsga.sms.ums.service.GenerateUUIDService;
import ph.edu.cdsga.sms.ums.service.LoggingService;
import ph.edu.cdsga.sms.ums.service.RegistrationService;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import javax.validation.Valid;

@RestController
@RequestMapping("${endpoint.url}")
@Tag(name = "Registration Endpoints")
public class RegistrationController {

    private final GenerateUUIDService generateUUIDService;
    private final RegistrationService registrationService;
    private final LoggingService loggingService;

    public RegistrationController(GenerateUUIDService generateUUIDService,
                                  RegistrationService registrationService,
                                  LoggingService loggingService) {
        this.generateUUIDService = generateUUIDService;
        this.registrationService = registrationService;
        this.loggingService = loggingService;
    }

    /** This endpoint accepts and processes the incoming request from user
     * to register the student information.
     *
     * @param registrationDto Registration data transfer object
     * @return SuccessResponse
     * @see #registerAccount(RegistrationDto)
     */
    @PostMapping("/register")
    public SuccessResponse registerAccount(@Valid @RequestBody RegistrationDto registrationDto){
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_REGISTRATION_CONTROLLER_REGISTER_ACCOUNT,
                "", "RegistrationDto : " + registrationDto.toString());
        return registrationService.registerAccount(uuid, registrationDto);
    }

    /** This endpoint accepts and processes the incoming request
     * from user to activate the account.
     *
     * @param userDto SMS User data transfer object
     * @return SuccessResponse
     * @see #activateAccount(UserDto)
     */
    @PostMapping("/activate-account")
    public SuccessResponse activateAccount(@Valid @RequestBody UserDto userDto){
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_REGISTRATION_CONTROLLER_ACTIVATE_ACCOUNT,
                "", "UserDto : " + userDto.toString());
        return registrationService.activateAccount(uuid, userDto);
    }
}
