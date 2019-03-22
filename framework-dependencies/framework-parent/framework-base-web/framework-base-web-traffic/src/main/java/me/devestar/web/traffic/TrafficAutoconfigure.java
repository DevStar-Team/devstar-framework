package me.devestar.web.traffic;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import me.devstar.web.Application;

@Configuration
@AutoConfigureAfter(Application.class)
@ComponentScan(basePackages = TrafficAutoconfigure.BASE_PACKAGE_PREFIX)
@EntityScan(basePackageClasses = Jsr310JpaConverters.class, basePackages = { TrafficAutoconfigure.BASE_PACKAGE_PREFIX })
@EnableJpaRepositories(basePackages = TrafficAutoconfigure.BASE_PACKAGE_PREFIX)
@EnableJpaAuditing
public class TrafficAutoconfigure {

	public static final String BASE_PACKAGE_PREFIX = "me.devstar.web.traffic";

	
}
