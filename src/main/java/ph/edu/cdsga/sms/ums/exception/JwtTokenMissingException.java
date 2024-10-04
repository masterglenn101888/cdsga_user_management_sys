package ph.edu.cdsga.sms.ums.exception;

public class JwtTokenMissingException extends RuntimeException{

    public JwtTokenMissingException(String exceptionStr) {
        super(exceptionStr);
    }
}
