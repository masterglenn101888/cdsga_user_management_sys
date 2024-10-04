package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_EMERGENCY_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyInfo {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE_NO")
    private String mobileNo;

    @Column(name = "TEL_NO")
    private String telephoneNo;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PI_EI_ID", nullable=false)
    private PersonalInfo personalInfo;

    @PrePersist
    public void onPrePersist() {
        String date = ObjectUtils.getCurrentDateAndTime();
        this.setCreationDate(date);
    }

    @PreUpdate
    public void onPreUpdate() {
        String date = ObjectUtils.getCurrentDateAndTime();
        this.setLastModificationDate(date);
    }
}
