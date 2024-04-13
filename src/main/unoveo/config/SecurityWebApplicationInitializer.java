package config;

import jakarta.servlet.Filter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SecurityWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//AnnotationConfigWebApplicationContext
    @Override
    protected Class<?>[] getRootConfigClasses() {
  return null;    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("Dispatcher initializer");
        return new Class[] {
                WebSecurityConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    protected Filter[] getServletFilters() {
        return new Filter[] { new HiddenHttpMethodFilter() };
    }
    // ... other overrides ...
}