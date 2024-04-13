package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"config","controller", "model",
        "CalculatorController","handler"
})



public class WebSecurityConfig {
WebSecurityConfig(){
    System.out.println("web security invoked");

}
}