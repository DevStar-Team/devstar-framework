package me.devstar.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = Application.BASE_PACKAGE_PREFIX, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "*Autoconfigure") }, excludeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
@EnableAutoConfiguration
public class Application implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static final String BASE_PACKAGE_PREFIX = "me.devstar";

	@Override
	public void afterPropertiesSet() throws Exception {
		if (LOG.isDebugEnabled()) {
			LOG.debug(getClass().getName() + " initialized.");
		}
	}
}
