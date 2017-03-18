package test_person;
import contectCore.*;
public class TestPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person ben_ten = new Person("ben_ten_Œ‚ºŒ»Û");
		ben_ten.setPhoneNumber("13660081839");
		ben_ten.setTel("87865467");
		ben_ten.setCompany("SCAU");
		ben_ten.setPostCode("510655");
		ben_ten.setNote("It's a cartoon charactor");
		ben_ten.setAddress("China X-man school street");
		ben_ten.setPicture("\\picture\\ben_ten.jpg");
		Time date = new Time(1996, 9, 30);
		ben_ten.setBirthday(date);
		ben_ten.setEmail("845687624@qq.com");
		ben_ten.setInstantContect("phone");
		System.out.println("Name: "+ben_ten.getName());
		System.out.println("phoneticize: "+ben_ten.getPhoneticize());
		System.out.println("Phone:"+ben_ten.getPhoneNumber());
		System.out.println("Tel:"+ben_ten.getTel());
		System.out.println("Company"+ben_ten.getCompany());
		System.out.println("PostCode"+ben_ten.getPostCode());
		System.out.println("Note:"+ben_ten.getNote());
		System.out.println("Address"+ben_ten.getAddress());
		System.out.println("Picture link:"+ben_ten.getPicture());
		System.out.println("Birthday:"+ben_ten.getBirthday().toString());
		System.out.println("Email:"+ben_ten.getEmail());
		System.out.println("Instant contect:"+ben_ten.getInstantContect());
	}

}
