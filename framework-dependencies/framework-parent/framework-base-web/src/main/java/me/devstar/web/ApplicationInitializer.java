package me.devstar.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <pre>
 * Spring Boot에서 외부 Tomcat에 WAR 형태로 deploy 하기 위한 클래스
 * - 기본적으로 Spring Boot는 jar &#38; embeded tomcat으로 실행되기 때문에 SpringBootServletInitializer 통해서 외부 Tomcat과 연결 필요
 * 
 * 1. SpringBootServletInitializer 클래스를 상속 받고
 * 2. @Configuration 을 구현한 클래스를 configure 메소드내 등록
 * 3. pom.xml에 spring-boot-starter-tomcat dependency를 provide scope으로 등록
 * 
 * 전통적인 WAR 형태의 deployment 환경에서 SpringApplication 프로그램을 실행하기 위한 WebApplicationInitializer 인터페이스 구현체
 * configure 메소드내에서 Configuration이 필요한(@Configuration) Class를 설정
 * </pre>
 */
public class ApplicationInitializer extends SpringBootServletInitializer {

	private static Logger LOG = LoggerFactory.getLogger(ApplicationInitializer.class);

	/**
	 * SpringApplication에 설정을 위한 Configuration 클래스를 설정
	 * @see org.springframework.boot.builder.SpringApplicationBuilder - SpringApplication or ApplicationContext 객체를 생성하기
	 *      위한 Builder 제공
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		LOG.debug("Application Initializing...");
		return application.sources(Application.class);
	}

	/**
	 * Application의 시작
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.debug("Application Initializing...");

		new SpringApplication(Application.class).run(args);
	}
}
