package ph.edu.cdsga.sms.ums.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.common.prop.ApplicationProperties;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.entity.account.SmsUser;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.entity.account.UserToken;
import ph.edu.cdsga.sms.ums.enums.UserStatus;
import ph.edu.cdsga.sms.ums.exception.AuthenticationException;
import ph.edu.cdsga.sms.ums.repository.UserRepository;
import ph.edu.cdsga.sms.ums.repository.UserTokenRepository;
import ph.edu.cdsga.sms.ums.utils.bcrypt.BCryptPasswordGenerator;
import ph.edu.cdsga.sms.ums.utils.jwt.JwtUtility;
import ph.edu.cdsga.sms.ums.utils.string.CommonStringUtility;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import java.util.*;

@Service
@Transactional
public class AuthenticationService {


    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final LoggingService loggingService;
    private final BCryptPasswordGenerator encoder;
    private final ApplicationProperties applicationProperties;

    public AuthenticationService(UserRepository userRepository,
                                 UserTokenRepository userTokenRepository,
                                 LoggingService loggingService,
                                 BCryptPasswordGenerator encoder,
                                 ApplicationProperties applicationProperties) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.loggingService = loggingService;
        this.encoder = encoder;
        this.applicationProperties = applicationProperties;
    }

    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public SuccessResponse authenticate(String uuid, String username, String password, String url){
        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_SERVICE_AUTHENTICATE,
                "", "Username : " + username + " | Password : " + password);

        SmsUser smsUser = userRepository.findUserByUsername(username);
        if(!Objects.isNull(smsUser)){

            boolean isLocked = smsUser.isLocked();
            if(isLocked){
                throw new AuthenticationException(CommonStringUtility.ERR_CODE_INACTIVE_SUSPENDED, 403);
            }

            if(smsUser.getLoginAttempts() >= MAX_LOGIN_ATTEMPTS){
                userRepository.updateLockedAndStatus(true, UserStatus.INACTIVE.toString(), smsUser.getUsername());
                throw new AuthenticationException(CommonStringUtility.ERR_CODE_001_TOO_MANY_LOGIN_ATTEMPTS, 403);
            } else{
                if(encoder.passwordMatches(password, smsUser.getPassword())){
                    List<String> rolesList = new ArrayList<>();
                    for(UserRole userRole : smsUser.getUserRoles()){
                        rolesList.add(userRole.getUserRole());
                    }

                    String accessToken = JwtUtility.generateToken(smsUser, rolesList, url);
                    UserToken token = JwtUtility.populateUserToken(smsUser, accessToken, "login");
                    userTokenRepository.saveAndFlush(token);

                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("access_token", accessToken);

                    userRepository.updateLoginAttempt(0, smsUser.getUsername());

                    return new SuccessResponse(tokenMap, CommonStringUtility.SUCCESS_MSG_LOGGED_IN,
                            applicationProperties.getAppName(), applicationProperties.getAppVersion());
                }else{
                    int attemptCount = smsUser.getLoginAttempts() + 1;
                    userRepository.updateLoginAttempt(attemptCount, smsUser.getUsername());
                    throw new AuthenticationException(CommonStringUtility.ERR_CODE_LOGIN_INCORRECT_PASSWORD, 403);
                }
            }
        }else{
            throw new AuthenticationException(CommonStringUtility.ERR_CODE_LOGIN_USERNAME_NOT_CONNECTED, 403);
        }
    }
}
