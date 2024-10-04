package ph.edu.cdsga.sms.ums.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.common.prop.ApplicationProperties;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.RegistrationDto;
import ph.edu.cdsga.sms.ums.dto.UserDto;
import ph.edu.cdsga.sms.ums.entity.account.SmsUser;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.enums.UserRoles;
import ph.edu.cdsga.sms.ums.enums.UserStatus;
import ph.edu.cdsga.sms.ums.exception.ServiceException;
import ph.edu.cdsga.sms.ums.repository.UserRepository;
import ph.edu.cdsga.sms.ums.repository.UserRoleRepository;
import ph.edu.cdsga.sms.ums.utils.bcrypt.BCryptPasswordGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;
import ph.edu.cdsga.sms.ums.utils.string.CommonStringUtility;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import java.util.Objects;

@Service
@Transactional
public class RegistrationService {

    private final ApplicationProperties applicationProperties;
    private final LoggingService loggingService;
    private final BCryptPasswordGenerator encoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public RegistrationService(ApplicationProperties applicationProperties,
                               LoggingService loggingService,
                               BCryptPasswordGenerator encoder,
                               UserRepository userRepository,
                               UserRoleRepository userRoleRepository) {
        this.applicationProperties = applicationProperties;
        this.loggingService = loggingService;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public SuccessResponse registerAccount(String uuid, RegistrationDto registrationDto){
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_SERVICE_AUTHENTICATE,
                "", "RegistrationDto : " + registrationDto);

        // Check student ID if existing
        SmsUser smsUser1 = userRepository.findUserByIdNo(registrationDto.getUsername());
        if(!Objects.isNull(smsUser1)){
            throw new ServiceException(String.format(CommonStringUtility.ERR_CODE_001_USERNAME_TAKEN, registrationDto.getUsername()), 403);
        }

        // Check email if existing
        SmsUser smsUser2 = userRepository.findUserByEmail(registrationDto.getEmail());
        if(!Objects.isNull(smsUser2)){
            throw new ServiceException(String.format(CommonStringUtility.ERR_CODE_001_EMAIL_TAKEN, registrationDto.getEmail()), 403);
        }

        SmsUser user = new SmsUser();
        user.setStudentIdNo(registrationDto.getUsername());
        user.setStatus(UserStatus.INACTIVE.toString());
        user.setActive(false);
        user.setLoginAttempts(0);
        ObjectUtils.copyProperties(registrationDto, user);
        user.setPassword(encoder.passwordEncoder(registrationDto.getPassword()));

        UserRole role = new UserRole();
        role.setUserRole(UserRoles.getRole(registrationDto.getUserRole()));
        role.setUserRoleId(UserRoles.getRoleId(registrationDto.getUserRole()));
        role.setSmsUser(user);
        userRoleRepository.save(role);

        return new SuccessResponse(user, CommonStringUtility.SUCCESS_MSG_REGISTRATION,
                applicationProperties.getAppName(), applicationProperties.getAppVersion());
    }

    public SuccessResponse activateAccount(String uuid, UserDto userDto){

        return new SuccessResponse("", CommonStringUtility.SUCCESS_MSG_REGISTRATION,
                applicationProperties.getAppName(), applicationProperties.getAppVersion());
    }

}
