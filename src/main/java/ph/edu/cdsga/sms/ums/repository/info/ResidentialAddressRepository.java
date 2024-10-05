package ph.edu.cdsga.sms.ums.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.edu.cdsga.sms.ums.entity.info.ResidentialAddress;

public interface ResidentialAddressRepository extends JpaRepository<ResidentialAddress, String> {
}
