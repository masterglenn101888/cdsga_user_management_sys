package ph.edu.cdsga.sms.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ph.edu.cdsga.sms.ums.entity.account.SmsUser;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<SmsUser, String> {

    @Query(value = "SELECT u FROM SmsUser u WHERE u.studentIdNo= :division")
    SmsUser findUserByIdNo(@Param("division") String division);

    @Query(value = "SELECT u FROM SmsUser u WHERE u.email= :email")
    SmsUser findUserByEmail(@Param("email") String email);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE SmsUser u SET u.loginAttempts= :loginAttempts WHERE u.username = :username")
    void updateLoginAttempt(@Param("loginAttempts") int loginAttempts, @Param("username") String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE SmsUser u SET u.isLocked = :isLocked, u.status = :status WHERE u.username = :username")
    void updateLockedAndStatus(@Param("isLocked") boolean isLocked,
                               @Param("status") String status,
                               @Param("username") String username);

    @Query(value = "SELECT * FROM SMS_USER WHERE DIVISION= :division", nativeQuery = true)
    List<SmsUser> findUsersByDepartment(@Param("division") String division);

    @Query(value = "SELECT * FROM SMS_USER WHERE USERNAME = :username", nativeQuery = true)
    SmsUser findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM SMS_USER WHERE USER_ID = :userId", nativeQuery = true)
    SmsUser findUserByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM DTS_USER WHERE STATUS = :status", nativeQuery = true)
    SmsUser findUserByUserStatus(@Param("status") String status);

//    @Query(value = "SELECT d FROM SmsUser d WHERE d.username LIKE %:username%")
//    List<SmsUser> findUserRecordByUsername(@Param("username") String username);

//    @Query(value = "SELECT d FROM SmsUser d WHERE d.username LIKE %:username% OR d.firstName LIKE %:firstName% " +
//            "OR d.designation LIKE %:designation% OR d.status LIKE %:status% OR d.division LIKE %:division%")
//    List<SmsUser> searchUserAccount(@Param("username") String username,
//                                    @Param("firstName") String firstName,
//                                    @Param("designation") String designation,
//                                    @Param("status") String status,
//                                    @Param("division") String division);


}
