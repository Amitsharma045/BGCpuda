/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  com.bitGallon.complaintMgmt.entity.BaseEntity
 *  com.bitGallon.complaintMgmt.entity.Category
 *  com.bitGallon.complaintMgmt.entity.Role
 *  com.bitGallon.complaintMgmt.entity.RoleCategoryMapping
 *  javax.persistence.CascadeType
 *  javax.persistence.Entity
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
 *  javax.persistence.Id
 *  javax.persistence.JoinColumn
 *  javax.persistence.ManyToOne
 *  javax.persistence.Table
 */
package com.bitGallon.complaintMgmt.entity;

import com.bitGallon.complaintMgmt.entity.BaseEntity;
import com.bitGallon.complaintMgmt.entity.Category;
import com.bitGallon.complaintMgmt.entity.Role;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BG_RoleCategoryMapping")
public class RoleCategoryMapping
extends BaseEntity<String>
implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name="BG_RoleId", referencedColumnName="id")
    private Role role;
    @ManyToOne(cascade={CascadeType.DETACH})
    @JoinColumn(name="BG_CategoryId", referencedColumnName="id")
    private Category category;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

