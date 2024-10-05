package ph.edu.cdsga.sms.ums.seeder;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ph.edu.cdsga.sms.ums.entity.account.UserProfile;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.enums.UserRoles;
import ph.edu.cdsga.sms.ums.enums.UserStatus;
import ph.edu.cdsga.sms.ums.repository.account.UserProfileRepository;
import ph.edu.cdsga.sms.ums.repository.account.UserRoleRepository;
import ph.edu.cdsga.sms.ums.utils.bcrypt.BCryptPasswordGenerator;

import java.util.List;

/**
 * This class is a Database Seeder for TUP - Online Document Tracking System web app
 */

@Component
public class DatabaseSeeder {

    private final UserProfileRepository userProfileRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordGenerator encoder;

    public DatabaseSeeder(UserProfileRepository userProfileRepository,
                          UserRoleRepository userRoleRepository,
                          BCryptPasswordGenerator encoder) {
        this.userProfileRepository = userProfileRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<UserProfile> userProfileList = userProfileRepository.findAll();
        if(userProfileList.isEmpty()){

            UserProfile userProfile = new UserProfile(
                    null,
                    "Heero",
                    "A.",
                    "Yuy",
                    "heero.yuy@gmail.com",
                    UserRoles.ROLE_SUPER_USER.getStrRole(),
                    "2024100000",
                    encoder.passwordEncoder("P@$$w0rd1234"),
                    null,
                    0,
                    UserStatus.ACTIVE.toString(),
                    true,
                    false,
                    null,
                    "",
                    null,
                    "",
                    null,
                    null
            );

            UserRole superUserRole = new UserRole(
                    null,
                    "RS1",
                    UserRoles.ROLE_SUPER_USER.getStrRole(),
                    null,
                    null,
                    null,
                    null,
                    userProfile);
            userRoleRepository.save(superUserRole);

        }
    }

}
