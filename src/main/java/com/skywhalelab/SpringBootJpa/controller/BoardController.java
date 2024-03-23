package com.skywhalelab.SpringBootJpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.skywhalelab.SpringBootJpa.dto.Page;
import com.skywhalelab.SpringBootJpa.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
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
		
		page = service.findAll(page);
		
		model.addAttribute("page", page);
		
		return "board";
	}
}
