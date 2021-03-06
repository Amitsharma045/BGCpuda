package com.bitGallon.complaintMgmt.util;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfigurer extends WebMvcConfigurerAdapter {
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("In Resource handlers"); 
         ResourceHandlerRegistration reg = registry.addResourceHandler("/bitGallon/api/uploadedContents/**");
         reg.addResourceLocations("file:"+Constants.COMPLAINT_IMAGE_LOC); 
    }*/
    
	@Override
	public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
        // Turn off suffix-based content negotiation
        configurer.favorPathExtension(false);
    }
	
    @Bean
    public TomcatEmbeddedServletContainerFactory containerFactory() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
         factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
             ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
            }
         });
         return factory;
    }

} 
