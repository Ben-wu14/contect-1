package test_GroupList;
/*
 * 测试前提:
 * 完成TestGroup的测试
 * 完成Test_newGroup测试
 * 
 * 预期结果：
 * 
 * 1st Group
 * 2nd Group
 * 3rd Group
 * 4th Group
 * 
 * 2nd Group
 * 4th Group
 */
import java.util.ArrayList;
import java.util.List;

import contectCore.Group;
import contectCore.GroupList;

public class Test_removeListOfGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroupList groupList = new GroupList();
		Group group = groupList.newGroup("1st Group");
		Group group2 = groupList.newGroup("2nd Group");
		Group group3 = groupList.newGroup("3rd Group");
		Group group4 = groupList.newGroup("4th Group");
		printer(groupList);
		
		System.out.println();
		
		List<Group> list = new ArrayList<>();
		list.add(group3);
		list.add(group);
		groupList.removeListOfGroup(list);
		printer(groupList);
		
	}
	public static void printer(GroupList groupList){
		for (Group group : groupList.getGroups()) {
			System.out.println(group.getListName());
		}
	}

}
