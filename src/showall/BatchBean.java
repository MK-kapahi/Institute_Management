package showall;

public class BatchBean {

	String batch;
	String date;
	String time;
	int tseats;
	int bseats;
	float fee;
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTseats() {
		return tseats;
	}
	public void setTseats(int tseats) {
		this.tseats = tseats;
	}
	public int getBseats() {
		return bseats;
	}
	public void setBseats(int bseats) {
		this.bseats = bseats;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	
	public BatchBean() {}
	public BatchBean(String batch, String date, String time, int tseats, int bseats, float fee) {
		super();
		this.batch = batch;
		this.date = date;
		this.time = time;
		this.tseats = tseats;
		this.bseats = bseats;
		this.fee = fee;
	}
	
}
