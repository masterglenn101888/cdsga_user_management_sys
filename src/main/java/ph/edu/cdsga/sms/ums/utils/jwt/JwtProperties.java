package ph.edu.cdsga.sms.ums.utils.jwt;

public class JwtProperties {

    JwtProperties() {
        throw new IllegalStateException("Utility class");
    }

    public static final String JWT_SECRET = "DtsSecretKey";
    public static final String JWT_BEARER = "Bearer";
    public static final long EXPIRATION_TIME =  86400000;
    public static final String HEADER_STRING = "Authorization";
}
