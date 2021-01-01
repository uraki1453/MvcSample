package MvcSample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class EqualsValidator implements ConstraintValidator<Equals, Object>{

    private String fieldName;
    private String comparisonFieldName;
    private String message;

    @Override
    public void initialize(Equals equals) {
        fieldName = equals.fieldName();
        comparisonFieldName = equals.comparisonFieldName();
        message = equals.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(fieldName);
        Object comparisonFieldValue = beanWrapper.getPropertyValue(comparisonFieldName);
        boolean matched = ObjectUtils.nullSafeEquals(fieldValue, comparisonFieldValue);
        if(matched) {
            return true;
        }else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
            return false;
        }
    }

}
