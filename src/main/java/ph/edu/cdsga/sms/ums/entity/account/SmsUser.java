//package ph.edu.cdsga.sms.ums.entity.account;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import ph.edu.cdsga.sms.ums.utils.object.ObjectUtils;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "SMS_USER")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class SmsUser {
//
//    @Id
//    @GeneratedValue(generator = "uuid-hibernate-generator")
//    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(name = "ID")
//    private String id;
//
//    @Column(name = "FIRST_NAME")
//    private String firstName;
//
//    @Column(name = "MIIDLE_NAME")
//    private String middleName;
//
//    @Column(name = "LAST_NAME")
//    private String lastName;
//
//    @Column(name = "EMAIL")
//    private String email;
//
//    @Column(name = "MOBILE_NUMBER")
//    private String mobileNumber;
//
//    @Column(name = "STUDENT_ID_NO")
//    private String studentIdNo;
//
//    @Column(name = "ACAD_PROGRAM")
//    private String academicProgram;
//
//    @Column(name = "USER_ROLE")
//    private String userRole;
//
//    @Column(name = "USERNAME")
//    private String username;
//
//    @Column(name = "PASSWORD")
//    private String password;
//
//    @Column(name = "LAST_PASSWORD")
//    private String lastPassword;
//
//    @Column(name = "LOGIN_ATTEMPTS")
//    private int loginAttempts;
//
//    @Column(name = "STATUS")
//    private String status;
//
//    @Column(name = "IS_ACTIVE")
//    private boolean isActive;
//
//    @Column(name = "IS_LOCKED")
//    private boolean isLocked;
//
//    @Column(name = "CREATION_DATE")
//    private String creationDate;
//
//    @Column(name = "CREATED_BY")
//    private String createdBy;
//
//    @Column(name = "LAST_MODIFICATION_DATE")
//    private String lastModificationDate;
//
//    @Column(name = "LAST_MODIFIED_BY")
//    private String lastModifiedBy;
//
//    @PrePersist
//    public void onPrePersist() {
//        String date = ObjectUtils.getCurrentDateAndTime();
//        this.setCreatedBy("System");
//        this.setCreationDate(date);
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        String date = ObjectUtils.getCurrentDateAndTime();
//        this.setLastModifiedBy("System");
//        this.setLastModificationDate(date);
//    }
//
//}
