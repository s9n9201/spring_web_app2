package web.charmi.util;

import org.springframework.security.core.context.SecurityContextHolder;
import web.charmi.security.service.UserDetailsImp;


public class UserDitail {
    public static int getOrgId() {
        UserDetailsImp userDetailsImp=(UserDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetailsImp.getOrgId();
    }
}
