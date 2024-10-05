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
public class ResidentialAddressDto {

    @Schema(example = "Blk 2 Lot 12 UC2")
    private String homeAddress;

    @Schema(example = "Citrus")
    private String brgy;

    @Schema(example = "San Jose Del Monte")
    private String municipality;

    @Schema(example = "Bulacan")
    private String province;

    @Schema(example = "3023")
    private String zipCode;
}
