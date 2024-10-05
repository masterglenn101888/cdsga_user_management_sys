package ph.edu.cdsga.sms.ums.dto.info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SiblingInfoDto {

    @Schema(example = "Ashley A. Anduiza")
    private String fullName;

    @Schema(example = "12/20/2022")
    private String dob;

    @Schema(example = "2")
    private String age;

    @Schema(example = "Female")
    private String gender;

    @Schema(example = "12")
    private String gradeLevel;

    @Schema(example = "06/20/2024")
    private String schoolAttended;
}
