package test_Group;
import java.awt.List;
import java.util.ArrayList;

import contectCore.*;
/*
 * 测试前提：完成TestPerson测试
 * 
 * 期望结果：
 * This is a group name: 家人
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
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
	}
	public static void printer(Group group){
		ArrayList<Person> list= group.getList();
		System.out.println("This is a group name: "+group.getListName());
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}
}
