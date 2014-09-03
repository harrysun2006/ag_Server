package com.agloco.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

public class BeanFactoryAglocoProcessor implements BeanFactoryPostProcessor {

	private static Log _log = LogFactory.getLog(BeanFactoryAglocoProcessor.class);
	private final static String beanNameSessionFactory = "sessionFactory";
	private static ConfigurableListableBeanFactory beanFactory;

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {
		BeanFactoryAglocoProcessor.beanFactory = beanFactory;
		hookHibernateConfiguration();
	}

	private void hookHibernateConfiguration() {
		try {
			AbstractBeanDefinition bd = (AbstractBeanDefinition) beanFactory.getBeanDefinition(beanNameSessionFactory);
			bd.setBeanClass(HibernateConfiguration.class);
			System.out.println("hook Hibernate Configuration success");
		} catch(Exception e) {
			_log.error("Hook Hibernate Configuration Failed!", e);
		}
	}

	
	
}
