package ph.edu.cdsga.sms.ums.common.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperties {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

}