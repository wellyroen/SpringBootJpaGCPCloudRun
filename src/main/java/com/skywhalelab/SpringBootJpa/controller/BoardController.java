package com.skywhalelab.SpringBootJpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.skywhalelab.SpringBootJpa.dao.BoardRepository;
import com.skywhalelab.SpringBootJpa.dto.Page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
			
	@Autowired
	BoardRepository repository;
	
	@GetMapping("/tables")
	public String tables() {
		return "tables";
	}
	
	@GetMapping("/boardSample")
	public String boardSample() {
		return "boardSample";
	}
	
	@GetMapping("/board")
	public String board(HttpServletRequest request, HttpServletResponse response
			, Model model
			, @ModelAttribute Page page) {
		
		Pageable pageable = PageRequest.of(page.getNaviIdx(), page.getItemPerPage(), Sort.by("seq").descending());
		
		repository.findAll(pageable).forEach(bbs -> {
			
//			if (page.getList() == null) {
//				page.setList(new ArrayList<Board>());
//			}
			
			page.getList().add(bbs);
		});
		
		model.addAttribute("page", page);
		
		return "board";
	}
}
