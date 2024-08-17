package com.pawzem.microservices.beans;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Service
@Log
public class SimpleService implements Service {

	public void publicMethod1() {
		log.info("publicMethod1");
		// direct call
		this.publicMethod2();
	}

	public void publicMethod2() {
		log.info("publicMethod2");
		// some logic...
	}
}