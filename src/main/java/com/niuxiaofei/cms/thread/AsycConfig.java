package com.niuxiaofei.cms.thread;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsycConfig implements AsyncConfigurer{

	@Override
	public Executor getAsyncExecutor() {
		//创建线程池对象
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		//设置线程池
		taskExecutor.setCorePoolSize(3); //核心线程数
		taskExecutor.setMaxPoolSize(30); //最大线程数
		taskExecutor.setQueueCapacity(50); //队列
		
		//初始化操作
		taskExecutor.initialize();
		return taskExecutor;
	}

	
}
