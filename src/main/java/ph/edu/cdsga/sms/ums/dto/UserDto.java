package ph.edu.cdsga.sms.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "MailRequest")
public class UserDto {

    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String userRole;
    private String status;
    private String designation;
    private String division;
    private String creationDateStr;
    private Date creationDate;
    private String createdBy;
    private Date lastModificationDate;
    private String lastModifiedBy;
}
