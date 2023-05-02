package googler;

public class GoogleBean {
    String id;     
	String name;
	String Mobile;
	String College;
	String sem;
	String Branch;
	String Technology;
	String Sdate;
	String Stime;
	Float Tfee;
	Float adv;
	Float bal;
	String date;
	
	public GoogleBean(String id, String name, String mobile,String college, String sem, String branch,String technology, String sdate, String stime,
			 Float tfee,  Float adv, Float bal,String date) {
		super();
		this.id = id;
		this.name = name;
		Mobile = mobile;
		College = college;
		this.sem = sem;
		Branch = branch;
		Technology = technology;
		Sdate = sdate;
		Stime = stime;
		Tfee = tfee;
		this.adv = adv;
		this.bal = bal;
		this.date = date;
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
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getCollege() {
		return College;
	}
	public void setCollege(String college) {
		College = college;
	}
	public String getSem() {
		return sem;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	public String getBranch() {
		return Branch;
	}
	public void setBranch(String branch) {
		Branch = branch;
	}
	public String getTechnology() {
		return Technology;
	}
	public void setTechnology(String technology) {
		Technology = technology;
	}
	public String getSdate() {
		return Sdate;
	}
	public void setSdate(String sdate) {
		Sdate = sdate;
	}
	public String getStime() {
		return Stime;
	}
	public void setStime(String stime) {
		Stime = stime;
	}
	public Float getTfee() {
		return Tfee;
	}
	public void setTfee(Float tfee) {
		Tfee = tfee;
	}
	public Float getAdv() {
		return adv;
	}
	public void setAdv(Float adv) {
		this.adv = adv;
	}
	public Float getBal() {
		return bal;
	}
	public void setBal(Float bal) {
		this.bal = bal;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}    
}
