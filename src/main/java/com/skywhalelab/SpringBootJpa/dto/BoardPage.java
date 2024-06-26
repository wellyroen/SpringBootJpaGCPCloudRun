package com.skywhalelab.SpringBootJpa.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardPage {
	
	private String searchText;
	
	private long itemTotCnt;
	
	@Builder.Default
	private int itemPerPage = 10;

	@Builder.Default
	private int naviIdx = 1;
	
	@Builder.Default
	private int naviSize = 6;
	
	@Builder.Default
	private int naviStIdx = 1;
	
	@Builder.Default
	private int naviEdIdx = 6;
	
	@Builder.Default
	private int naviLstIdx = Integer.MAX_VALUE;
	
	private long itemStIdx;
	private long itemEdIdx;
	
	@Builder.Default
	private List<Board> list = new java.util.ArrayList<Board>();

}
