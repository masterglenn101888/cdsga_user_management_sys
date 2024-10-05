package ph.edu.cdsga.sms.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "MailRequest")
public class SmsUserDto {

    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String studentIdNo;
    private String academicProgram;
    private String userRole;
    private String username;
    private String password;
    private String lastPassword;
    private int loginAttempts;
    private String status;
    private boolean isActive;
    private boolean isLocked;
}
