package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bitGallon.complaintMgmt.entity.IssueType;
import com.bitGallon.complaintMgmt.repository.IssueTypeRepository;

@Repository
@Transactional
public class IssueTypeManager {
	@Autowired
	private IssueTypeRepository repository;

	public Long saveIssueType(IssueType issueType) throws Exception {
		return repository.saveIssueType(issueType);
	}
	
	public IssueType getIssueType(int id) {
		return repository.getIssueType(id);
	}
	
	public List<IssueType> getAllIssueTypes(){
		return repository.getAllIssueTypes();
	}
	
}
