package com.axelor.config;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;


import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class MyGuiceRestEasyConfig extends GuiceResteasyBootstrapServletContextListener {

	@Override
	protected List<? extends Module> getModules(ServletContext context) {
//		return Arrays.asList(new JpaPersistModule("MyPersistence"), new ContactModule());
		 return Arrays.asList(new ServletModule() {
	           @Override
	           protected void configureServlets() {
	               install(new JpaPersistModule("MyPersistence"));
	               filter("/*").through(PersistFilter.class);
	           }
	       }, new ContactModule());
	}

	@Override
	public void withInjector(Injector injector) {
		injector.getInstance(PersistService.class).start();
	}
}
