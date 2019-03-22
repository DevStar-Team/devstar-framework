package me.devestar.web.traffic;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
@ConfigurationProperties(prefix = TrafficProperties.CONFIGURATION_PREFIX)
public class TrafficProperties implements InitializingBean {
	

	public static final String CONFIGURATION_PREFIX = "devstar.traffic";
	
	private String kyochon;
	
	private String gram;


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(kyochon);
		
	}


	public String getKyochon() {
		return kyochon;
	}


	public void setKyochon(String kyochon) {
		this.kyochon = kyochon;
	}
	
	

}
