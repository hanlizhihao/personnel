package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator 2017-1-5
 */
@Entity
@Table(name = "announcement", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Announcement.findAll", query = "SELECT a FROM Announcement a")
    , @NamedQuery(name = "Announcement.findById", query = "SELECT a FROM Announcement a WHERE a.id = :id")
    , @NamedQuery(name = "Announcement.findByTitle", query = "SELECT a FROM Announcement a WHERE a.title = :title")
    , @NamedQuery(name = "Announcement.findBySendTime", query = "SELECT a FROM Announcement a WHERE a.sendTime = :sendTime")
    , @NamedQuery(name = "Announcement.findByAuthorName", query = "SELECT a FROM Announcement a WHERE a.authorName = :authorName")
    , @NamedQuery(name = "Announcement.findByStyleName", query = "SELECT a FROM Announcement a WHERE a.styleName = :styleName")})
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 255)
    @Column(name = "title", length = 255)
    private String title;
    @Column(name = "send_time")
    @Temporal(TemporalType.DATE)
    private Date sendTime;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content", length = 2147483647)
    private String content;
    @Size(max = 255)
    @Column(name = "author_name", length = 255)
    private String authorName;
    @Size(max = 255)
    @Column(name = "style_name", length = 255)
    private String styleName;

    public Announcement() {
    }

    public Announcement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
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
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Announcement[ id=" + id + " ]";
    }

}
