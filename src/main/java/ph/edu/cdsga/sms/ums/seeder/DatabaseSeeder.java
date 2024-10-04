package ph.edu.cdsga.sms.ums.seeder;

import com.fasterxml.uuid.Generators;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ph.edu.cdsga.sms.ums.entity.account.SmsUser;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;
import ph.edu.cdsga.sms.ums.enums.UserRoles;
import ph.edu.cdsga.sms.ums.repository.UserRepository;
import ph.edu.cdsga.sms.ums.repository.UserRoleRepository;
import ph.edu.cdsga.sms.ums.utils.bcrypt.BCryptPasswordGenerator;
import ph.edu.cdsga.sms.ums.utils.string.CommonStringUtility;

import java.util.List;
import java.util.UUID;

/**
 * This class is a Database Seeder for TUP - Online Document Tracking System web app
 */

@Component
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordGenerator encoder;

    public DatabaseSeeder(UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordGenerator encoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.encoder = encoder;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
    }

    private void seedUser(){
        List<SmsUser> dtsUserList = userRepository.findAll();
        if(dtsUserList.isEmpty()){

            SmsUser dtsSuperUser = new SmsUser(
                    null,
                    "Heero",
                    "A",
                    "Yuy",
                    "heero.yuy@gmail.com",
                    UserRoles.ROLE_SUPER_USER.getStrRole(),
                    "2024100000",
                    "BACHELOR OF SCIENCE IN COMPUTER ENGINEERING",
                    UserRoles.ROLE_SUPER_USER.getStrRole(),
                    "2024100000",
                    encoder.passwordEncoder("P@$$w0rd1234"),
                    null,
                    0,
                    "ACTIVE",
                    true,
                    false,
                    null,
                    null,
                    "",
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
                    dtsSuperUser);
            userRoleRepository.save(superUserRole);

            SmsUser dtsAdminUser = new SmsUser(
                    null,
                    "Glenn Mark",
                    "Trampe",
                    "Anduiza",
                    "heero.yuy@gmail.com",
                    UserRoles.ROLE_STUDENT.getStrRole(),
                    "2024100001",
                    "BACHELOR OF SCIENCE IN COMPUTER ENGINEERING",
                    UserRoles.ROLE_STUDENT.getStrRole(),
                    "2024100001",
                    encoder.passwordEncoder("P@$$w0rd1234"),
                    null,
                    0,
                    "ACTIVE",
                    true,
                    false,
                    null,
                    null,
                    "",
                    "",
                    null,
                    null
            );

            UserRole adminUserRole = new UserRole(
                    null,
                    "RS2",
                    UserRoles.ROLE_ADMIN.getStrRole(),
                    null,
                    null,
                    null,
                    null,
                    dtsAdminUser);
            userRoleRepository.save(adminUserRole);
        }
    }


    /** This method is used to generate unique notification ID
     * @return userID user ID
     * @see #generateDtsUserId()
     */
    private String generateDtsUserId(){
        UUID uuid = Generators.timeBasedGenerator().generate();
        return String.format(CommonStringUtility.CDSGA_SMS_ID, uuid.toString().substring(0, 7).toUpperCase());
    }
}
