package ph.edu.cdsga.sms.ums.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.SmsUserDto;
import ph.edu.cdsga.sms.ums.dto.info.PersonalInfoDto;
import ph.edu.cdsga.sms.ums.service.AccountService;
import ph.edu.cdsga.sms.ums.service.GenerateUUIDService;
import ph.edu.cdsga.sms.ums.service.LoggingService;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import javax.validation.Valid;

@RestController
@RequestMapping("${endpoint.url}")
@Tag(name = "Account Endpoints")
public class AccountController {

    private final GenerateUUIDService generateUUIDService;
    private final LoggingService loggingService;
    private final AccountService accountService;

    public AccountController(GenerateUUIDService generateUUIDService,
                             LoggingService loggingService,
                             AccountService accountService) {
        this.generateUUIDService = generateUUIDService;
        this.loggingService = loggingService;
        this.accountService = accountService;
    }

    /** This endpoint accepts and processes the incoming request
     * from user to activate the account.
     *
     * @param userDto SMS User data transfer object
     * @return SuccessResponse
     * @see #activateAccount(SmsUserDto)
     */
    @PostMapping("/activate-account")
    public SuccessResponse activateAccount(@Valid @RequestBody SmsUserDto userDto){
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_REGISTRATION_CONTROLLER_ACTIVATE_ACCOUNT,
                "", "SmsUserDto : " + userDto.toString());
        return accountService.activateAccount(uuid, userDto);
    }

    /** This endpoint accepts and processes the incoming request from user
     * to register the student information.
     *
     * @param personalInfoDto Student Information data transfer object
     * @return SuccessResponse
     * @see #updateAccount(PersonalInfoDto)
     */
    @PostMapping("/update-account")
    public SuccessResponse updateAccount(@Valid @RequestBody PersonalInfoDto personalInfoDto) {
        String uuid = generateUUIDService.generateUUID();
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_REGISTRATION_CONTROLLER_ACTIVATE_ACCOUNT,
                "", "PersonalInfoDto : " + personalInfoDto.toString());
        return accountService.updateAccount(uuid, personalInfoDto);
    }
}
