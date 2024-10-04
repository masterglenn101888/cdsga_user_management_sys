package ph.edu.cdsga.sms.ums.enums;

/**
 * This is an enum for UserRoles
 */

public enum UserRoles {
    ROLE_SUPER_USER("RS1", "Super Admin"),
    ROLE_ADMIN("RS2", "Admin"),
    ROLE_STUDENT("RS3", "Student"),
    ROLE_PARENT("RS4", "Parent");

    private final String strRoleId;
    private final String strRole;

    UserRoles(String roleId, String role) {
        this.strRoleId = roleId;
        this.strRole = role;
    }

    public String getStrRoleId() {
        return strRoleId;
    }

    public String getStrRole() {
        return strRole;
    }

    public static String getRole(String role){
        String r = "";
        if(role.equals(UserRoles.ROLE_SUPER_USER.getStrRole())){
            r = UserRoles.ROLE_SUPER_USER.getStrRole();
        }else if(role.equals(UserRoles.ROLE_ADMIN.getStrRole())){
            r = UserRoles.ROLE_ADMIN.getStrRole();
        }else if(role.equals(UserRoles.ROLE_STUDENT.getStrRole())){
            r = UserRoles.ROLE_STUDENT.getStrRole();
        }else if(role.equals(UserRoles.ROLE_PARENT.getStrRole())){
            r = UserRoles.ROLE_PARENT.getStrRole();
        }
        return r;
    }

    public static String getRoleId(String role){
        String r = "";
        if(role.equals(ROLE_SUPER_USER.getStrRole())){
            r = UserRoles.ROLE_SUPER_USER.getStrRoleId();
        }else if(role.equals(ROLE_ADMIN.getStrRole())){
            r = UserRoles.ROLE_ADMIN.getStrRoleId();
        }else if(role.equals(ROLE_STUDENT.getStrRole())){
            r = UserRoles.ROLE_STUDENT.getStrRoleId();
        }else if(role.equals(ROLE_PARENT.getStrRole())){
            r = UserRoles.ROLE_PARENT.getStrRoleId();
        }
        return r;
    }
}
