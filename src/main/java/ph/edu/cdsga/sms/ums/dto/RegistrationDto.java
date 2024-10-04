package ph.edu.cdsga.sms.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(name = "Register Account")
public class RegistrationDto {

    @Schema(example = "Student")
    private String userRole;

    @Schema(example = "Heero")
    private String lastName;

    @Schema(example = "Yuy")
    private String firstName;

    @Schema(example = "masterglenn101888@gmail.com")
    private String email;

    @Schema(example = "2024100000")
    private String username;

    @Schema(example = "P@$$w0rd1234")
    private String password;
}
