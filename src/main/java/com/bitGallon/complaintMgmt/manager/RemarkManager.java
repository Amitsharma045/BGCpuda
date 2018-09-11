package com.bitGallon.complaintMgmt.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bitGallon.complaintMgmt.entity.Remark;
import com.bitGallon.complaintMgmt.repository.RemarkRepository;

@Repository
@Transactional
public class RemarkManager {
	@Autowired
	private RemarkRepository repository;

	public Long saveRemark(Remark remark) throws Exception {
		return repository.saveRemark(remark);
	}
	
	public Remark getRemark(int id) {
		return repository.getRemark(id);
	}
	
	public List<Remark> getAllRemarks(){
		return repository.getAllRemarks();
	}
}
