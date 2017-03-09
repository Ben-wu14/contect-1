package test_Group;

/*
 * 测试前提：
 * 完成Test_Person
 * 完成TestGroup
 * 完成addPerson方法
 * 
 * 预期结果：
 * 
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 * Sam
 * 删除sam后
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 */
import java.util.ArrayList;

import contectCore.Group;
import contectCore.Person;

public class Test_deletePerson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Person>list=new ArrayList<>();
		list.add(new Person("Jerry"));
		list.add(new Person("Tom"));
		list.add(new Person("vicky"));
		Group group2 = new Group("同学", list);
		
		Person delPerson=new Person("Sam");
		//测试addPerson方法
		group2.addPerson(delPerson);
		printer(group2);
		
		//测试deletePerson方法
		System.out.println("删除sam后");
		group2.deletePerson(delPerson);
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
