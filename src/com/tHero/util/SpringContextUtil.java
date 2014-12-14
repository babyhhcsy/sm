package com.tHero.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * spring使用的工具类，用来获取spring的相关配置
 * @author babycsy
 *
 */
public class SpringContextUtil implements ApplicationContextAware{
	private static  ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	private static void checkApplicationContext(){
		if(null==applicationContext){
			throw new IllegalStateException("bean对象未注入成功");
		}
	}
	/**
	 * 获得spring中配置的bean对象
	 * @param name
	 * @return object spring配置文件中的bean名称
	 * @throws BeansException
	 * @author babycsy
	 */
	public static <T> Object getBean(String name) throws BeansException{
		checkApplicationContext();
		return (T)applicationContext.getBean(name);
	}
	/**
	 * 获取applicationContext上下文对象
	 * @return ApplicationContext
	 */
	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	/**
	 * 从spring容器中请求事务管理器
	 * @return PlatformTransactionManager
	 * @author babycsy
	 */
	public static PlatformTransactionManager getPlatformTransactionManager(){
		return (PlatformTransactionManager) applicationContext.getBean("transactionManager");
	}
	
}
