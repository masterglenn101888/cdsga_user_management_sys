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
public class EmergencyInfoDto {

    @Schema(example = "Glenn Mark Anduiza")
    private String contactPerson;

    @Schema(example = "Blk 2 Lot 12 UC2 Brgy Citrus, City of San Jose Del Monte, Bulacan")
    private String address;

    @Schema(example = "09106121529")
    private String mobileNo;

    @Schema(example = "+639106121529")
    private String telephoneNo;

}
