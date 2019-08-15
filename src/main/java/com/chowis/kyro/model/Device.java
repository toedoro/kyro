package com.chowis.kyro.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="device")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Device implements Serializable {

	private static final long serialVersionUID = -2018423840658666118L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sequency", nullable = false)
    private BigInteger id;

	@NotNull
    @Column(name = "optical_number", nullable = false)
    private String opticalNumber;

	@NotNull
    @Column(name = "app_version", nullable = false)
    private String appVersion;

    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private int deleted;
    
    @Column(name="createdDate", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false, nullable = false)
    private Date createdDate;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_sequence")
//  private User userSequence;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getOpticalNumber() {
		return opticalNumber;
	}

	public void setOpticalNumber(String opticalNumber) {
		this.opticalNumber = opticalNumber;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

}

