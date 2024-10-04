package ph.edu.cdsga.sms.ums.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final int status;

    public AuthenticationException(String message) {
        super(message);
        this.status = 500;
    }

    public AuthenticationException(String message, int status) {
        super(message);
        this.status = status;
    }
}
