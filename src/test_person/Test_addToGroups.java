package test_person;
/*
 * 测试前提：
 * 完成Person的getter和setter
 * 完成Group的getter
 * 
 * 输入：无
 * 
 * 期待结果：
 * 显示的3个组里面都包含Sam
 */
import java.util.ArrayList;
import java.util.List;

import contectCore.Group;
import contectCore.Person;

public class Test_addToGroups {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Person psPerson= new Person("Sam");
		Group group = new Group("The first group");
		Group group2 = new Group("The second group");
		Group group3 = new Group("The third group");
		List<Group>list = new ArrayList<>();
		list.add(group);
		list.add(group2);
		list.add(group3);
		psPerson.addToGroups(list);
		printer(group);
		printer(group2);
		printer(group3);
	}
	public static void printer(Group group){
		ArrayList<Person> list= group.getList();
		System.out.println("This is a group name: "+group.getListName());
		for (Person person : list) {
			System.out.println(person.getName());
		}
	}
}
