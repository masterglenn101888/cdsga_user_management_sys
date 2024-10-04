package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_SIBLING_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiblingInfo {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "AGE")
    private String age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "GRADE_LEVEL")
    private String gradeLevel;

    @Column(name = "SCHOOL_ATTENDED")
    private String schoolAttended;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PI_SI_ID", nullable=false)
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
