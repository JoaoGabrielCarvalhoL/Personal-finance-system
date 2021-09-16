package br.com.carv.finances.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import br.com.carv.finances.entities.enums.StatusRegister;
import br.com.carv.finances.entities.enums.TypeRegister;

@Entity
@Table(name = "postingrecords", schema = "fin")
public class PostingRecords {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpr")
	private Long idpr;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "monthregister")
	private Integer monthRegister;
	
	@Column(name = "yearregister")
	private Integer yearRegister;
	
	@Column(name = "valueregister")
	private BigDecimal valueRegister;
	
	@JoinColumn(name = "id_user")
	@ManyToOne
	private User user; 
	
	@Column(name = "date_register")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dateRegister;
	
	@Column(name = "typeregister")
	@Enumerated(value = EnumType.STRING)
	private TypeRegister typeRegister;
	
	@Column(name = "statusregister")
	@Enumerated(value = EnumType.STRING)
	private StatusRegister statusRegister;
	
	
	public PostingRecords() {
		
	}


	public PostingRecords(String description, Integer monthRegister, Integer yearRegister, BigDecimal valueRegister,
			User user, LocalDate dateRegister, TypeRegister typeRegister, StatusRegister statusRegister) {
		super();
		this.description = description;
		this.monthRegister = monthRegister;
		this.yearRegister = yearRegister;
		this.valueRegister = valueRegister;
		this.user = user;
		this.dateRegister = dateRegister;
		this.typeRegister = typeRegister;
		this.statusRegister = statusRegister;
	}


	public Long getIdpr() {
		return idpr;
	}


	public void setIdpr(Long idpr) {
		this.idpr = idpr;
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


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public LocalDate getDateRegister() {
		return dateRegister;
	}


	public void setDateRegister(LocalDate dateRegister) {
		this.dateRegister = dateRegister;
	}


	public TypeRegister getTypeRegister() {
		return typeRegister;
	}


	public void setTypeRegister(TypeRegister typeRegister) {
		this.typeRegister = typeRegister;
	}


	public StatusRegister getStatusRegister() {
		return statusRegister;
	}


	public void setStatusRegister(StatusRegister statusRegister) {
		this.statusRegister = statusRegister;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idpr == null) ? 0 : idpr.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostingRecords other = (PostingRecords) obj;
		if (idpr == null) {
			if (other.idpr != null)
				return false;
		} else if (!idpr.equals(other.idpr))
			return false;
		return true;
	}
	
	

}
