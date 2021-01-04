package MvcSample.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import MvcSample.domain.LoginUserDetails;

@Aspect
@Component
public class MapperAspect {
    @Before("execution(* MvcSample.mapper.*Mapper.insert*(..)) || " +
            "execution(* MvcSample.mapper.*Mapper.update*(..))")
    public void setCommonProperty(JoinPoint jp) throws Throwable {

        // Mapperのメソッド名を取得
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();

        // Spring Securityの認証情報を取得
        LoginUserDetails loginUserInfo = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof LoginUserDetails) {
            loginUserInfo = LoginUserDetails.class.cast(authentication.getPrincipal());
        }

        // 現在日時の取得
        Date now = new Date();

        Object[] args = jp.getArgs();
        Object dto = args[0];

        if(methodName.startsWith("insert")) {
            setCreatedProperty(loginUserInfo, now, dto);
            setUpdatedProperty(loginUserInfo, now, dto);
        } else if(methodName.startsWith("update")) {
            setUpdatedProperty(loginUserInfo, now, dto);
        }

    }

    private void setCreatedProperty(LoginUserDetails loginUserInfo, Date now, Object dto) throws Throwable {

        Method setCreatedById = ReflectionUtils.findMethod(dto.getClass(), "setCreatedBy", String.class);
        if(setCreatedById != null) {
            setCreatedById.invoke(dto, loginUserInfo.getStaff().getLoginid());
        }

        Method setCreatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setCreatedAt", Date.class);
        if(setCreatedTimestamp != null) {
            setCreatedTimestamp.invoke(dto, now);
        }

    }

    private void setUpdatedProperty(LoginUserDetails loginUserInfo, Date now, Object dto) throws Throwable {
        Method setUpdatedById  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedBy", String.class);
        if(setUpdatedById != null) {
            setUpdatedById.invoke(dto, loginUserInfo.getStaff().getLoginid());
        }

        Method setUpdatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedAt", Date.class);
        if(setUpdatedTimestamp != null) {
            setUpdatedTimestamp.invoke(dto, now);
        }

    }
}
