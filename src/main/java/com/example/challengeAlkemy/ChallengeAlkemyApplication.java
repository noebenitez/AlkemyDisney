package com.example.challengeAlkemy;

import com.example.challengeAlkemy.filter.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ChallengeAlkemyApplication {

	private static final String[] AUTH_WHITELIST = {
			"/authenticate",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/v3/api-docs",
			"/webjars/**"
	};

	public static void main(String[] args) {
		SpringApplication.run(ChallengeAlkemyApplication.class, args);
	}

	@EnableSwagger2
	@EnableWebSecurity
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
					.antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
					.antMatchers(AUTH_WHITELIST).permitAll()
					.anyRequest().authenticated();
		}
	}

}
