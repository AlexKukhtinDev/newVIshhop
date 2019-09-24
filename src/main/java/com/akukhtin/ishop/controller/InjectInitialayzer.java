package com.akukhtin.ishop.controller;

import com.akukhtin.ishop.lib.Injector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class InjectInitialayzer implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(InjectInitialayzer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            logger.info("Dependency injection started ...");
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.fatal("FAILED: ", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
