package ph.edu.cdsga.sms.ums.dto.info;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ph.edu.cdsga.sms.ums.common.model.Format;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Student Info")
public class PersonalInfoDto extends Format {

    @Schema(example = "Caspy")
    private String firstName;

    @Schema(example = "Alcantara")
    private String middleName;

    @Schema(example = "Anduiza")
    private String lastName;

    @Schema(example = "A.")
    private String middleInitial;

    @Schema(example = "Jr.")
    private String ext;

    @Schema(example = "Male")
    private String gender;

    @Schema(example = "12/20/2022")
    private String dateOfBirth;

    @Schema(example = "Lucena City")
    private String placeOfBirth;

    @Schema(example = "Single")
    private String status;

    @Schema(example = "Filipino")
    private String nationality;

    @Schema(example = "Roman Catholic")
    private String religion;

    @Schema(example = "masterglenn101888@gmail.com")
    private String email;

    @Schema(example = "09106121529")
    private String contactNo;

    @Schema(example = "170")
    private String height;

    @Schema(example = "86.5")
    private String weight;

    @Schema(example = "O")
    private String bloodType;

    @Schema(example = "Tribo ni Kalpus")
    private String ethnicity;

    @Schema(example = "Second Dose")
    private String vaccinationStatus;

    @Schema(example = "/to/be/converted/in/bytes")
    private String studentIdPath;

    @Schema(example = "/to/be/converted/in/bytes")
    private String signaturePath;

    @Schema(example = "2024-10-00-00")
    private String lrl;

    @Schema(example = "NO")
    private String isSped;

    @Schema(example = "NO")
    private String isPwd;

    private List<ResidentialAddressDto> residentialAddressDto;
    private List<PermanentAddressDto> permanentAddressDto;
    private List<EmergencyInfoDto> emergencyInfoDto;
    private List<SiblingInfoDto> siblingInfoDto;
    private List<EducationalBackgroundDto> educationalBackgroundDto;
    private List<FamilyBackgroundDto> familyBackgroundDto;
}
