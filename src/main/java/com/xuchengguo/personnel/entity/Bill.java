package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "bill", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
    , @NamedQuery(name = "Bill.findById", query = "SELECT b FROM Bill b WHERE b.id = :id")
    , @NamedQuery(name = "Bill.findByName", query = "SELECT b FROM Bill b WHERE b.name = :name")
    , @NamedQuery(name = "Bill.findByStyle", query = "SELECT b FROM Bill b WHERE b.style = :style")
    , @NamedQuery(name = "Bill.findByNumber", query = "SELECT b FROM Bill b WHERE b.number = :number")
    , @NamedQuery(name = "Bill.findByDescribeBill", query = "SELECT b FROM Bill b WHERE b.describeBill = :describeBill")
    , @NamedQuery(name = "Bill.findByTimeBill", query = "SELECT b FROM Bill b WHERE b.timeBill = :timeBill")})
public class Bill implements Serializable {

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
    @Column(name = "style", length = 255)
    private String style;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "number", precision = 11, scale = 0)
    private Double number;
    @Size(max = 255)
    @Column(name = "describeBill", length = 255)
    private String describeBill;
    @Column(name = "timeBill")
    private Date timeBill;
    @JoinColumn(name = "opreaterid", referencedColumnName = "id")
    @ManyToOne
    private User opreaterid;

    public Bill() {
    }

    public Bill(Integer id) {
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getDescribeBill() {
        return describeBill;
    }

    public void setDescribeBill(String describeBill) {
        this.describeBill = describeBill;
    }

    public Date getTimeBill() {
        return timeBill;
    }

    public void setTimeBill(Date timeBill) {
        this.timeBill = timeBill;
    }

    public User getOpreaterid() {
        return opreaterid;
    }

    public void setOpreaterid(User opreaterid) {
        this.opreaterid = opreaterid;
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
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Bill[ id=" + id + " ]";
    }

}
