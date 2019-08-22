package com.chowis.kyro.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="content")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Content implements Serializable{
	
	private static final long serialVersionUID = -5573522053321801741L;

	@Id
    @GeneratedValue
	@Column(name = "id", nullable = false)
    private BigInteger id;

    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "content_type", columnDefinition = "tinyint(1)")
    private Integer contentType;

    @Column(name = "version")
    private int version;

    @Column(name="date_updated")
    private Date dateUpdated;
    
    @Column(name="date_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false, nullable = false)
    private Date dateCreated;
    
    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0")
    private int deleted;
    
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
    
    

}
