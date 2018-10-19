package com.domor.common;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PrintTask {
	
	@Scheduled(cron = "0/10 * * * * ?")
	public void cron() {
		System.out.println("测试cron>>>>>>"+new Date());
	}
	
	//fixedRate上一个调用开始后再次调用的延时
	@Scheduled(fixedRate = 6000)
	public void fixedRate() {
		System.out.println("测试fixedRate>>>>>>"+new Date());
	}
	
	//fixedRate上一个调用完成后再次调用的延时
	@Scheduled(fixedDelay = 7000)
	public void fixedDelay() {
		System.out.println("测试fixedDelay>>>>>>"+new Date());
	}

}
