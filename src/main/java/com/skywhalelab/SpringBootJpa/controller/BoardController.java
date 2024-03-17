package com.skywhalelab.SpringBootJpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BoardController {
	
    @GetMapping("/board")
	public String board(HttpServletRequest request, HttpServletResponse response) {
		return "board";
	}
    
    @GetMapping("/boardOrg")
	public String boardOrg(HttpServletRequest request, HttpServletResponse response) {
		return "boardOrg";
	}
    
    @GetMapping("/tables")
	public String tables(HttpServletRequest request, HttpServletResponse response) {
		return "tables";
	}
}
