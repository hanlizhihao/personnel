package webModel;

public class AssessModel {
	private String name;
	private String job;
	private int quality;//素质得分
	private int ability;//能力得分
	private int outstanding;//业绩得分
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
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getAbility() {
		return ability;
	}
	public void setAbility(int ability) {
		this.ability = ability;
	}
	public int getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(int outstanding) {
		this.outstanding = outstanding;
	}
	
}
