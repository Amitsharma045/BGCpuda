/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.bitGallon.complaintMgmt.entity.Category
 *  com.bitGallon.complaintMgmt.entity.Role
 *  com.bitGallon.complaintMgmt.entity.RoleCategoryMapping
 *  com.bitGallon.complaintMgmt.repository.RoleCategoryRepository
 *  javax.transaction.Transactional
 *  org.hibernate.Query
 *  org.hibernate.Session
 *  org.hibernate.SessionFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Repository
 */
package com.bitGallon.complaintMgmt.repository;

import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.Role;
import com.bitGallon.complaintMgmt.entity.RoleCategoryMapping;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RoleCategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<Category> getAssignedCategory(Role role) {
        List<RoleCategoryMapping> roleCategoryMappinglist = this.getSession().createQuery("FROM RoleCategoryMapping rc WHERE rc.isActive = 1 and rc.role.id =:p1").setParameter("p1", (Object)role.getId()).list();
        if (roleCategoryMappinglist.size() != 0) {
            ArrayList<Category> catList = new ArrayList<Category>();
            for (RoleCategoryMapping roleCategoryMapping : roleCategoryMappinglist) {
                catList.add(roleCategoryMapping.getCategory());
            }
            return catList;
        }
        return null;
    }
}

