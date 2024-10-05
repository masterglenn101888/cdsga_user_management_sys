package ph.edu.cdsga.sms.ums.entity.account;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SMS_USER_PROFILE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIIDLE_INTIAL")
    private String middleInitial;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SMS_GROUP")
    private String group;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "LAST_PASSWORD")
    private String lastPassword;

    @Column(name = "LOGIN_ATTEMPTS")
    private int loginAttempts;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "IS_LOCKED")
    private boolean isLocked;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserToken> userToken;

    @PrePersist
    public void onPrePersist() {
        Date date = ObjectUtils.getLocalDateTime();
        this.setDateCreated(date);
        this.setCreatedBy("System");
    }

    @PreUpdate
    public void onPreUpdate() {
        Date date = ObjectUtils.getLocalDateTime();
        this.setDateModified(date);
        this.setModifiedBy("System");
    }
}
