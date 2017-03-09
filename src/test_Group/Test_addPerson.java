package test_Group;

import java.util.ArrayList;

import contectCore.Group;
import contectCore.Person;

/*
 * 测试前提：
 * 通过TestGroup测试
 * 预期结果：
 * 加入sam到同学列表
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 * Sam
 */
public class Test_addPerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Person>list=new ArrayList<>();
		list.add(new Person("Jerry"));
		list.add(new Person("Tom"));
		list.add(new Person("vicky"));
		Group group2 = new Group("同学");
		group2.addListOfPeople(list);
		Person delPerson=new Person("Sam");
		//测试addPerson方法
		System.out.println("加入sam到同学列表");
		group2.addPerson(delPerson);
		printer(group2);
	}
	public static void printer(Group group){
		ArrayList<Person> list= group.getList();
		System.out.println("This is a group name: "+group.getListName());
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}
}
