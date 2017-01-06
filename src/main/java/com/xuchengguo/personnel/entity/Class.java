package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "class", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c")
    , @NamedQuery(name = "Class.findByClassId", query = "SELECT c FROM Class c WHERE c.classId = :classId")
    , @NamedQuery(name = "Class.findByClassName", query = "SELECT c FROM Class c WHERE c.className = :className")
    , @NamedQuery(name = "Class.findByStyle", query = "SELECT c FROM Class c WHERE c.style = :style")})
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "class_id", nullable = false)
    private Integer classId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "class_name", nullable = false, length = 255)
    private String className;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "class_job", nullable = false, length = 65535)
    private String classJob;
    @Column(name = "style")
    private Integer style;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<Membership> membershipCollection;

    public Class() {
    }

    public Class(Integer classId) {
        this.classId = classId;
    }

    public Class(Integer classId, String className, String classJob) {
        this.classId = classId;
        this.className = className;
        this.classJob = classJob;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassJob() {
        return classJob;
    }

    public void setClassJob(String classJob) {
        this.classJob = classJob;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    @XmlTransient
    public Collection<Membership> getMembershipCollection() {
        return membershipCollection;
    }

    public void setMembershipCollection(Collection<Membership> membershipCollection) {
        this.membershipCollection = membershipCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classId != null ? classId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Class[ classId=" + classId + " ]";
    }

}
