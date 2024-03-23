package com.skywhalelab.SpringBootJpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.skywhalelab.SpringBootJpa.dao.BoardRepository;
import com.skywhalelab.SpringBootJpa.dto.Board;
import com.skywhalelab.SpringBootJpa.dto.BoardPage;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository repository;

	@Override
	public BoardPage findAll(BoardPage boardPage) {
		
		long itemTotCnt = 0;
		int itemPerPage = 10;
		
		int naviIdx = 1;
		int naviLstIdx = Integer.MAX_VALUE;
		
		int naviSize = 5;
		
		int naviStIdx = 1; 
		int naviEdIdx = 5;
		
		String searchText = boardPage.getSearchText();
		
		if (searchText != null && !searchText.isEmpty()) {
			itemTotCnt = repository.countByTitleContaining(searchText);
		} else {
			itemTotCnt = repository.count();	
		}
		
		boardPage.setItemTotCnt(itemTotCnt);
		
		if (itemTotCnt == 0) {
			boardPage.setNaviStIdx(1);
			boardPage.setNaviEdIdx(1);
			boardPage.setNaviIdx(1);
			boardPage.setNaviLstIdx(1);
			return boardPage;
		}
		
		itemPerPage = boardPage.getItemPerPage();
		
		naviIdx = boardPage.getNaviIdx();
		naviLstIdx = boardPage.getNaviLstIdx();
		
		naviSize = boardPage.getNaviSize();
		naviStIdx = boardPage.getNaviStIdx();
		naviEdIdx = boardPage.getNaviEdIdx();
		
		long itemStIdx = 0; 
		long itemEdIdx = 0;
		
		if(itemTotCnt <= itemPerPage) {
			naviIdx = 1;
			naviStIdx = 1;
			naviEdIdx = 1;
			naviLstIdx = 1;
			
			itemStIdx = 1;
			itemEdIdx = itemTotCnt;
		} else {
			if(itemTotCnt % itemPerPage == 0) {
				naviLstIdx = (int)(itemTotCnt / itemPerPage);
			} else {
				naviLstIdx = (int)(itemTotCnt / itemPerPage) + 1;
			}
			
			itemStIdx = itemTotCnt - (naviIdx * itemPerPage) + 1;
			itemEdIdx = itemTotCnt - ((naviIdx - 1) * itemPerPage);
			
			if (itemStIdx < 1) {
				itemStIdx = 1;
			}
			
			if(naviIdx < naviStIdx) {
				System.out.println("naviIdx too small");
				
				while(naviIdx < naviStIdx) {
					naviStIdx = naviStIdx - naviSize;
				}
				
				naviEdIdx = naviStIdx + naviSize - 1;
				
			} else if (naviIdx > naviEdIdx) {
				System.out.println("naviIdx too big");
				
				while(naviIdx > naviEdIdx) {
					naviEdIdx = naviEdIdx + naviSize;
				}
				
				naviStIdx = naviEdIdx - naviSize + 1;
				
			}
			
			if(naviEdIdx > naviLstIdx) {
				naviEdIdx = naviLstIdx;
			}
		}
		
		boardPage.setNaviIdx(naviIdx);
		boardPage.setNaviLstIdx(naviLstIdx);
		boardPage.setNaviStIdx(naviStIdx);
		boardPage.setNaviEdIdx(naviEdIdx);
		
		boardPage.setItemTotCnt(itemTotCnt);
		boardPage.setItemStIdx(itemStIdx);
		boardPage.setItemEdIdx(itemEdIdx);
		
		Pageable pageable = PageRequest.of(boardPage.getNaviIdx() - 1, boardPage.getItemPerPage(), Sort.by("seq").descending());
		
		pageable = PageRequest.of(boardPage.getNaviIdx() - 1, boardPage.getItemPerPage(), Sort.by("seq").descending());
		
		long rownum = itemEdIdx;
		
		if (searchText != null && !searchText.isEmpty()) {
			/*
			repository.findByTitleContaining(searchText, pageable).forEach(board -> {
				boardPage.getList().add(board);
			});
			*/
			List<Board> list = repository.findByTitleContaining(searchText, pageable);
			
			for (int i = 0; i < list.size(); i++) {
				Board board = list.get(i);
				board.setRownum(rownum--);
				boardPage.getList().add(board);
			}
		} else {
			
			/*
			repository.findAll(pageable).forEach(board -> {
				
				System.out.println(board);
				
				//board.setRownum(rownum--);
				
				boardPage.getList().add(board);
			});
			*/
			
			Page<Board> result = repository.findAll(pageable);
			
			List<Board> list = result.getContent();
			
			for (int i = 0; i < list.size(); i++) {
				Board board = list.get(i);
				board.setRownum(rownum--);
				boardPage.getList().add(board);
			}
		}	
		
		return boardPage;
	}

}
