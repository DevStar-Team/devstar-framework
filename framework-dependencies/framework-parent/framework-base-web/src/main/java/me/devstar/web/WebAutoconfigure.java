package me.devstar.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import me.devstar.web.WebApplicationProperties.Pagination;
import me.devstar.web.interceptor.RequestParameterLoggingInterceptor;
import me.devstar.web.resolver.SearchFormHandlerMethodArgumentResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Application 자동 설정
 */
@Configuration
@AutoConfigureAfter(Application.class)
@ComponentScan(basePackages = WebAutoconfigure.BASE_PACKAGE_PREFIX)
@EntityScan(basePackageClasses = Jsr310JpaConverters.class, basePackages = {WebAutoconfigure.BASE_PACKAGE_PREFIX})
@EnableJpaRepositories(basePackages = WebAutoconfigure.BASE_PACKAGE_PREFIX)
@EnableJpaAuditing(auditorAwareRef = "anonymousAuditorAware")
@EnableSwagger2
public class WebAutoconfigure implements WebMvcConfigurer, InitializingBean {
	
	public static final String BASE_PACKAGE_PREFIX = "me.devstar.web";
	
	public static final String LOCALE_PARAMETER_NAME = "lang";
	
	private static final Logger LOG = LoggerFactory.getLogger(WebAutoconfigure.class);
	
	class AnonymousAuditAware implements AuditorAware<String> {
		
		@Override
		public Optional<String> getCurrentAuditor() {
			Optional<String> optional = Optional.ofNullable("anonymous");
			optional.orElse("anonymous");
			return optional;
		}
		
	}
	
	@Autowired
	WebApplicationProperties configs;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(paginationResolver());
		argumentResolvers.add(searchFormResolver());
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getRequestParameterLoggingInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (LOG.isDebugEnabled()) LOG.debug(getClass().getName() + " initialized.");
	}
	
	@Bean
	RequestParameterLoggingInterceptor getRequestParameterLoggingInterceptor() {
		return new RequestParameterLoggingInterceptor();
	}
	
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request로 넘어오는 language parameter를 받아서 locale로 설정 한다.
		localeChangeInterceptor.setParamName(LOCALE_PARAMETER_NAME);
		return localeChangeInterceptor;
	}
	
	@Bean
	LocaleResolver localeResolver() {
		// 세션 기준으로 로케일을 설정 한다.
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 쿠키 기준(세션이 끊겨도 브라우져에 설정된 쿠키 기준으로)
		// CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		
		// 최초 기본 로케일을 강제로 설정이 가능 하다.
		localeResolver.setDefaultLocale(configs.getDefaultLocale());
		return localeResolver;
	}
	
	/**
	 * Pageable을 사용 가능하도록 Resolver 객체 생성
	 * @return
	 */
	@Bean
	PageableHandlerMethodArgumentResolver paginationResolver() {
		Pagination pagination = configs.getPagination();
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		Sort sort = new Sort(Direction.fromString(pagination.getSortDirection()), pagination.getSortProperty());
		resolver.setFallbackPageable(PageRequest.of(0, pagination.getPageSize(), sort));
		// resolver.setOneIndexedParameters(true);
		return resolver;
	}
	
	/**
	 * SearchForm을 사용 가능하도록 Resolver 객체 생성
	 * @return
	 */
	@Bean
	SearchFormHandlerMethodArgumentResolver searchFormResolver() {
		return new SearchFormHandlerMethodArgumentResolver(paginationResolver());
	}
	
	@Bean
	AuditorAware<String> anonymousAuditorAware() {
		return new AnonymousAuditAware();
	}

	
	/**
	 * SWAGGER-UI 자동 생성을 위한 bean 메서드
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any()).build();
	}
	
}
