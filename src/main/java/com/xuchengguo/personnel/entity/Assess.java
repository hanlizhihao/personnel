package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 *考核
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "assess", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assess.findAll", query = "SELECT a FROM Assess a")
    , @NamedQuery(name = "Assess.findById", query = "SELECT a FROM Assess a WHERE a.id = :id")
    , @NamedQuery(name = "Assess.findByName", query = "SELECT a FROM Assess a WHERE a.name = :name")
    , @NamedQuery(name = "Assess.findByJob", query = "SELECT a FROM Assess a WHERE a.job = :job")
    , @NamedQuery(name = "Assess.findByScore", query = "SELECT a FROM Assess a WHERE a.score = :score")
    , @NamedQuery(name = "Assess.findByQuality", query = "SELECT a FROM Assess a WHERE a.quality = :quality")
    , @NamedQuery(name = "Assess.findByAbility", query = "SELECT a FROM Assess a WHERE a.ability = :ability")
    , @NamedQuery(name = "Assess.findByOutstanding", query = "SELECT a FROM Assess a WHERE a.outstanding = :outstanding")
    , @NamedQuery(name = "Assess.findByCheckTime", query = "SELECT a FROM Assess a WHERE a.checkTime = :checkTime")})
public class Assess implements Serializable {

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

    public Assess() {
    }

    public Assess(Integer id) {
        this.id = id;
    }

    public Assess(Integer id, double score) {
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
        if (!(object instanceof Assess)) {
            return false;
        }
        Assess other = (Assess) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Assess[ id=" + id + " ]";
    }

}
