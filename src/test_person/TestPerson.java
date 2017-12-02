package test_person;
import contectCore.*;
public class TestPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person ben_ten = new Person("ben_ten_Œ‚ºŒ»Û");
		System.out.println("Name: "+ben_ten.getName());
		System.out.println("phoneticize: "+ben_ten.getPhoneticize());
		System.out.println("short Phoneticize:"+ben_ten.getShortPontic());
		System.out.println("Middle size Phoneticize"+ben_ten.getMidfPontic());
	}

}
