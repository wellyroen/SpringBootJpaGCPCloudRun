package com.skywhalelab.SpringBootJpa.service;

import com.skywhalelab.SpringBootJpa.dto.BoardPage;

public interface BoardService {

	public BoardPage findAll(BoardPage param);
}
