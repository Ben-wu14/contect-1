package test_GroupList;

import contectCore.*;
/*
 * ����ǰ��:
 * ���TestGroup�Ĳ���
 * 
 * Ԥ�ڽ����
 * 
 * 1st Group
 * 2nd Group
 */
public class Test_newGroup {
	public static void main(String[] args) {
		GroupList groupList = new GroupList();
		groupList.newGroup("1st Group");
		groupList.newGroup("2nd Group");
	}
	public static void printer(GroupList groupList){
		for (Group group : groupList.getGroups()) {
			System.out.println(group.getListName());
		}
	}
}
