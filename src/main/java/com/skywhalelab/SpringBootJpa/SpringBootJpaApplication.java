package com.skywhalelab.SpringBootJpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.skywhalelab.SpringBootJpa.dao.BoardRepository;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.skywhalelab.SpringBootJpa.controller")
public class SpringBootJpaApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(SpringBootJpaApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootJpaApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}

//    @Bean
//    CommandLineRunner demo(BoardRepository repository) {
//		return (args) -> {
//			
//			/*
//			for(int i = 0; i < 300; i++) {
//				Board board = new Board();
//				board.setTitle("test title " + i);
//				board.setContent("test content " + i);
//				
//				System.out.println(board.toString());
//				
//				repository.save(board);
//			}
//			*/
//			
//            Pageable pageable = PageRequest.of(2, 5, Sort.by("seq").descending());
//            repository.findAll(pageable).forEach(bbs -> {
//				log.info(bbs.toString());
//			});
//			
////			repository.findByTitle("test").forEach(bbs -> {
////				log.info(bbs.toString());
////			});
//		};
//	}
}
