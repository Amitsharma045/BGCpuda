package com.bitGallon.complaintMgmt.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BG_Feedback")
public class Feedback extends BaseEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "ComplaintRegistrationId", referencedColumnName = "id")
	private ComplaintRegistration complaintRegistration;
	
	@Column(name = "ServiceRating", nullable = false)
	private String serviceRating;
	
	@Column(name = "ServiceComment", nullable = true)
	private String serviceComment;
	
	@Column(name = "RecommendedPoints", nullable = false)
	private String recommendedPoints;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ComplaintRegistration getComplaintRegistration() {
		return complaintRegistration;
	}

	public void setComplaintRegistration(ComplaintRegistration complaintRegistration) {
		this.complaintRegistration = complaintRegistration;
	}

	public String getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(String serviceRating) {
		this.serviceRating = serviceRating;
	}

	public String getServiceComment() {
		return serviceComment;
	}

	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}

	public String getRecommendedPoints() {
		return recommendedPoints;
	}

	public void setRecommendedPoints(String recommendedPoints) {
		this.recommendedPoints = recommendedPoints;
	}
	
}
