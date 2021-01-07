package MvcSample.domain.form;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;

import MvcSample.config.TestConfig;
import MvcSample.validation.Group1;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class StaffFormTest {
    @Autowired
    SmartValidator validator;

    private StaffForm staffForm = new StaffForm();
    private BindingResult bindingResult = new BindException(staffForm, "StaffForm");

    @Before
    public void before() {
        staffForm.setLoginid("test");
    }

    @Test
    public void testBlank() {
        validator.validate(staffForm, bindingResult,Group1.class);
        assertThat(bindingResult.getFieldError("name").getDefaultMessage(), is("名前は必須です。"));
    }

}
