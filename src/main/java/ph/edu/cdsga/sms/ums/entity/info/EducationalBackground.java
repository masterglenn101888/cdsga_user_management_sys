package ph.edu.cdsga.sms.ums.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_EDUCATIONAL_BACKGROUND")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalBackground {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "LRN")
    private String lrl;

    @Column(name = "IS_SPED")
    private String isSped;

    @Column(name = "IS_PWD")
    private String isPwd;

    @Column(name = "LEVEL")
    private String level;

    @Column(name = "IS_PRIVATE")
    private String isPrivateSchool;

    @Column(name = "SCHOOL_NAME")
    private String schoolName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PROGRAM")
    private String program;

    @Column(name = "YEAR_OF_GRAD")
    private String yearOfGraduation;

    @Column(name = "GWA")
    private String gwa;

    @Column(name = "AWARD_HONOR")
    private String awardHonor;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private String lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PI_EB_ID", nullable=false)
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
