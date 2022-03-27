package web.charmi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class User {

    private Integer OrgId;
    @NotBlank
    @Size(max=20)
    private String OrgName;
    @NotBlank
    @Size(max=50)
    @Email
    private String Email;
    @NotBlank
    private String Password;

    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList=roleList;
    }

    public Integer getOrgId() {
        return OrgId;
    }

    public void setOrgId(Integer orgId) {
        OrgId=orgId;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String orgName) {
        OrgName=orgName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email=email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password=password;
    }
}
