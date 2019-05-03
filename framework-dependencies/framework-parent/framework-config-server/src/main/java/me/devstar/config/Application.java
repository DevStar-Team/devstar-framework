/**
 * 
 */
package me.devstar.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sudden(sch0718@naver.com)
 */
@SpringBootApplication
@EnableConfigServer
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
}
