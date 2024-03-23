package com.skywhalelab.SpringBootJpa.service;

import com.skywhalelab.SpringBootJpa.dto.Page;

public interface BoardService {

	public Page findAll(Page param);
}
