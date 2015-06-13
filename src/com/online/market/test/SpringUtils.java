package com.online.market.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	public static ApplicationContext applicationContext;
	static{
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
	}
}
