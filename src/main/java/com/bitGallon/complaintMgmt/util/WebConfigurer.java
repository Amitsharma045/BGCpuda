package com.bitGallon.complaintMgmt.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/bitGallon/api/uploadedContents/**").addResourceLocations("file:"+Constants.COMPLAINT_IMAGE_LOC);
    }
} 
