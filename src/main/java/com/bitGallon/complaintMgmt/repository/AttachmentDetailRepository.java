package com.bitGallon.complaintMgmt.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.util.AmazonS3FilesManager;

@Repository
@Transactional
public class AttachmentDetailRepository {

	@Autowired
	private SessionFactory sessionFactory;

	private static AmazonS3FilesManager fileUpload = new AmazonS3FilesManager();
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public AttachmentDetail[] saveAttachments(ComplaintRegistration savedComplaint, AttachmentDetail[] attachmentDetails,
			MultipartFile[] uploadedFiles) throws Exception
	{
		for (int i=0;i<attachmentDetails.length;i++) {
			attachmentDetails[i]= new AttachmentDetail();
			attachmentDetails[i].setComplaintReferenceId(savedComplaint);
			attachmentDetails[i].setAttachmentType(uploadedFiles[i].getContentType());
			attachmentDetails[i].setName(savedComplaint.getComplaintId()+"_"+uploadedFiles[i].getOriginalFilename());
			attachmentDetails[i].setId((long) getSession().save(attachmentDetails[i]));
			//uploadAttachmentToGivenLoacation
			uploadFile(savedComplaint.getComplaintId(),uploadedFiles[i]);
		}
		return attachmentDetails;
	}

	private void uploadFile(String complaintId, MultipartFile uploadedFiles) throws Exception {
		File destLocation = new File(System.getProperty("java.io.tmpdir")+"/" + complaintId +"_"+ uploadedFiles.getOriginalFilename());
		try {
			uploadedFiles.transferTo(destLocation);
			fileUpload.uploadedFile(destLocation);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<AttachmentDetail> getAttachments(Long complaintId) {
		return getSession().createCriteria(AttachmentDetail.class).add(Restrictions.eq("complaintReferenceId.id", complaintId)).list();
		/*return getSession().createQuery("FROM AttachmentDetail WHERE complaintReferenceId.id =:id")
				.setParameter("id", complaintId).list();*/
	}

	public void updateAttachment(AttachmentDetail attachmentDetail) {
		getSession().saveOrUpdate(attachmentDetail);
	}

}
