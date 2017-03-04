package testMain;
import java.awt.List;
import java.util.ArrayList;

import contectCore.*;
/*
 * 测试前提：完成TestPerson测试
 */
public class TestGroup {
	public static void main(String[] args) {
		//测试第一种构建方法
		Group group = new Group("家人");
		printer(group);
		
		//测试第二种构建方法
		ArrayList<Person>list=new ArrayList<>();
		list.add(new Person("Jerry"));
		list.add(new Person("Tom"));
		list.add(new Person("vicky"));
		Group group2 = new Group("同学", list);
		printer(group2);
		
		Person delPerson=new Person("Sam");
		//测试addPerson方法
		System.out.println("加入sam到同学列表");
		group2.addPerson(delPerson);
		printer(group2);
		
		//测试deletePerson方法
		System.out.println("删除sam后");
		group2.deletePerson(delPerson);
		printer(group2);
		
		//测试addListofPeople
		ArrayList<Person>list2=new ArrayList<>();
		list2.add(new Person("Jone"));
		list2.add(new Person("Lily"));
		list2.add(new Person("Jony"));
		group.addListOfPeople(list2);
		printer(group);
		
		//测试deleteListOfPeople
		ArrayList<Person>list3=new ArrayList<>();
		list3.add(new Person("Jone"));
		list3.add(new Person("Lily"));
		group.deleteListOfPeople(list3);
		printer(group);
		
	}
	public static void printer(Group group){
		ArrayList<Person> list= group.getList();
		System.out.println("This is a group name: "+group.getListName());
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}
}
/*
 * 期望结果：
 * This is a group name: 家人
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 * 加入sam到同学列表
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
 * This is a group name: 家人
 * Jone
 * Lily
 * Jony
 * This is a group name: 家人
 * Jony
 */
