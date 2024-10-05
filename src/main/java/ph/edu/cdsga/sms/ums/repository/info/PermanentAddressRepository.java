package ph.edu.cdsga.sms.ums.repository.info;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.edu.cdsga.sms.ums.entity.info.PermanentAddress;

public interface PermanentAddressRepository extends JpaRepository<PermanentAddress, String> {
}
