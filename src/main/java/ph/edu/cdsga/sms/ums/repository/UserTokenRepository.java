package ph.edu.cdsga.sms.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ph.edu.cdsga.sms.ums.entity.account.UserToken;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    @Query(value = "SELECT * FROM SMS_USER_TOKEN WHERE TOKEN_ID = :dtsUserId " +
            "AND TOKEN_ID = (SELECT MAX(TOKEN_ID) FROM SMS_USER_TOKEN WHERE TOKEN_ID = :dtsUserId)", nativeQuery = true)
    UserToken findLatestTokenByUserId(@Param("dtsUserId")String dtsUserId);
}
