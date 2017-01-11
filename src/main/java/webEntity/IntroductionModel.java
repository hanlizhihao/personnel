package webEntity;

import java.sql.Date;
/**
 * 为了在人员信息页显示部门信息所创建
 * @author Administrator
 *
 */
public class IntroductionModel {
    private int id;
    private String department;
    private String name;
    private String sex;
    private Integer age;
    private Date birthday;
    private String nation;
    private String nativePlace;
    private String birthplace;
    private Date joinPartyTime;
    private Date joinWorkTime;
    private String physicalCondition;
    private String educationBackground;
    private String graduationSchool;
    private String nowJob;
    private String resume;
    private String rewardsPunishment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
