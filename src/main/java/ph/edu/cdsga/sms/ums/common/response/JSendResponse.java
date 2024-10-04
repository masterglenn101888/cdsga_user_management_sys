package ph.edu.cdsga.sms.ums.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class JSendResponse {

    private String programName;
    private String version;
    private Date datetime;
    private String status;
    private int code;
    private String message;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mfaToken;

}

