package web.mutbrocha;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http
	        .authorizeRequests()
	            .antMatchers("/css/**", "/js/**", "/images/**", "/register", "/registerconta").permitAll()
	            .anyRequest().authenticated()
	        .and()
	        .formLogin(form -> form
	                .loginPage("/login")
	                .defaultSuccessUrl("/", true)
	                .permitAll()
	        )
	        .logout(logout -> logout.logoutUrl("/logout")
	                .logoutSuccessUrl("/login")
	        )
	        .csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder);
	}

}