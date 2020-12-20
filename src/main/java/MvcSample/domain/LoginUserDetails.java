package MvcSample.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class LoginUserDetails extends User{
    static boolean enabled = true;
    static boolean accountNonExpired = true;
    static boolean credentialsNonExpired = true;
    static boolean accountNonLocked = true;

    public LoginUserDetails(Staff staff, String role) {
        super(staff.getLoginid(), staff.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, AuthorityUtils.createAuthorityList(role));
    }
}
