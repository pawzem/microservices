package com.pawzem.microservices.beans;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;

@Log
public class PlainJava {

	public static void main(String[] args) {
		Service servicePlain = new SimpleService();
		log.info("Call without a proxy");
		servicePlain.publicMethod1();


		log.info("Call with a proxy");
		ProxyFactory factory = new ProxyFactory(new SimpleService());
		factory.addInterface(Service.class);
		factory.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				log.info("before method " +  method.getName());
			}
		});

		Service serviceProxy = (Service) factory.getProxy();
		// this is a method call on the proxy!
		serviceProxy.publicMethod1();//missing before on public method2
		log.info("now with proxy");
		serviceProxy.publicMethod2();//now it's here
		//https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html#transaction-declarative-annotations-method-visibility
		return;
	}

}
