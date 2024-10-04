package ph.edu.cdsga.sms.ums.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("success"),
    ERROR("error"),
    FAIL("fail"),
    ACCEPTED("accepted");

    private final String status;

}
