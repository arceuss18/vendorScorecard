package com.vendor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "vendor_score_card_poc_test",uniqueConstraints=@UniqueConstraint(columnNames={"vendor_name", "vendor_score_month"}))
public class Vendor implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	@Column(name = "vendor_score_card_poc_id")
	private String id;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@NotNull
	@Column(name = "vendor_score_month")
	private String vendorScoreMonth;
	
	@NotNull
	@Column(name = "vendor_name")
	private String vendorname;
	
	@NotNull
	@Column(name = "vendor_score")
	private double vendorScore;
 
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
 
	public Vendor() {
	}

	public Vendor(String id, String username, String userEmail, @NotNull String vendorScoreMonth,
			@NotNull String vendorname, @NotNull double vendorScore, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.username = username;
		this.userEmail = userEmail;
		this.vendorScoreMonth = vendorScoreMonth;
		this.vendorname = vendorname;
		this.vendorScore = vendorScore;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getVendorScoreMonth() {
		return vendorScoreMonth;
	}

	public void setVendorScoreMonth(String vendorScoreMonth) {
		this.vendorScoreMonth = vendorScoreMonth;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}

	public double getVendorScore() {
		return vendorScore;
	}

	public void setVendorScore(double vendorScore) {
		this.vendorScore = vendorScore;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date string) {
		this.modifiedDate = string;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", username=" + username
				+ ", userEmail=" + userEmail + ", vendorScoreMonth=" + vendorScoreMonth + ", vendorname=" + vendorname
				+ ", vendorScore=" + vendorScore + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ "]";
		
	}
		
}
