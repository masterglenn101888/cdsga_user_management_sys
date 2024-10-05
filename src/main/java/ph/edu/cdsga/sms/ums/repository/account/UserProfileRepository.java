package ph.edu.cdsga.sms.ums.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.entity.account.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    @Query(value = "SELECT u FROM UserProfile u WHERE u.email= :email")
    UserProfile findByEmail(@Param("email") String email);

    @Query(value = "SELECT u FROM UserProfile u WHERE u.username= :username")
    UserProfile findByUsername(@Param("username") String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE UserProfile u SET u.loginAttempts= :loginAttempts WHERE u.username = :username")
    void updateLoginAttempt(@Param("loginAttempts") int loginAttempts, @Param("username") String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE UserProfile u SET u.isLocked = :isLocked, u.status = :status WHERE u.username = :username")
    void updateLockedAndStatus(@Param("isLocked") boolean isLocked,
                               @Param("status") String status,
                               @Param("username") String username);

    @Query(value = "SELECT u FROM UserProfile u  WHERE u.username = :userId")
    UserProfile findByStudentId(@Param("userId") String userId);
}
