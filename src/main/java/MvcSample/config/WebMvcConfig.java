package MvcSample.config;

import java.nio.charset.StandardCharsets;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import MvcSample.aspect.MapperAspect;
import MvcSample.mapper.StaffMapper;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages= {"MvcSample.controller","MvcSample.service","MvcSample.config, MvcSample.mapper"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/template/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine ste = new SpringTemplateEngine();
        ste.setTemplateResolver(templateResolver());
        ste.setEnableSpringELCompiler(true);
        ste.addDialect(springSecurityDialect());
        return ste;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver tvr = new ThymeleafViewResolver();
        tvr.setTemplateEngine(templateEngine());
        tvr.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return tvr;
    }

    @Bean
    public IDialect springSecurityDialect() {
        SpringSecurityDialect dialect = new SpringSecurityDialect();
        return dialect;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("postgres");
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        //sfb.setConfigLocation(new ClassPathResource("/WEB-INF/mybatis.xml"));
        sfb.setDataSource(dataSource());
        return sfb.getObject();
    }

    @Bean
    public MapperFactoryBean<StaffMapper> mapperFactoryBean() throws Exception {
        MapperFactoryBean<StaffMapper> mfb = new MapperFactoryBean<StaffMapper>();
        mfb.setMapperInterface(MvcSample.mapper.StaffMapper.class);
        mfb.setSqlSessionFactory(sqlSessionFactory());
        return mfb;

    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(dataSource());
        return dstm;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

    @Bean
    public MapperAspect mapperAspect() {
        return new MapperAspect();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry reg) {
        reg.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
