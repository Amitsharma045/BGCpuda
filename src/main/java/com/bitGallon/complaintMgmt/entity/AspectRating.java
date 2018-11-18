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
@Table(name = "BG_AspectRating")
public class AspectRating extends BaseEntity<String> implements Serializable {

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
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "AspectId", referencedColumnName = "id")
	private Aspect aspect;
	
	@Column(name = "RatingValue", nullable = false)
	private String ratingValue;

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

	public Aspect getAspect() {
		return aspect;
	}

	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}

	public String getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(String ratingValue) {
		this.ratingValue = ratingValue;
	}
}
