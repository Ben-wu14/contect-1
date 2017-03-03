package contectCore;

public class Time {
	private int year;
	private int month;
	private int day;
	public Time(int year,int month,int day) {
		// TODO Auto-generated constructor stub
		this.day=day;
		this.year=year;
		this.month=month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDay()+","+getMonth()+","+getYear();
	}
	
}
