package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator 2017-1-6
 */
@Entity
@Table(name = "limits_power", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LimitsPower.findAll", query = "SELECT l FROM LimitsPower l")
    , @NamedQuery(name = "LimitsPower.findById", query = "SELECT l FROM LimitsPower l WHERE l.id = :id")
    , @NamedQuery(name = "LimitsPower.findByName", query = "SELECT l FROM LimitsPower l WHERE l.name = :name")})
public class LimitsPower implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "describe_limits", length = 65535)
    private String describeLimits;
    @OneToMany(mappedBy = "limitsPower")
    private Collection<User> userCollection;

    public LimitsPower() {
    }

    public LimitsPower(Integer id) {
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

    public String getDescribeLimits() {
        return describeLimits;
    }

    public void setDescribeLimits(String describeLimits) {
        this.describeLimits = describeLimits;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
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
        if (!(object instanceof LimitsPower)) {
            return false;
        }
        LimitsPower other = (LimitsPower) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.LimitsPower[ id=" + id + " ]";
    }

}
