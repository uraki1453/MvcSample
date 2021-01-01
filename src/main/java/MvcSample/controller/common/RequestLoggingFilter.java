package MvcSample.controller.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component("loggingFilter")
public class RequestLoggingFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest currentRequest = (HttpServletRequest) request;
        final HttpServletResponse currentResponse = (HttpServletResponse) response;

        StringBuffer requestURL = currentRequest.getRequestURL();
        logger.info("Request URL: {}", requestURL);
        try {
            chain.doFilter(currentRequest, currentResponse);
        } finally {
            int status = 200;//currentResponse
            logger.info("Response status: {}", status);
        }
    }
}
