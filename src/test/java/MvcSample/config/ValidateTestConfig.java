package MvcSample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidateTestConfig {
    @Bean
    public SmartValidator validator() {
        return new LocalValidatorFactoryBean();
    }

}
