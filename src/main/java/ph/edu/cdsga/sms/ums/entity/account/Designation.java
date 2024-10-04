package ph.edu.cdsga.sms.ums.entity.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;

import javax.persistence.*;

@Entity
@Table(name = "SMS_DESIGNATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Designation {

    @Id
    @Column(name = "DESIGNATION_ID", length = 10, nullable = false)
    private String designationId;

    @Column(name = "DESIGNATION", length = 150, nullable = false)
    private String designation;

    @Column(name = "DESCRIPTION", length = 150, nullable = false)
    private String description;

    @Column(name = "CREATION_DATE", nullable = false)
    private String creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @PrePersist
    public void onPrePersist() {
        String date = ObjectUtils.getCurrentDateAndTime();
        this.setCreationDate(date);
    }
}
