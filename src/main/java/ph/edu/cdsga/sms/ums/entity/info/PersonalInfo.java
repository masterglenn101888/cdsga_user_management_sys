package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SMS_PERSONAL_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIIDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIIDLE_INITIAL")
    private String middleInitial;

    @Column(name = "EXT")
    private String ext;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "DOB")
    private String dateOfBirth;

    @Column(name = "POB")
    private String placeOfBirth;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Column(name = "RELIGION")
    private String religion;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "HEIGHT")
    private String height;

    @Column(name = "WEIGHT")
    private String weight;

    @Column(name = "BLOOD_TYPE")
    private String bloodType;

    @Column(name = "ETHNICITY")
    private String ethnicity;

    @Column(name = "VACCINATION_STATUS")
    private String vaccinationStatus;

    @Column(name = "IS_PWD")
    private String isPwd;

    @Column(name = "STUDENT_ID_PATH")
    private String studentIdPath;

    @Column(name = "SIGNATURE_PATH")
    private String signaturePath;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ResidentialAddress> residentialAddress;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<PermanentAddress> permanentAddress;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EmergencyInfo> emergencyInfo;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<SiblingInfo> siblingInfo;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<EducationalBackground> educationalBackground;

    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<FamilyBackground> familyBackground;

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
