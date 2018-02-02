package se.kwikstrom.brisket.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import se.kwikstrom.brisket.crm.security.VaadinSecurityContextHolderStrategy;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class Application {
	public static void main(String[] args) {
		SecurityContextHolder.setStrategyName(VaadinSecurityContextHolderStrategy.class.getName());
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserDetailsService uds) throws Exception {
		auth.userDetailsService(uds);
	}
}
