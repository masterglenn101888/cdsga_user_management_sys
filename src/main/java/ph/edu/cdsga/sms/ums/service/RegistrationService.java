package ph.edu.cdsga.sms.ums.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.common.prop.ApplicationProperties;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.account.UserProfileDto;
import ph.edu.cdsga.sms.ums.entity.account.UserProfile;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.enums.UserRoles;
import ph.edu.cdsga.sms.ums.enums.UserStatus;
import ph.edu.cdsga.sms.ums.exception.ServiceException;
import ph.edu.cdsga.sms.ums.repository.account.UserProfileRepository;
import ph.edu.cdsga.sms.ums.repository.account.UserRoleRepository;
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
    private final UserProfileRepository userProfileRepository;
    private final UserRoleRepository userRoleRepository;

    public RegistrationService(ApplicationProperties applicationProperties,
                               LoggingService loggingService,
                               BCryptPasswordGenerator encoder,
                               UserProfileRepository userProfileRepository,
                               UserRoleRepository userRoleRepository) {
        this.applicationProperties = applicationProperties;
        this.loggingService = loggingService;
        this.encoder = encoder;
        this.userProfileRepository = userProfileRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public SuccessResponse registerAccount(String uuid, UserProfileDto userProfileDto){
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_SERVICE_AUTHENTICATE,
                "", "UserProfileDto : " + userProfileDto);

        // Check username if existing
        UserProfile userProfileUsername = userProfileRepository.findByUsername(userProfileDto.getUsername());
        if(!Objects.isNull(userProfileUsername)){
            throw new ServiceException(String.format(CommonStringUtility.ERR_CODE_001_USERNAME_TAKEN, userProfileDto.getUsername()), 403);
        }

        // Check email if existing
        UserProfile userProfileEmail = userProfileRepository.findByEmail(userProfileDto.getEmail());
        if(!Objects.isNull(userProfileEmail)){
            throw new ServiceException(String.format(CommonStringUtility.ERR_CODE_001_EMAIL_TAKEN, userProfileDto.getEmail()), 403);
        }

        UserProfile userProfile = this.populateUserProfile();
        ObjectUtils.copyProperties(userProfileDto, userProfile);
        userProfile.setPassword(encoder.passwordEncoder(userProfileDto.getPassword()));

        UserRole role = this.populateUserRole(userProfile, userProfileDto.getGroup());
        userRoleRepository.save(role);

        return new SuccessResponse(userProfile, CommonStringUtility.SUCCESS_MSG_REGISTRATION,
                applicationProperties.getAppName(), applicationProperties.getAppVersion());
    }

    private UserProfile populateUserProfile(){
        return UserProfile.builder()
                .status(UserStatus.INACTIVE.toString())
                .isActive(false)
                .loginAttempts(0)
                .build();
    }

    private UserRole populateUserRole(UserProfile userProfile, String group){
        return UserRole.builder()
                .userRole(UserRoles.getRole(group))
                .userRoleId(UserRoles.getRoleId(group))
                .userProfile(userProfile)
                .build();
    }

}
