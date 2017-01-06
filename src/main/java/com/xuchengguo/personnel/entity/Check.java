package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "check", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Check.findAll", query = "SELECT c FROM Check c")
    , @NamedQuery(name = "Check.findById", query = "SELECT c FROM Check c WHERE c.id = :id")
    , @NamedQuery(name = "Check.findByName", query = "SELECT c FROM Check c WHERE c.name = :name")
    , @NamedQuery(name = "Check.findByJob", query = "SELECT c FROM Check c WHERE c.job = :job")
    , @NamedQuery(name = "Check.findByScore", query = "SELECT c FROM Check c WHERE c.score = :score")
    , @NamedQuery(name = "Check.findByQuality", query = "SELECT c FROM Check c WHERE c.quality = :quality")
    , @NamedQuery(name = "Check.findByAbility", query = "SELECT c FROM Check c WHERE c.ability = :ability")
    , @NamedQuery(name = "Check.findByOutstanding", query = "SELECT c FROM Check c WHERE c.outstanding = :outstanding")
    , @NamedQuery(name = "Check.findByCheckTime", query = "SELECT c FROM Check c WHERE c.checkTime = :checkTime")})
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "job", length = 255)
    private String job;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score", nullable = false)
    private double score;
    @Column(name = "quality")
    private Integer quality;
    @Column(name = "ability")
    private Integer ability;
    @Column(name = "outstanding")
    private Integer outstanding;
    @Column(name = "check_time")
    @Temporal(TemporalType.DATE)
    private Date checkTime;

    public Check() {
    }

    public Check(Integer id) {
        this.id = id;
    }

    public Check(Integer id, double score) {
        this.id = id;
        this.score = score;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getAbility() {
        return ability;
    }

    public void setAbility(Integer ability) {
        this.ability = ability;
    }

    public Integer getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Integer outstanding) {
        this.outstanding = outstanding;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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
        if (!(object instanceof Check)) {
            return false;
        }
        Check other = (Check) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Check[ id=" + id + " ]";
    }

}
