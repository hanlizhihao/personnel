package webModel;

public class MembershipModel {
    private int id;
    private String department;
    private String name;
    private String sex;
    private Integer age;
    private String nowJob;
    private String removeJob;
    private String nextJob;
    private String resume;
    private String rewardsPunishment;
    private String changeReason;
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
	public String getRemoveJob() {
		return removeJob;
	}
	public void setRemoveJob(String removeJob) {
		this.removeJob = removeJob;
	}
	public String getNextJob() {
		return nextJob;
	}
	public void setNextJob(String nextJob) {
		this.nextJob = nextJob;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
    
}
