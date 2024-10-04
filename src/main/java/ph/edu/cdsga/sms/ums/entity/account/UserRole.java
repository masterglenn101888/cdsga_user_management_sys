package ph.edu.cdsga.sms.ums.entity.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_USER_ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(generator="IdOrGenerated")
    @GenericGenerator(name="IdOrGenerated", strategy="ph.edu.cdsga.sms.ums.utils.object.UserRoleIDGeneratorUtil")
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "ROLE_ID")
    private String userRoleId;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "CREATION_DATE")
    private String creationDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="USER_ROLE_ID")
    private SmsUser smsUser;

    @PrePersist
    public void onPrePersist() {
        String date = ObjectUtils.getCurrentDateAndTime();
        this.setCreatedBy("System");
        this.setCreationDate(date);
    }

    @PreUpdate
    public void onPreUpdate() {
        String date = ObjectUtils.getCurrentDateAndTime();
        this.setLastModifiedBy("System");
        this.setLastModificationDate(date);
    }

}