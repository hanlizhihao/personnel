package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *部门成员表，也做任免表
 * @author Administrator 2017-1-7
 */
@Entity
@Table(name = "membership", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m")
    , @NamedQuery(name = "Membership.findById", query = "SELECT m FROM Membership m WHERE m.id = :id")
    , @NamedQuery(name = "Membership.findByName", query = "SELECT m FROM Membership m WHERE m.name = :name")
    , @NamedQuery(name = "Membership.findBySex", query = "SELECT m FROM Membership m WHERE m.sex = :sex")
    , @NamedQuery(name = "Membership.findByAge", query = "SELECT m FROM Membership m WHERE m.age = :age")
    , @NamedQuery(name = "Membership.findByBirthday", query = "SELECT m FROM Membership m WHERE m.birthday = :birthday")
    , @NamedQuery(name = "Membership.findByNation", query = "SELECT m FROM Membership m WHERE m.nation = :nation")
    , @NamedQuery(name = "Membership.findByNativePlace", query = "SELECT m FROM Membership m WHERE m.nativePlace = :nativePlace")
    , @NamedQuery(name = "Membership.findByBirthplace", query = "SELECT m FROM Membership m WHERE m.birthplace = :birthplace")
    , @NamedQuery(name = "Membership.findByJoinPartyTime", query = "SELECT m FROM Membership m WHERE m.joinPartyTime = :joinPartyTime")
    , @NamedQuery(name = "Membership.findByJoinWorkTime", query = "SELECT m FROM Membership m WHERE m.joinWorkTime = :joinWorkTime")
    , @NamedQuery(name = "Membership.findByPhysicalCondition", query = "SELECT m FROM Membership m WHERE m.physicalCondition = :physicalCondition")
    , @NamedQuery(name = "Membership.findByEducationBackground", query = "SELECT m FROM Membership m WHERE m.educationBackground = :educationBackground")
    , @NamedQuery(name = "Membership.findByGraduationSchool", query = "SELECT m FROM Membership m WHERE m.graduationSchool = :graduationSchool")
    , @NamedQuery(name = "Membership.findByNowJob", query = "SELECT m FROM Membership m WHERE m.nowJob = :nowJob")
    , @NamedQuery(name = "Membership.findByNextJob", query = "SELECT m FROM Membership m WHERE m.nextJob = :nextJob")
    , @NamedQuery(name = "Membership.findByRemoveJob", query = "SELECT m FROM Membership m WHERE m.removeJob = :removeJob")})
public class Membership implements Serializable {

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
    @Column(name = "sex", length = 255)
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 255)
    @Column(name = "nation", length = 255)
    private String nation;
    @Size(max = 255)
    @Column(name = "native_place", length = 255)
    private String nativePlace;
    @Size(max = 255)
    @Column(name = "birthplace", length = 255)
    private String birthplace;
    @Column(name = "join_party_time")
    @Temporal(TemporalType.DATE)
    private Date joinPartyTime;
    @Column(name = "join_work_time")
    @Temporal(TemporalType.DATE)
    private Date joinWorkTime;
    @Size(max = 255)
    @Column(name = "physical_condition", length = 255)
    private String physicalCondition;
    @Size(max = 255)
    @Column(name = "education_background", length = 255)
    private String educationBackground;
    @Size(max = 255)
    @Column(name = "graduation_school", length = 255)
    private String graduationSchool;
    @Size(max = 255)
    @Column(name = "now_job", length = 255)
    private String nowJob;
    @Size(max = 255)
    @Column(name = "next_job", length = 255)
    private String nextJob;
    @Size(max = 255)
    @Column(name = "remove_job", length = 255)
    private String removeJob;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resume", length = 2147483647)
    private String resume;
    @Lob
    @Size(max = 65535)
    @Column(name = "rewards_punishment", length = 65535)
    private String rewardsPunishment;
    @Lob
    @Size(max = 65535)
    @Column(name = "change_reason", length = 65535)
    private String changeReason;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne
    private Department departmentId;

    public Membership() {
    }

    public Membership(Integer id) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Date getJoinPartyTime() {
        return joinPartyTime;
    }

    public void setJoinPartyTime(Date joinPartyTime) {
        this.joinPartyTime = joinPartyTime;
    }

    public Date getJoinWorkTime() {
        return joinWorkTime;
    }

    public void setJoinWorkTime(Date joinWorkTime) {
        this.joinWorkTime = joinWorkTime;
    }

    public String getPhysicalCondition() {
        return physicalCondition;
    }

    public void setPhysicalCondition(String physicalCondition) {
        this.physicalCondition = physicalCondition;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool;
    }

    public String getNowJob() {
        return nowJob;
    }

    public void setNowJob(String nowJob) {
        this.nowJob = nowJob;
    }

    public String getNextJob() {
        return nextJob;
    }

    public void setNextJob(String nextJob) {
        this.nextJob = nextJob;
    }

    public String getRemoveJob() {
        return removeJob;
    }

    public void setRemoveJob(String removeJob) {
        this.removeJob = removeJob;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getRewardsPunishment() {
        return rewardsPunishment;
    }

    public void setRewardsPunishment(String rewardsPunishment) {
        this.rewardsPunishment = rewardsPunishment;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
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
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.xuchengguo.personnel.entity.Membership[ id=" + id + " ]";
    }

}
