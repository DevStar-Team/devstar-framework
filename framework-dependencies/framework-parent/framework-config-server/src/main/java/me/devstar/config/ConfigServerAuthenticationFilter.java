/**
 * 
 */
package me.devstar.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author sudden(sch0718@naver.com)
 */
public class ConfigServerAuthenticationFilter extends BasicAuthenticationFilter {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigServerAuthenticationFilter.class);

	public static final String HEADER_APPLICATION_NAME = "application-name";
	public static final String HEADER_APPLICATION_PASSWORD = "application-password";

	private ConfigClientAccountProperties clientAccount;

	/**
	 * @param authenticationManager
	 */
	public ConfigServerAuthenticationFilter(AuthenticationManager authenticationManager,
			ConfigClientAccountProperties clientAccount) {
		super(authenticationManager);
		this.clientAccount = clientAccount;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String appName = request.getHeader(HEADER_APPLICATION_NAME);
		String password = request.getHeader(HEADER_APPLICATION_PASSWORD);

		if (logger.isDebugEnabled()) {
			LOG.debug("application-name : [{}]", appName);
			LOG.debug("request uri : [{}]", request.getRequestURI());
		}

		if (password != null && password.equals(clientAccount.getAccount().get(appName))) {
			String uri = new StringBuilder("/").append(appName).append("/").toString();

			if (!request.getRequestURI().startsWith(uri) && !request.getRequestURI().equals("/encrypt")
					&& !request.getRequestURI().equals("/decrypt")) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(appName, null,
					Collections.emptyList());

			SecurityContextHolder.getContext().setAuthentication(authentication);

			chain.doFilter(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
	}
}
