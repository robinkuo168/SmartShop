package tw.com.pershing;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"tw.com.pershing.config", 
		"tw.com.pershing.web", 
		"tw.com.pershing.rest", 
		"tw.com.pershing.repository",
		"tw.com.pershing.service"
		})
public class ApplicationConfiguration {

}
