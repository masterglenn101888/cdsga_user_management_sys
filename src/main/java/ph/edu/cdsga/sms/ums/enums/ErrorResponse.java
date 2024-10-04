package ph.edu.cdsga.sms.ums.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorResponse {
    SUCCESS(String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase()),
    BAD_REQUEST(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    FORBIDDEN(String.valueOf(HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.getReasonPhrase()),
    NOT_FOUND(String.valueOf(HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND.getReasonPhrase()),
    INTERNAL_SERVER_ERROR(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    private final String code;
    private final String message;

    public static String getMessageByCode(String code) {

        for (ErrorResponse type : ErrorResponse.values()) {
            if (type.getCode().equalsIgnoreCase(code)) {
                return type.getMessage();
            }
        }
        return code;
    }

}
