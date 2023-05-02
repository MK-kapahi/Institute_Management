package status;

public class StatusBean {
     String id;
     String name;
     String Mobile;
     String College;
     String Sem;
     String Branch;
     String tech;
     String Date;
     String Stime;
     Float total;
     Float Paid;
     Float bal;
     String date;
     public String getCollege() {
		return College;
	}
	public void setCollege(String college) {
		College = college;
	}
	public String getSem() {
		return Sem;
	}
	public void setSem(String sem) {
		Sem = sem;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getStime() {
		return Stime;
	}
	public void setStime(String stime) {
		Stime = stime;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
     public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public Float getPaid() {
		return Paid;
	}
	public void setPaid(Float paid) {
		Paid = paid;
	}
	public Float getBal() {
		return bal;
	}
	public void setBal(Float bal) {
		this.bal = bal;
	}
	public StatusBean(String id, String name, String mobile, String college, String sem, String branch, String tech,
			String date, String stime, Float total, Float paid, Float bal, String date2) {
		super();
		this.id = id;
		this.name = name;
		Mobile = mobile;
		College = college;
		Sem = sem;
		Branch = branch;
		this.tech = tech;
		Date = date;
		Stime = stime;
		this.total = total;
		Paid = paid;
		this.bal = bal;
		date = date2;
	}
	
}
