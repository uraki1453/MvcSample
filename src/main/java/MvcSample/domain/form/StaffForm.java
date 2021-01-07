package MvcSample.domain.form;

import java.util.Date;

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

    @NotEmpty(groups = Group1.class, message="名前は必須です。")
    @Size(min = 2, max = 30, groups = Group1.class)
    private String name;

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String password;

    @NotEmpty(groups = Group1.class)
    @Size(min = 2, max = 30, groups = Group1.class)
    private String confirmPassword;


    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
