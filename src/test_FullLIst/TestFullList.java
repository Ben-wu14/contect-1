package test_FullLIst;

import contectCore.FullList;
import contectCore.Person;

public class TestFullList {
	public static void main(String[] args) {
		FullList fullList = new FullList();
		Person person1=new Person("ben");
		person1.setTel("845687624");
		Person person2=new Person("zeze");
		person2.setTel("2456");
		fullList.addNewPerson(person1);
		fullList.addNewPerson(new Person("ten"));
		fullList.addNewPerson(new Person("bill"));
		fullList.addNewPerson(new Person("xian"));
		fullList.addNewPerson(new Person("Œ‚“‡∑≤"));
		fullList.addNewPerson(new Person("Œ‚∂´…˝"));
		fullList.addNewPerson(new Person("123ƒæÕ∑»À"));
		fullList.addNewPerson(new Person("apple"));
		fullList.addNewPerson(new Person("Tom"));
		fullList.addNewPerson(new Person("∞¢“Ã"));
		fullList.addNewPerson(person2);
		fullList.addNewPerson(new Person("xj"));
		fullList.addNewPerson(new Person("Œ‚ºŒ»Û"));
		
		for (Person person : fullList.getPersonList()) {
			System.out.println(person.getName());//output the list of people
		}
		for (int person : fullList.getHeader()) {
			System.out.println(person);//get the header of each letter set
		}
		System.out.println(fullList.getPersonList().get(fullList.getHeader()[26]).getName());
		for (Person person : fullList.searchByName("Œ‚")) {
			System.out.println(person.getName());
		}
		for (Person person : fullList.searchByPhoneticize("wds")) {
			System.out.println(person.getName());
		}
		for (Person person : fullList.searchByPhone("8456")) {
			System.out.println(person.getName());
		}
	}
}
