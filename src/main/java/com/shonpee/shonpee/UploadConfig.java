package com.shonpee.shonpee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {
    @Override                                                          
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:C:/Users/CJSCOPE/Desktop/shonpee/src/main/resources/static/pic/");
    }
}


