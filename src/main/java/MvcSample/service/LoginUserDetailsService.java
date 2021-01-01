package MvcSample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import MvcSample.domain.LoginUserDetails;
import MvcSample.domain.Staff;
import MvcSample.mapper.StaffMapper;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    StaffMapper staffMapper;

    @Override
    public LoginUserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {
        Staff staff = staffMapper.findByLoginid(loginid);
        if  (staff   ==  null)   {
            throw new UsernameNotFoundException("Wrong email or password");
        }

        return new LoginUserDetails(staff, "ADMIN");
    }

}
