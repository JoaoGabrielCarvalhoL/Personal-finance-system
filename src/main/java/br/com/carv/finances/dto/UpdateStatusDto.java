package br.com.carv.finances.dto;

public class UpdateStatusDto {
	
	private String status; 
	
	public UpdateStatusDto() {
		
	}
	
	public UpdateStatusDto(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
