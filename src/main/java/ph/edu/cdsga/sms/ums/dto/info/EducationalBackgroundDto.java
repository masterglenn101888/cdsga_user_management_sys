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
public class EducationalBackgroundDto {

    @Schema(example = "2024-10-00-00")
    private String lrl;

    @Schema(example = "NO")
    private String isSped;

    @Schema(example = "NO")
    private String isPwd;

    @Schema(example = "Undergraduate/College/ALS")
    private String level;

    @Schema(example = "Yes")
    private String isPrivateSchool;

    @Schema(example = "Harvard University")
    private String schoolName;

    @Schema(example = "Massachusetts Hall, Cambridge, MA")
    private String address;

    @Schema(example = "Bachelor of Science in Computer Engineering")
    private String program;

    @Schema(example = "05/20/2028")
    private String yearOfGraduation;

    @Schema(example = "1.00")
    private String gwa;

    @Schema(example = "Summa Cum Laude")
    private String awardHonor;
}
