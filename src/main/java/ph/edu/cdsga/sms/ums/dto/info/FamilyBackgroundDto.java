package ph.edu.cdsga.sms.ums.dto.info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FamilyBackgroundDto {

    @Schema(example = "Caspy")
    private String fathersFirstName;

    @Schema(example = "Ancantara")
    private String fathersMiddleName;

    @Schema(example = "Anduiza")
    private String fathersLastName;

    @Schema(example = "A.")
    private String fathersMiddleInitial;

    @Schema(example = "Sr")
    private String ext;

    @Schema(example = "05/20/2020")
    private String fathersDateOfBirth;

    @Schema(example = "Engineer")
    private String fathersOccupation;

    @Schema(example = "1,000,000")
    private String fathersMonthlyIncome;

    @Schema(example = "12,000,000")
    private String fathersYearlyCompensation;

    @Schema(example = "09106121529")
    private String fathersContactNo;

    @Schema(example = "College Graduate")
    private String fathersEducationalAttainment;

    @Schema(example = "Great Ashley")
    private String mothersFirstName;

    @Schema(example = "Trampe")
    private String mothersMiddleName;

    @Schema(example = "Anduiza")
    private String mothersMaidenName;

    @Schema(example = "T.")
    private String mothersMiddleInitial;

    @Schema(example = "05/20/2020")
    private String mothersDateOfBirth;

    @Schema(example = "Engineer")
    private String mothersOccupation;

    @Schema(example = "1,000,000")
    private String mothersMonthlyIncome;

    @Schema(example = "12,000,000")
    private String mothersYearlyCompensation;

    @Schema(example = "09106121529")
    private String mothersContactNo;

    @Schema(example = "College Graduate")
    private String mothersEducationalAttainment;

    @Schema(example = "Single/Married/Separated/Widow/Annulled/Solo Parent")
    private String parentMaritalStatus;

    @Schema(example = "Great Ashley")
    private String guardiansFirstName;

    @Schema(example = "Trampe")
    private String guardiansMiddleName;

    @Schema(example = "Anduiza")
    private String guardiansLastName;

    @Schema(example = "Blk 2 Lot 12 UC2")
    private String homeAddress;

    @Schema(example = "Citrus")
    private String brgy;

    @Schema(example = "San Jose Del Monte")
    private String municipality;

    @Schema(example = "Bulacan")
    private String province;

    @Schema(example = "masterglenn101888@gmail.com")
    private String email;

    @Schema(example = "09106121529")
    private String contactNo;

    @Schema(example = "Salary")
    private String sourceOfRevenue;

    @Schema(example = "Work")
    private String natureOfBusiness;

    @Schema(example = "1,000,000")
    private String monthlyIncome;

    @Schema(example = "12,000,000")
    private String mothersYearlyIncome;

    @Schema(example = "Glenn Mark Anduiza")
    private String contactPerson;

    @Schema(example = "Blk 2 Lot 12 UC2 Brgy Citrus, City of San Jose Del Monte, Bulacan")
    private String cpAddress;

    @Schema(example = "09106121529")
    private String cpMobileNo;

    @Schema(example = "+639106121529")
    private String cpTelephoneNo;

    @Schema(example = "masterglenn101888@gmail.com")
    private String cpEmail;
}
