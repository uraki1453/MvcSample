package MvcSample.domain.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import MvcSample.validation.Equals;
import MvcSample.validation.Group1;
import MvcSample.validation.Group2;

@Equals(comparisonFieldName = "confirmPassword", fieldName = "password", groups = Group2.class)
public class StaffForm {

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String loginid;

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String name;

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String password;

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String confirmPassword;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getHashPassword() {
        return new BCryptPasswordEncoder().encode(this.password);
    }
}
