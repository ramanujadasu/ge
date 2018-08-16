package com.ge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ge.config.JwtFilter;

@SpringBootApplication(scanBasePackages = {"com.ge"})
public class TicketBookingApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicketBookingApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {

		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/tb/*");
		return registrationBean;
	}
}
