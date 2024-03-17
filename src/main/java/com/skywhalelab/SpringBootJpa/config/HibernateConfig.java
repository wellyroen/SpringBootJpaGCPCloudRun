package com.skywhalelab.SpringBootJpa.config;

import java.util.Set;
import java.util.Map.Entry;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
	@Bean
    HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
		
        return hibernateProperties -> {
        	System.out.println("check now");
        	Set<Entry<String, Object>> entry = hibernateProperties.entrySet();
        	Object [] array = entry.toArray();
        	Object item = null;
        	for(int i = 0; i < array.length; i++) {
        		item = array[i];
        		System.out.println("First: " + item.toString());
        	}
        	
        	/* Whether to null check the entity field itself when the column annotation has [nullable = false]. */
        	/* do not null check */
//        	hibernateProperties.put(Environment.CHECK_NULLABILITY, false);
        	/* Performs a null check. Raises an error if field is null */
//        	hibernateProperties.put(Environment.CHECK_NULLABILITY, true);
        	
        	entry = hibernateProperties.entrySet();
        	array = entry.toArray();
        	for(int i = 0; i < array.length; i++) {
        		item = array[i];
        		System.out.println("Second: " + item.toString());
        	}
        };
    }
}