package com.wm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(MyListener.class);

    public void  contextInitialized(ServletContextEvent servletContextEvent){
        logger.info("contextInitialized");
    }

    public void  contextDestroyed(ServletContextEvent servletContextEvent){
        logger.info("contextDestroyed");
    }
}
