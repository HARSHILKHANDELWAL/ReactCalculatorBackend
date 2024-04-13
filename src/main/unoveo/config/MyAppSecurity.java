package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class MyAppSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        System.out.println("in sec fil chainnnnnn");
        try {
            http.cors((cors) -> cors
                            .configurationSource(apiConfigurationSource())
                    )
                    .csrf().disable()
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("//user/logout").permitAll()
                            .requestMatchers("/Calculator").hasRole("ADMIN")
                            .requestMatchers("/CalculatorController").hasRole("ADMIN")
                            .requestMatchers("/user/profile").hasRole("USER")
                            .requestMatchers("/admin/profile").hasRole("ADMIN")
                            .requestMatchers("/CustomServlet").permitAll()
                            .anyRequest().authenticated()
                    )
                    .formLogin(
                            (form) -> form
                                    .loginPage("/login")
//                                    .failureHandler(authenticationFailureHandler)
                                    .permitAll()

                    )
                    .httpBasic(Customizer.withDefaults());



        }
        catch(Exception e){
            System.out.println("fdfdfdfd");
        }
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("invoked userdetails");
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


    @Bean
    CorsConfigurationSource apiConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin","Authorization", "Accept"));
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



}
