package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitGallon.complaintMgmt.entity.AttachmentDetail;
import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.repository.AttachmentDetailRepository;

@Service
@Transactional
public class AttachmentDetailManager {
	

	@Autowired
	private AttachmentDetailRepository repository;

	public AttachmentDetail[] saveAttachments(ComplaintRegistration savedComplaint, AttachmentDetail[] attachmentDetails,
											  MultipartFile[] uploadedFiles) throws Exception
	{
		// TODO Auto-generated method stub
		return repository.saveAttachments(savedComplaint,attachmentDetails,uploadedFiles);
	}
	
	public List<AttachmentDetail> getAttachments(Long complaintId) throws Exception {
		return repository.getAttachments(complaintId);
	}
		


}
