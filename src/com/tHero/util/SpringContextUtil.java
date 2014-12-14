package com.tHero.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * springʹ�õĹ����࣬������ȡspring���������
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
			throw new IllegalStateException("bean����δע��ɹ�");
		}
	}
	/**
	 * ���spring�����õ�bean����
	 * @param name
	 * @return object spring�����ļ��е�bean����
	 * @throws BeansException
	 * @author babycsy
	 */
	public static <T> Object getBean(String name) throws BeansException{
		checkApplicationContext();
		return (T)applicationContext.getBean(name);
	}
	/**
	 * ��ȡapplicationContext�����Ķ���
	 * @return ApplicationContext
	 */
	public ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	/**
	 * ��spring�������������������
	 * @return PlatformTransactionManager
	 * @author babycsy
	 */
	public static PlatformTransactionManager getPlatformTransactionManager(){
		return (PlatformTransactionManager) applicationContext.getBean("transactionManager");
	}
	
}
