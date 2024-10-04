package ph.edu.cdsga.sms.ums.utils.bcrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordGenerator {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String passwordEncoder(String rawPassword){
        return encoder.encode(rawPassword);
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword){
        return encoder.matches(rawPassword, encodedPassword);
    }
}
