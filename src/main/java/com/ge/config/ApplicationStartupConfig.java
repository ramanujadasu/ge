package com.ge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ge.service.TBService;

@Component
public class ApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	TBService tbService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent arg0) {
    	tbService.setNotAllocatedSeats();

    }
}
