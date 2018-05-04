package com.etl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
@EnableScheduling
public class Application extends SpringBootServletInitializer {
//	/**
//	 * 前段过滤器
//	 * @return
//	 */
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		AppHTTPBasicAuthorizeAttribute httpBasicFilter = new AppHTTPBasicAuthorizeAttribute();
//		registrationBean.setFilter(httpBasicFilter);
//		List<String> urlPatterns = new ArrayList<String>();
//		urlPatterns.add("/server/*");
//		registrationBean.setUrlPatterns(urlPatterns);
//		return registrationBean;
//	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		application.sources(this.getClass());
		return super.configure(application);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
