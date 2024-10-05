package ph.edu.cdsga.sms.ums.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.account.UserProfileDto;
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
     * @param userProfileDto Registration data transfer object
     * @return SuccessResponse
     * @see #registerAccount(UserProfileDto)
     */
    @PostMapping("/register-account")
    public SuccessResponse registerAccount(@Valid @RequestBody UserProfileDto userProfileDto){
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_REGISTRATION_CONTROLLER_REGISTER_ACCOUNT,
                "", "UserProfileDto : " + userProfileDto.toString());
        return registrationService.registerAccount(uuid, userProfileDto);
    }

}
