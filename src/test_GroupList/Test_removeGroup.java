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
 * 
 * 1st Group
 * 3rd Group
 */
import contectCore.Group;
import contectCore.GroupList;

public class Test_removeGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroupList groupList = new GroupList();
		groupList.newGroup("1st Group");
		Group group2=groupList.newGroup("2nd Group");
		groupList.newGroup("3rd Group");
		printer(groupList);
		
		System.out.println();
		
		groupList.removeGroup("2nd Group");
		printer(groupList);
		
	}
	public static void printer(GroupList groupList){
		for (Group group : groupList.getGroups()) {
			System.out.println(group.getListName());
		}
	}
}
