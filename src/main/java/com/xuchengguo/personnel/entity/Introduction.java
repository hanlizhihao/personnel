package com.xuchengguo.personnel.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *简介
 * @author Administrator 2017-1-7
 */
@Entity
@Table(name = "introduction", catalog = "personnel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Introduction.findAll", query = "SELECT i FROM Introduction i")
    , @NamedQuery(name = "Introduction.findById", query = "SELECT i FROM Introduction i WHERE i.id = :id")
    , @NamedQuery(name = "Introduction.findByDepartmentId", query = "SELECT i FROM Introduction i WHERE i.departmentId = :departmentId")
    , @NamedQuery(name = "Introduction.findByName", query = "SELECT i FROM Introduction i WHERE i.name = :name")
    , @NamedQuery(name = "Introduction.findBySex", query = "SELECT i FROM Introduction i WHERE i.sex = :sex")
    , @NamedQuery(name = "Introduction.findByAge", query = "SELECT i FROM Introduction i WHERE i.age = :age")
    , @NamedQuery(name = "Introduction.findByBirthday", query = "SELECT i FROM Introduction i WHERE i.birthday = :birthday")
    , @NamedQuery(name = "Introduction.findByNation", query = "SELECT i FROM Introduction i WHERE i.nation = :nation")
    , @NamedQuery(name = "Introduction.findByNativePlace", query = "SELECT i FROM Introduction i WHERE i.nativePlace = :nativePlace")
    , @NamedQuery(name = "Introduction.findByBirthplace", query = "SELECT i FROM Introduction i WHERE i.birthplace = :birthplace")
    , @NamedQuery(name = "Introduction.findByJoinPartyTime", query = "SELECT i FROM Introduction i WHERE i.joinPartyTime = :joinPartyTime")
    , @NamedQuery(name = "Introduction.findByJoinWorkTime", query = "SELECT i FROM Introduction i WHERE i.joinWorkTime = :joinWorkTime")
    , @NamedQuery(name = "Introduction.findByPhysicalCondition", query = "SELECT i FROM Introduction i WHERE i.physicalCondition = :physicalCondition")
    , @NamedQuery(name = "Introduction.findByEducationBackground", query = "SELECT i FROM Introduction i WHERE i.educationBackground = :educationBackground")
    , @NamedQuery(name = "Introduction.findByGraduationSchool", query = "SELECT i FROM Introduction i WHERE i.graduationSchool = :graduationSchool")
    , @NamedQuery(name = "Introduction.findByNowJob", query = "SELECT i FROM Introduction i WHERE i.nowJob = :nowJob")})
public class Introduction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    @Id
    private int id;
    @Column(name = "department_id")
    private Integer departmentId;
    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;
    @Size(max = 255)
    @Column(name = "sex", length = 255)
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "birthday")
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
    private Date joinPartyTime;
    @Column(name = "join_work_time")
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
    @Lob
    @Size(max = 2147483647)
    @Column(name = "resume", length = 2147483647)
    private String resume;
    @Lob
    @Size(max = 65535)
    @Column(name = "rewards_punishment", length = 65535)
    private String rewardsPunishment;

    public Introduction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

}
