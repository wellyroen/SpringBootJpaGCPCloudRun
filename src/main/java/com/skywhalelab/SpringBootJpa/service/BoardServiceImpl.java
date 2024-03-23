package com.skywhalelab.SpringBootJpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.skywhalelab.SpringBootJpa.dao.BoardRepository;
import com.skywhalelab.SpringBootJpa.dto.Page;


@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository repository;

	@Override
	public Page findAll(Page page) {
		
		long itemTotCnt = 0;
		int itemPerPage = 10;
		
		int naviIdx = 1;
		int naviLstIdx = Integer.MAX_VALUE;
		
		int naviSize = 5;
		
		int naviStIdx = 1; 
		int naviEdIdx = 5;
		
		String searchText = page.getSearchText();
		
		if (searchText != null && !searchText.isEmpty()) {
			itemTotCnt = repository.countByTitleContaining(searchText);
		} else {
			itemTotCnt = repository.count();	
		}
		
		if (itemTotCnt == 0) {
			page.setNaviStIdx(1);
			page.setNaviEdIdx(1);
			page.setNaviIdx(1);
			page.setNaviLstIdx(1);
			return page;
		}
		
		itemPerPage = page.getItemPerPage();
		
		naviIdx = page.getNaviIdx();
		naviLstIdx = page.getNaviLstIdx();
		
		naviSize = page.getNaviSize();
		naviStIdx = page.getNaviStIdx();
		naviEdIdx = page.getNaviEdIdx();
		
//		long itemStIdx = 0; 
//		long itemEdIdx = 0;
		
		if(itemTotCnt <= itemPerPage) {
			naviIdx = 1;
			naviStIdx = 1;
			naviEdIdx = 1;
			naviLstIdx = 1;
			
//			itemStIdx = 1;
//			itemEdIdx = itemTotCnt;
		} else {
			if(itemTotCnt % itemPerPage == 0) {
				naviLstIdx = (int)(itemTotCnt / itemPerPage);
			} else {
				naviLstIdx = (int)(itemTotCnt / itemPerPage) + 1;
			}
			
//			itemStIdx = itemTotCnt - (naviIdx * itemPerPage) + 1;
//			itemEdIdx = itemTotCnt - ((naviIdx - 1) * itemPerPage);
			
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
		
		page.setNaviIdx(naviIdx);
		page.setNaviLstIdx(naviLstIdx);
		page.setNaviStIdx(naviStIdx);
		page.setNaviEdIdx(naviEdIdx);
		
//		page.setItemTotCnt(itemTotCnt);
//		page.setItemStIdx(itemStIdx);
//		page.setItemEdIdx(itemEdIdx);
		
		Pageable pageable = PageRequest.of(page.getNaviIdx() - 1, page.getItemPerPage(), Sort.by("seq").descending());
		
		pageable = PageRequest.of(page.getNaviIdx() - 1, page.getItemPerPage(), Sort.by("seq").descending());
		
		if (searchText != null && !searchText.isEmpty()) {
			repository.findByTitleContaining(searchText, pageable).forEach(board -> {
				page.getList().add(board);
			});
		} else {
			repository.findAll(pageable).forEach(board -> {
				page.getList().add(board);
			});	
		}	
		
		
		
		return page;
	}

}
