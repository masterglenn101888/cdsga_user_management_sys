package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_FAMILY_BACKGROUND")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyBackground {

    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private String id;

    @Column(name = "F_FIRST_NAME")
    private String fathersFirstName;

    @Column(name = "F_MIIDLE_NAME")
    private String fathersMiddleName;

    @Column(name = "F_LAST_NAME")
    private String fathersLastName;

    @Column(name = "F_MIIDLE_INITIAL")
    private String fathersMiddleInitial;

    @Column(name = "F_EXT")
    private String ext;

    @Column(name = "F_DOB")
    private String fathersDateOfBirth;

    @Column(name = "F_OCCUPATION")
    private String fathersOccupation;

    @Column(name = "F_MONTHLY_INCOME")
    private String fathersMonthlyIncome;

    @Column(name = "F_YEARLY_COMPENSATION")
    private String fathersYearlyCompensation;

    @Column(name = "F_CONTACT_NO")
    private String fathersContactNo;

    @Column(name = "F_EDUC_ATTAINMENT")
    private String fathersEducationalAttainment;

    @Column(name = "M_FIRST_NAME")
    private String mothersFirstName;

    @Column(name = "M_MIIDLE_NAME")
    private String mothersMiddleName;

    @Column(name = "M_MAIDEN_NAME")
    private String mothersMaidenName;

    @Column(name = "M_MIIDLE_INITIAL")
    private String mothersMiddleInitial;

    @Column(name = "M_DOB")
    private String mothersDateOfBirth;

    @Column(name = "M_OCCUPATION")
    private String mothersOccupation;

    @Column(name = "M_MONTHLY_INCOME")
    private String mothersMonthlyIncome;

    @Column(name = "M_YEARLY_COMPENSATION")
    private String mothersYearlyCompensation;

    @Column(name = "M_CONTACT_NO")
    private String mothersContactNo;

    @Column(name = "M_EDUC_ATTAINMENT")
    private String mothersEducationalAttainment;

    @Column(name = "PARENT_MARITAL_STATUS")
    private String parentMaritalStatus;

    @Column(name = "G_FIRST_NAME")
    private String guardiansFirstName;

    @Column(name = "G_MIIDLE_NAME")
    private String guardiansMiddleName;

    @Column(name = "G_LAST_NAME")
    private String guardiansLastName;

    @Column(name = "HOME_ADDRESS")
    private String homeAddress;

    @Column(name = "BRGY")
    private String brgy;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "SOURCE_OF_REVENUE")
    private String sourceOfRevenue;

    @Column(name = "NATURE_OF_BUSINESS")
    private String natureOfBusiness;

    @Column(name = "MONTHLY_INCOME")
    private String monthlyIncome;

    @Column(name = "M_YEARLY_INCOME")
    private String mothersYearlyIncome;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "CP_ADDRESS")
    private String cpAddress;

    @Column(name = "CP_MOBILE_NO")
    private String cpMobileNo;

    @Column(name = "CP_TEL_NO")
    private String cpTelephoneNo;

    @Column(name = "CP_EMAIL")
    private String cpEmail;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PI_FB_ID", nullable=false)
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
