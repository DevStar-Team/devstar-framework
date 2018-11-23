/**
 * 
 */
package me.devstar.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author sudden(sch0718@naver.com)
 */
@Configuration
@AutoConfigureAfter(ConfigClientAccountProperties.class)
public class ConfigServerAutoconfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	private ConfigClientAccountProperties clientAccount;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().logout().disable().formLogin().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, e) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests().anyRequest().authenticated().and()
				.addFilterBefore(new ConfigServerAuthenticationFilter(authenticationManager(), clientAccount),
						UsernamePasswordAuthenticationFilter.class);
	}
}
