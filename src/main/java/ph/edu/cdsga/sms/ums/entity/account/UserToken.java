package ph.edu.cdsga.sms.ums.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "SMS_USER_TOKEN")
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Long tokenId;

    @Column(name = "ACCESS_TOKEN", length = 1024)
    private String accessToken;

    @Column(name = "EXPIRES_IN")
    private String expiresIn;

    @Column(name = "REFRESH_EXPIRES_IN")
    private String refreshExpiresIn;

    @Column(name = "TOKEN_TYPE")
    private String tokenType;

    @Column(name = "NOT_BEFORE_POLICY")
    private String notBeforePolicy;

    @Column(name = "SCOPE")
    private String scope;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_PROFILE_TOKEN_ID", nullable=false)
    private UserProfile userProfile;
}
