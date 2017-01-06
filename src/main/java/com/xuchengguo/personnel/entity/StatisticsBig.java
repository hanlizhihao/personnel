package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *统计表实体
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "statistics_big", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatisticsBig.findAll", query = "SELECT s FROM StatisticsBig s")
    , @NamedQuery(name = "StatisticsBig.findById", query = "SELECT s FROM StatisticsBig s WHERE s.id = :id")
    , @NamedQuery(name = "StatisticsBig.findByName", query = "SELECT s FROM StatisticsBig s WHERE s.name = :name")
    , @NamedQuery(name = "StatisticsBig.findByNumber", query = "SELECT s FROM StatisticsBig s WHERE s.number = :number")})
public class StatisticsBig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "number", precision = 22)
    private Double number;

    public StatisticsBig() {
    }

    public StatisticsBig(Integer id) {
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

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
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
        if (!(object instanceof StatisticsBig)) {
            return false;
        }
        StatisticsBig other = (StatisticsBig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.StatisticsBig[ id=" + id + " ]";
    }

}
