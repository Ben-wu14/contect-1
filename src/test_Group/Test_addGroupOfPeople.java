package test_Group;
/*
 * 测试前提：
 * 完成TestPerson的测试
 * 完成TestGroup的测试
 * 完成Test_addListOfPerson的测试
 * 完成Test_addPerson的测试
 * 
 * 预期结果：
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 * This is a group name: 同学
 * Jerry
 * Tom
 * vicky
 * Jason
 */
import java.util.ArrayList;

import contectCore.Group;
import contectCore.Person;

public class Test_addGroupOfPeople {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Person>list=new ArrayList<>();
		list.add(new Person("Jerry"));
		list.add(new Person("Tom"));
		list.add(new Person("vicky"));
		Group group2 = new Group("同学", list);
		printer(group2);
		
		Group newGroup = new Group("融合组");
		newGroup.addGroupOfPeople(group2);
		newGroup.addPerson(new Person("Jason"));
		printer(newGroup);
		
	}
	public static void printer(Group group){
		ArrayList<Person> list= group.getList();
		System.out.println("This is a group name: "+group.getListName());
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}
}
