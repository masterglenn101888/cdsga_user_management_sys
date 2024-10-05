package ph.edu.cdsga.sms.ums.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.common.prop.ApplicationProperties;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.entity.account.UserProfile;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.entity.account.UserToken;
import ph.edu.cdsga.sms.ums.enums.UserStatus;
import ph.edu.cdsga.sms.ums.exception.AuthenticationException;
import ph.edu.cdsga.sms.ums.repository.account.UserProfileRepository;
import ph.edu.cdsga.sms.ums.repository.account.UserTokenRepository;
import ph.edu.cdsga.sms.ums.utils.bcrypt.BCryptPasswordGenerator;
import ph.edu.cdsga.sms.ums.utils.jwt.JwtUtility;
import ph.edu.cdsga.sms.ums.utils.string.CommonStringUtility;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import java.util.*;

@Service
@Transactional
public class AuthenticationService {


    private final UserProfileRepository userProfileRepository;
    private final UserTokenRepository userTokenRepository;
    private final LoggingService loggingService;
    private final BCryptPasswordGenerator encoder;
    private final ApplicationProperties applicationProperties;

    public AuthenticationService(UserProfileRepository userProfileRepository,
                                 UserTokenRepository userTokenRepository,
                                 LoggingService loggingService,
                                 BCryptPasswordGenerator encoder,
                                 ApplicationProperties applicationProperties) {
        this.userProfileRepository = userProfileRepository;
        this.userTokenRepository = userTokenRepository;
        this.loggingService = loggingService;
        this.encoder = encoder;
        this.applicationProperties = applicationProperties;
    }

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public SuccessResponse authenticate(String uuid, String username, String password, String url){
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_SERVICE_AUTHENTICATE,
                "", "Username : " + username + " | Password : " + password);

        UserProfile userProfile = userProfileRepository.findByUsername(username);
        if(!Objects.isNull(userProfile)){

            boolean isLocked = userProfile.isLocked();
            if(isLocked){
                throw new AuthenticationException(CommonStringUtility.ERR_CODE_INACTIVE_SUSPENDED, 403);
            }

            if(userProfile.getLoginAttempts() >= MAX_LOGIN_ATTEMPTS){
                userProfileRepository.updateLockedAndStatus(true, UserStatus.INACTIVE.toString(), userProfile.getUsername());
                throw new AuthenticationException(CommonStringUtility.ERR_CODE_001_TOO_MANY_LOGIN_ATTEMPTS, 403);
            } else{
                if(encoder.passwordMatches(password, userProfile.getPassword())){
                    List<String> rolesList = new ArrayList<>();
                    for(UserRole userRole : userProfile.getUserRoles()){
                        rolesList.add(userRole.getUserRole());
                    }

                    String accessToken = JwtUtility.generateToken(userProfile, rolesList, url);
                    UserToken token = JwtUtility.populateUserToken(userProfile, accessToken, "login");
                    userTokenRepository.saveAndFlush(token);

                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("access_token", accessToken);

                    userProfileRepository.updateLoginAttempt(0, userProfile.getUsername());

                    return new SuccessResponse(tokenMap, CommonStringUtility.SUCCESS_MSG_LOGGED_IN,
                            applicationProperties.getAppName(), applicationProperties.getAppVersion());
                }else{
                    int attemptCount = userProfile.getLoginAttempts() + 1;
                    userProfileRepository.updateLoginAttempt(attemptCount, userProfile.getUsername());
                    throw new AuthenticationException(CommonStringUtility.ERR_CODE_LOGIN_INCORRECT_PASSWORD, 403);
                }
            }
        }else{
            throw new AuthenticationException(CommonStringUtility.ERR_CODE_LOGIN_USERNAME_NOT_CONNECTED, 403);
        }
    }
}
