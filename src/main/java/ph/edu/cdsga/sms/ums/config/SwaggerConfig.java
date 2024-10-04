package ph.edu.cdsga.sms.ums.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Value("${product.version}")
  private String productVersion;

  @Bean
  public OpenAPI userManagementSystemOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("CDSGA-SMS - User Management System API v1.0")
                    .description("CDSGA-SMS - User Management System maintenance/admin")
                    .version(productVersion)
            );
  }
}