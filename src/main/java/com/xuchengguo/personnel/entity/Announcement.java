package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.sql.Date;
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
 *检务信息
 * @author Administrator 2017-1-5
 */
@Entity//实体 这个类将对应数据库中的一张表
@Table(name = "announcement", catalog = "personnel", schema = "")//对应数据库中的哪一张表，描述这个类
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Announcement.findAll", query = "SELECT a FROM Announcement a")
    , @NamedQuery(name = "Announcement.findById", query = "SELECT a FROM Announcement a WHERE a.id = :id")
    , @NamedQuery(name = "Announcement.findByTitle", query = "SELECT a FROM Announcement a WHERE a.title = :title")
   //根据名字去查询的一个事例 IDE（netbeans）自动生成的
    , @NamedQuery(name = "Announcement.findBySendTime", query = "SELECT a FROM Announcement a WHERE a.sendTime = :sendTime")
    , @NamedQuery(name = "Announcement.findByAuthorName", query = "SELECT a FROM Announcement a WHERE a.authorName = :authorName")
    , @NamedQuery(name = "Announcement.findByStyleName", query = "SELECT a FROM Announcement a WHERE a.styleName = :styleName")})
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;//序列化实际上从 
    //注解标明了实体和表的对应 映射关系
    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自动增加 插入数据时，不需要指定这个主键的属性是什么 数据库会根据表中的数据自动生成序号
    @Basic(optional = false)
    @Column(name = "id", nullable = false)//对应表中的一个属性 描述该属性对应表中的那一个列
    private Integer id;//类中的一个属性 名字叫id
    @Size(max = 255)
    @Column(name = "title", length = 255)
    private String title;
    @Column(name = "send_time")
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
    private String styleName;//六个属性对应表中六个列

    public Announcement() {//构造函数
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

    @Override//标识类所实例化的对象 存储在内存地址
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {//比较属性一致否
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Announcement)) {
            return false;
        }
        Announcement other = (Announcement) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {//把整个对象转化成字符串
        return "com.xuchengguo.personnel.entity.Announcement[ id=" + id + " ]";
    }

}
