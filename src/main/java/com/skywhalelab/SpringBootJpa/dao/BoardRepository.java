package com.skywhalelab.SpringBootJpa.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.skywhalelab.SpringBootJpa.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	long countByTitleContaining(String title);
	List<Board> findByTitleContaining(String title, Pageable pageable);
	Board findById(long id);
}
