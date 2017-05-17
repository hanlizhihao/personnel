package webModel;

import java.sql.Date;

public class BillOutputModel {
	private Integer id;
	private String name;
	private String style;
	private double number;
	private String describe;
	private Date timeBill;
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
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getTimeBill() {
		return timeBill;
	}
	public void setTimeBill(Date timeBill) {
		this.timeBill = timeBill;
	}
	
}
