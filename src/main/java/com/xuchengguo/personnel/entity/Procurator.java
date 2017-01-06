package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "procurator", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procurator.findAll", query = "SELECT p FROM Procurator p")
    , @NamedQuery(name = "Procurator.findById", query = "SELECT p FROM Procurator p WHERE p.id = :id")
    , @NamedQuery(name = "Procurator.findByName", query = "SELECT p FROM Procurator p WHERE p.name = :name")
    , @NamedQuery(name = "Procurator.findByGrade", query = "SELECT p FROM Procurator p WHERE p.grade = :grade")
    , @NamedQuery(name = "Procurator.findByAge", query = "SELECT p FROM Procurator p WHERE p.age = :age")
    , @NamedQuery(name = "Procurator.findByGradeId", query = "SELECT p FROM Procurator p WHERE p.gradeId = :gradeId")})
public class Procurator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "grade", length = 255)
    private String grade;
    @Column(name = "age")
    private Integer age;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resume", length = 2147483647)
    private String resume;
    @Column(name = "grade_id")
    private Integer gradeId;

    public Procurator() {
    }

    public Procurator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procurator)) {
            return false;
        }
        Procurator other = (Procurator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Procurator[ id=" + id + " ]";
    }

}
