package ph.edu.cdsga.sms.ums.service;

import org.springframework.stereotype.Service;
import ph.edu.cdsga.sms.ums.common.prop.ApplicationProperties;
import ph.edu.cdsga.sms.ums.common.response.SuccessResponse;
import ph.edu.cdsga.sms.ums.dto.SmsUserDto;
import ph.edu.cdsga.sms.ums.dto.info.*;
import ph.edu.cdsga.sms.ums.entity.info.*;
import ph.edu.cdsga.sms.ums.repository.info.*;
import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;
import ph.edu.cdsga.sms.ums.utils.string.CommonStringUtility;
import ph.edu.cdsga.sms.ums.utils.string.TraceLog;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final ApplicationProperties applicationProperties;
    private final LoggingService loggingService;
    private final PersonalInfoRepository personalInfoRepository;
    private final ResidentialAddressRepository residentialAddressRepository;
    private final PermanentAddressRepository permanentAddressRepository;
    private final EmergencyInfoRepository emergencyInfoRepository;
    private final SiblingInfoRepository siblingInfoRepository;
    private final EducationalBackgroundRepository educationalBackgroundRepository;
    private final FamilyBackgroundRepository familyBackgroundRepository;

    public AccountService(ApplicationProperties applicationProperties,
                          LoggingService loggingService,
                          PersonalInfoRepository personalInfoRepository,
                          ResidentialAddressRepository residentialAddressRepository,
                          PermanentAddressRepository permanentAddressRepository,
                          EmergencyInfoRepository emergencyInfoRepository,
                          SiblingInfoRepository siblingInfoRepository,
                          EducationalBackgroundRepository educationalBackgroundRepository,
                          FamilyBackgroundRepository familyBackgroundRepository) {
        this.applicationProperties = applicationProperties;
        this.loggingService = loggingService;
        this.personalInfoRepository = personalInfoRepository;
        this.residentialAddressRepository = residentialAddressRepository;
        this.permanentAddressRepository = permanentAddressRepository;
        this.emergencyInfoRepository = emergencyInfoRepository;
        this.siblingInfoRepository = siblingInfoRepository;
        this.educationalBackgroundRepository = educationalBackgroundRepository;
        this.familyBackgroundRepository = familyBackgroundRepository;
    }

    public SuccessResponse activateAccount(String uuid, SmsUserDto userDto){
        return new SuccessResponse("", CommonStringUtility.SUCCESS_MSG_REGISTRATION,
                applicationProperties.getAppName(), applicationProperties.getAppVersion());
    }

    public SuccessResponse updateAccount(String uuid, PersonalInfoDto personalInfoDto) {

//        PersonalInfo personalInfo = personalInfoRepository

        PersonalInfo personalInfo = new PersonalInfo();
        ObjectUtils.copyProperties(personalInfoDto, personalInfo);
        personalInfoRepository.save(personalInfo);

        List<ResidentialAddress> rdList = new ArrayList<>();
        for(ResidentialAddressDto rdDto: personalInfoDto.getResidentialAddressDto()){
            ResidentialAddress residentialAddress = new ResidentialAddress();
            ObjectUtils.copyProperties(rdDto, residentialAddress);
            residentialAddress.setPersonalInfo(personalInfo);
            rdList.add(residentialAddress);
        }
        residentialAddressRepository.saveAll(rdList);

        List<PermanentAddress> paList = new ArrayList<>();
        for(PermanentAddressDto paDto: personalInfoDto.getPermanentAddressDto()){
            PermanentAddress permanentAddress = new PermanentAddress();
            ObjectUtils.copyProperties(paDto, permanentAddress);
            permanentAddress.setPersonalInfo(personalInfo);
            paList.add(permanentAddress);
        }
        permanentAddressRepository.saveAll(paList);

        List<EmergencyInfo> eiList = new ArrayList<>();
        for(EmergencyInfoDto eiDto: personalInfoDto.getEmergencyInfoDto()){
            EmergencyInfo emergencyInfo = new EmergencyInfo();
            ObjectUtils.copyProperties(eiDto, emergencyInfo);
            emergencyInfo.setPersonalInfo(personalInfo);
            eiList.add(emergencyInfo);
        }
        emergencyInfoRepository.saveAll(eiList);

        List<SiblingInfo> siList = new ArrayList<>();
        for(SiblingInfoDto siDto: personalInfoDto.getSiblingInfoDto()){
            SiblingInfo siblingInfo = new SiblingInfo();
            ObjectUtils.copyProperties(siDto, siblingInfo);
            siblingInfo.setPersonalInfo(personalInfo);
            siList.add(siblingInfo);
        }
        siblingInfoRepository.saveAll(siList);

        List<EducationalBackground> ebList = new ArrayList<>();
        for(EducationalBackgroundDto ebDto: personalInfoDto.getEducationalBackgroundDto()){
            EducationalBackground educationalBackground = new EducationalBackground();
            ObjectUtils.copyProperties(ebDto, educationalBackground);
            educationalBackground.setPersonalInfo(personalInfo);
            ebList.add(educationalBackground);
        }
        educationalBackgroundRepository.saveAll(ebList);

        List<FamilyBackground> fbList = new ArrayList<>();
        for(FamilyBackgroundDto fbDto: personalInfoDto.getFamilyBackgroundDto()){
            FamilyBackground familyBackground = new FamilyBackground();
            ObjectUtils.copyProperties(fbDto, familyBackground);
            familyBackground.setPersonalInfo(personalInfo);
            fbList.add(familyBackground);
        }
        familyBackgroundRepository.saveAll(fbList);

        loggingService.log(uuid, this.getClass().toString() +  TraceLog.SMS_AUTHENTICATION_SERVICE_AUTHENTICATE,
                "", "PersonalInfo : " + personalInfo.toString());

        return new SuccessResponse("", CommonStringUtility.SUCCESS_MSG_REGISTRATION,
                applicationProperties.getAppName(), applicationProperties.getAppVersion());
    }
}
