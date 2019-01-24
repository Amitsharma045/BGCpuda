package com.bitGallon.complaintMgmt.config;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class HibernateBuildInCriterion {
	private static final int PARAMETER_LIMIT = 800;

	public static Criterion buildInCriterion(String propertyName, List<?> values) {
	 Criterion criterion = null;
	 int listSize = values.size();
	 for (int i = 0; i < listSize; i += PARAMETER_LIMIT) {
	 List<?> subList;
	 if (listSize > i + PARAMETER_LIMIT) {
	    subList = values.subList(i, (i + PARAMETER_LIMIT));
	   } else {
	    subList = values.subList(i, listSize);
	   }
	 if (criterion != null) {
	    criterion = Restrictions.or(criterion, Restrictions.in(propertyName, subList));
	   } else {
	    criterion = Restrictions.in(propertyName, subList);
	   }
	 }
	 return criterion;
	   }
}
