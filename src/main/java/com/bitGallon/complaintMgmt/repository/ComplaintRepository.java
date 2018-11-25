package com.bitGallon.complaintMgmt.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.bitGallon.complaintMgmt.entity.ComplaintRegistration;
import com.bitGallon.complaintMgmt.entity.Employee;
import com.bitGallon.complaintMgmt.property.ConstantProperty;

@Repository
@Transactional
public class ComplaintRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public HashMap<Employee, Integer> getAssignedEmployee(List<Employee> empList) {
		HashMap<Employee, Integer> hs = new HashMap<>();
		for(Employee emp : empList) {
			List<Employee> count = getSession()
					.createQuery("FROM ComplaintRegistration cr WHERE cr.employee.id =:p1 and cr.isActive = 1 and cr.status.status =:p2")
					.setParameter("p1", emp.getId())
					.setParameter("p2", ConstantProperty.STATUS_IN_PROGRESS).list();
			hs.put(emp, count.size());
		}
		return hs;
	}
	
	public ComplaintRegistration saveComplaintRegistration(ComplaintRegistration complaintRegistration) throws Exception {
		complaintRegistration.setId((Long) getSession().save(complaintRegistration));
		return complaintRegistration;
	}

	/*@SuppressWarnings("unchecked")
	public ComplaintRegistration getCategory(long id) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG);
		criteria.add(Restrictions.eq("id", id)).add(UtilRepository.isActiveRestricition());
		List<ComplaintRegistration> categoryBeans = UtilRepository.transferToComplaintRegistration(criteria).list();
		if(categoryBeans.isEmpty()) return null;
		return categoryBeans.get(0);
	}*/

	@SuppressWarnings("unchecked")
	public List<ComplaintRegistration> getAllCategory(Pageable pageable) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.CATEGORY_ALIAS);
		criteria.add(UtilRepository.isActiveRestricition());
		Page<ComplaintRegistration> asd = (Page<ComplaintRegistration>) UtilRepository.transferToCategoryBean(criteria).list();
		return asd.getContent();
	}

	public boolean updateIsActive(long id, short isActive) {
		ComplaintRegistration complaint = getSession().byId(ComplaintRegistration.class).load(id);
		if(complaint != null) {
			complaint.setIsActive((short) (isActive == 0 ? 0 : 1) );
			getSession().update(complaint);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<ComplaintRegistration> getAllComplaintsForUser(Pageable page, Long userId, Date startDate,
			Date endDate, Long categoryId) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG_MIN);
		criteria.add(UtilRepository.isActiveRestricition());
		UtilRepository.addPageableAndSorting(criteria, page);
		criteria.add(Restrictions.eq(UtilRepository.USER_ALIAS + ".id", userId));
		criteria.add(Restrictions.ne(UtilRepository.STATUS_ALIAS + ".id", 4l));
		if (startDate != null && endDate != null) {
			UtilRepository.addDateFilterCriteria(criteria, "createdDate", startDate, endDate);
		}
		if(categoryId!=null && categoryId!=0) {
			criteria.add(Restrictions.eq(UtilRepository.CATEGORY_ALIAS+".id", categoryId));
		}
		return UtilRepository.transferToMiniComplaintBean(criteria).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComplaintRegistration> getAllComplaintsForEmployee(Pageable page, Long employeeId, Date startDate,
			Date endDate, Long subCategoryId) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG_MIN);
		criteria.add(UtilRepository.isActiveRestricition());
		UtilRepository.addSorting(criteria, page);
		criteria.add(Restrictions.eq(UtilRepository.EMPLOYEE_ALIAS+ ".id", employeeId));
		criteria.add(Restrictions.lt("id", page.getPageNumber()));
		if (startDate != null && endDate != null) {
			UtilRepository.addDateFilterCriteria(criteria, "createdDate", startDate, endDate);
		}
		if(subCategoryId!=null && subCategoryId!=0) {
			criteria.add(Restrictions.eq(UtilRepository.SUB_CATEGORY_ALIAS+".id", subCategoryId));
		}
		return UtilRepository.transferToMiniComplaintBean(criteria).list();
	}


	@SuppressWarnings("unchecked")
	public ComplaintRegistration getComplaintByComplaintNumber(String complaintNumber, long userId) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG_MIN);
		criteria.add(UtilRepository.isActiveRestricition());
		criteria.add(Restrictions.eq("referenceComplaint", complaintNumber)).add(Restrictions.eq("user.id", userId));
		List<ComplaintRegistration> complaintlist = criteria.setFetchSize(1).list();
		if(complaintlist.size() != 0) {
			return complaintlist.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ComplaintRegistration getComplaintByComplaintNumberForEmployee(String complaintNumber, long employeeId) {
		Criteria criteria = getSession().createCriteria(ComplaintRegistration.class, UtilRepository.COMPLAINT_REG_MIN);
		criteria.add(UtilRepository.isActiveRestricition());
		criteria.add(Restrictions.eq("referenceComplaint", complaintNumber)).add(Restrictions.eq("employee.id", employeeId));
		List<ComplaintRegistration> complaintlist = criteria.addOrder(org.hibernate.criterion.Order.desc("complaintLevel")).setFetchSize(1).list();
		if(complaintlist.size() != 0) {
			return complaintlist.get(0);
		}
		return null;
	}
}
