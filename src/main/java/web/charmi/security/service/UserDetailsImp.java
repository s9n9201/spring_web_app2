package web.charmi.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import web.charmi.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {
    private Integer OrgId;
    private String OrgName;
    @JsonIgnore
    private String Password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImp(Integer OrgId, String OrgName, String Password, Collection<? extends GrantedAuthority> authorities) {
        this.OrgId=OrgId;
        this.OrgName=OrgName;
        this.Password=Password;
        this.authorities=authorities;
    }

    public static UserDetailsImp build(User user) {
        List<GrantedAuthority> authorities=user.getRoleList().stream()
                .map(role-> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImp(
                user.getOrgId(),
                user.getOrgName(),
                user.getPassword(),
                authorities);
    }

    public Integer getOrgId() {
        return OrgId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return OrgName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
