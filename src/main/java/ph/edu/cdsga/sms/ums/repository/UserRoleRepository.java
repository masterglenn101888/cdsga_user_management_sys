package ph.edu.cdsga.sms.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ph.edu.cdsga.sms.ums.entity.account.UserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    @Query(value = "SELECT * FROM SMS_USER_ROLE WHERE ROLE_ID = :userRoleId", nativeQuery = true)
    UserRole findUserRoleByUserId(@Param("userRoleId") String userRoleId);
}
