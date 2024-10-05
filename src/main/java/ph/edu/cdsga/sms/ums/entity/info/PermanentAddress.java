package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_PERMANENT_ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentAddress {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "HOME_ADDRESS")
    private String homeAddress;

    @Column(name = "BRGY")
    private String brgy;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PI_P_ADRRESS_ID", nullable=false)
    private PersonalInfo personalInfo;

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
