package com.chowis.kyro.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "recommended_product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RecommendedProduct implements Serializable {

	private static final long serialVersionUID = -6020520872676517485L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sequence", nullable = false)
    private BigInteger sequence;

	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "mode")
	private int mode;

	@Column(name = "update_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updateDate;
	
	@ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "user_sequence")
    private User user;

	public BigInteger getSequence() {
		return sequence;
	}

	public void setSequence(BigInteger sequence) {
		this.sequence = sequence;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@PreUpdate
	void onPerist(){
		setUpdateDate(new Date());
	}

}
