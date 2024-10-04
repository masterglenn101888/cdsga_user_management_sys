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
@Schema(name = "Login")
public class LoginDto {

    @Schema(example = "2024100000")
    private String username;

    @Schema(example = "P@$$w0rd1234")
    private String password;

}
