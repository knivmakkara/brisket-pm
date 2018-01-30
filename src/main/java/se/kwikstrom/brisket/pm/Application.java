package se.kwikstrom.brisket.pm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import se.kwikstrom.brisket.pm.security.VaadinSecurityContextHolderStrategy;

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
		// auth.inMemoryAuthentication().withUser("ram").password("ram123").roles("USER");
		// auth.inMemoryAuthentication().withUser("ravan").password("ravan123").roles("USER");
		// auth.inMemoryAuthentication().withUser("kans").password("kans123").roles("USER");
		// System.out.println("Security SET...");
		auth.userDetailsService(uds);
	}
}
