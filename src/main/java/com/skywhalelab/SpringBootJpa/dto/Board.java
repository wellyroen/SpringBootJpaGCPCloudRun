package com.skywhalelab.SpringBootJpa.dto;

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.skywhalelab.SpringBootJpa.util.cmmUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
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
@Entity
@DynamicInsert
public class Board {

	
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
//	@SequenceGenerator(name = "BOARD_SEQ", sequenceName = "BOARD_SEQ",  allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long seq;
	
	@Transient
	private Long rownum;

	@Column(length = 8)
	@ColumnDefault("'NONE'")
	private String bbsCd;

	@Column(length = 128)
	private String title;

	@Lob
	private String content;

	private Long uprSeq;

	@Column(insertable = false, nullable = false)
	@ColumnDefault("0")
	private Integer rdCnt;

	@Column(length = 2)
	@ColumnDefault("0")
	private Integer dth;

	@Column(length = 1, insertable = false, nullable = false)
	@ColumnDefault("'N'")
	private String delYn;

	/* Not included in [Insert SQL] syntax due to @DynamicInsert annotation when value is null
	 * Error if spring-boot-starter-validation is not present (i.e. it tries to check for validation even though it is not included in the Insert syntax)
	 */
	@Column(length = 320, nullable = false)
	@ColumnDefault("'system'")
	private String usrId;
	
	/* No error occurs with or without spring-boot-starter-validation because the field itself has a default value */
	/*
	@Column(length = 320, nullable = false)
	@ColumnDefault("'system'")
	@Builder.Default
	private String usrId = "system";
	*/
	
	/* For example, this column behaves as follows when null.
     * without spring-boot-starter-validation, it will be checked for not-null and an error will be thrown. Same behaviour as above.
     * If spring-boot-starter-validation is present, it is not included in the Insert syntax due to the @DynamicInsert annotation.
     * However, the table column is not assigned Default, so an error is thrown. 
	 */ 
	/*
	@Column(nullable = false)
	private String testColumn;
	*/

	@Column(insertable = false, updatable = false, nullable = false)
	@ColumnDefault(cmmUtil.DB_SYSDATE)
	private Date creDt;

	private Date modDt;

}