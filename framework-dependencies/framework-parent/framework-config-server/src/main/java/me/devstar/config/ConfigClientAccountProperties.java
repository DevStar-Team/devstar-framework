/**
 * 
 */
package me.devstar.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author sudden(sch0718@naver.com)
 */
@Configuration
@ConfigurationProperties(prefix = ConfigClientAccountProperties.CONFIGURATION_PREFIX)
public class ConfigClientAccountProperties implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigClientAccountProperties.class);

	public static final String CONFIGURATION_PREFIX = "devstar.client";

	private Map<String, String> account = new HashMap<String, String>();

	public Map<String, String> getAccount() {
		return account;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (String key : account.keySet()) {
			LOG.debug("key: {}, value: {}", key, account.get(key));
		}
	}
}
