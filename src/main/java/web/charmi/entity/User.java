package web.charmi.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.time.Instant;
import java.util.List;

public class User {

    private Integer OrgId;
    @NotBlank(groups={signIn.class, signUp.class})
    @Size(max=20, groups={signIn.class, signUp.class})
    private String OrgName;
    @NotBlank(groups=signUp.class)
    @Size(max=50, groups=signUp.class)
    @Email(groups=signUp.class)
    private String Email;
    @NotBlank(groups={signIn.class, signUp.class})
    private String Password;

    private List<Role> roleList;    //request

    private List<String> strRoleList;   //提供給response用
    @NotBlank(groups=refresh.class)
    private String RefreshToken;

    private Instant ExpiryDate;

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken=refreshToken;
    }

    public Instant getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        ExpiryDate=expiryDate;
    }

    public List<String> getStrRoleList() {
        return strRoleList;
    }

    public void setStrRoleList(List<String> strRoleList) {
        this.strRoleList=strRoleList;
    }

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

    public interface signIn extends Default {}
    public interface signUp extends Default {}
    public interface refresh extends Default {}
}
