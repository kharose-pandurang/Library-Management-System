package DAO;

public class Book {
	private String bname;
	private String aname;
	private String bsub;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getBsub() {
		return bsub;
	}
	public void setBsub(String bsub) {
		this.bsub = bsub;
	}
	@Override
	public String toString() {
		return "Book [bname=" + bname + ", aname=" + aname + ", bsub=" + bsub + ", count=" + count + "]";
	}
	
}
