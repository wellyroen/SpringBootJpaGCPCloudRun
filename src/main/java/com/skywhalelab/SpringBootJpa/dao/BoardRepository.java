package com.skywhalelab.SpringBootJpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skywhalelab.SpringBootJpa.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findByTitle(String title);
	Board findById(long id);
}
