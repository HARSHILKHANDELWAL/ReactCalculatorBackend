package config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    AppSecurityInitializer(){
        System.out.println("Security Web application initializer");
    }
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
