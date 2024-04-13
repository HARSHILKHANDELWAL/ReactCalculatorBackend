package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        registry.addViewController("/login.jsp");
//        registry.addViewController("/index1");
//        registry.addViewController("/index");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // @formatter:off
        registry.addResourceHandler("/resources/*")
                .addResourceLocations("classpath:/resources/");
        // @formatter:on
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public ViewResolver viewResolver(ISpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        System.out.println("in spring template resolver method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        resolver.setTemplateMode(TemplateMode.HTML);

//        resolver.setPrefix("WEB-INF/views/");
//        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("http://localhost:3000/")
                .allowedMethods("PUT","DELETE","GET","POST")
                .allowedHeaders("Authorization",
                        "Accept",
                        "Cache-Control",
                        "Content-Type",
                        "Origin",
                        "ajax",
                        "x-csrf-token",
                        "x-requested-with")
                .exposedHeaders("header1", "header2")
                .allowCredentials(true);

        // Add more mappings...
    }
}
