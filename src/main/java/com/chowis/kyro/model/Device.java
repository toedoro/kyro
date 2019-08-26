package com.chowis.kyro.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="device")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Device implements Serializable {

	private static final long serialVersionUID = -2018423840658666118L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sequence", nullable = false)
    private BigInteger sequence;

    @Column(name = "optic_number")
    private String opticalNumber;

	@Column(name="reg_date")
	private Date registrationDate;
	
    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "del", columnDefinition = "tinyint(1) default 0")
    private int deleted;
    
    @OneToMany(mappedBy = "device", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Content> contents;
    
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "user_sequence")
    private User user;
    
	public BigInteger getSequence() {
		return sequence;
	}

	public void setSequence(BigInteger sequence) {
		this.sequence = sequence;
	}

	public String getOpticalNumber() {
		return opticalNumber;
	}

	public void setOpticalNumber(String opticalNumber) {
		this.opticalNumber = opticalNumber;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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

	public Set<Content> getContents() {
		return contents;
	}

	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}

