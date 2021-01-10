package MvcSample.domain.form;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;

import MvcSample.config.ValidateTestConfig;
import MvcSample.validation.Group1;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ValidateTestConfig.class})
public class StaffFormTest {
    @Autowired
    SmartValidator validator;

    private StaffForm staffForm = new StaffForm();
    private BindingResult bindingResult = new BindException(staffForm, "StaffForm");

    @BeforeEach
    public void before() {
        staffForm.setLoginid("test");
    }

    @Test
    public void testBlank() {
        validator.validate(staffForm, bindingResult,Group1.class);
        assertThat(bindingResult.getFieldError("name").getDefaultMessage(), is("名前は必須です。"));
    }

}
