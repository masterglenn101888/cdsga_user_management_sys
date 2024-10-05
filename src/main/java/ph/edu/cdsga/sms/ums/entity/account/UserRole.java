package ph.edu.cdsga.sms.ums.entity.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SMS_USER_ROLE")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ROLE_ID")
    private String userRoleId;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "DATE_CREATED")
    private Date dateCreated;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "DATE_MODIFIED")
    private Date dateModified;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_PROFILE_ROLE_ID")
    private UserProfile userProfile;

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