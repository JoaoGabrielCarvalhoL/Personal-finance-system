package br.com.carv.finances.dto;

import java.math.BigDecimal;

public class PostingRecordsDto {
	
	private String description; 
	
	private Integer monthRegister;
	
	private Integer yearRegister;
	
	private BigDecimal valueRegister;
	
	private Long idUser; 
	
	private String typePosting;
	
	private String statusPosting;
	
	public PostingRecordsDto() {
		
	}

	public PostingRecordsDto(String description, Integer monthRegister, Integer yearRegister,
			BigDecimal valueRegister, Long idUser, String typePosting, String statusPosting) {
		super();
		this.description = description;
		this.monthRegister = monthRegister;
		this.yearRegister = yearRegister;
		this.valueRegister = valueRegister;
		this.idUser = idUser;
		this.typePosting = typePosting;
		this.statusPosting = statusPosting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMonthRegister() {
		return monthRegister;
	}

	public void setMonthRegister(Integer monthRegister) {
		this.monthRegister = monthRegister;
	}

	public Integer getYearRegister() {
		return yearRegister;
	}

	public void setYearRegister(Integer yearRegister) {
		this.yearRegister = yearRegister;
	}

	public BigDecimal getValueRegister() {
		return valueRegister;
	}

	public void setValueRegister(BigDecimal valueRegister) {
		this.valueRegister = valueRegister;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getTypePosting() {
		return typePosting;
	}

	public void setTypePosting(String typePosting) {
		this.typePosting = typePosting;
	}

	public String getStatusPosting() {
		return statusPosting;
	}

	public void setStatusPosting(String statusPosting) {
		this.statusPosting = statusPosting;
	}
	
	

}
