package contectCore;

import java.util.List;
import java.util.ArrayList;

public class GroupList implements Save{
	private ArrayList<Group> groups=new ArrayList<>();
	//自动生成“所有联系人”组别
	public GroupList() {
		// TODO 创建时自动生成一个所有联系人的组别
		newGroup("所有联系人");
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	//返回一个组别，如果已存在，输出文字提示并且返回该组，否则，创建新的组别并且返回该新组别
	public Group newGroup(String group){
	 // TODO 创建新的组到groups中,如果已存在就返回旧的group
		int index=getIndexOfGroup(group);
		if(index!=-1){
			System.out.println("The group exist");
			return groups.get(index);
		}else{
			 Group group2=new Group(group);
			 groups.add(group2);
			 return group2;
		}
	    
	}
	//返回该组的下标，若未找到，返回-1
	public int getIndexOfGroup(String group) {
		//found return index ,if not found return -1
		for (int i=0;i<groups.size();i++) {
			if(groups.get(i).getListName().equals(group)){
				return i;
			}
		}
		return -1;
	}
	//删除对应组别，同时把组内的所有Person的组别记录表更新
	public void removeGroup(String group){
		//TODO 从groups中删除delete组别
		Group delete=groups.get(getIndexOfGroup(group));
		for (Person person : delete.getList()) {
			person.removeGroupString(delete.getListName());
		}
		groups.remove(getIndexOfGroup(group));
	}
	public void removeGroup(Group delete){
		//TODO 从groups中删除delete组别
		for (Person person : delete.getList()) {
			person.removeGroupString(delete.getListName());
		}
		groups.remove(delete);
	}
	//删除list中列出的所有组别,更新person内的组别记录表
	public void removeListOfGroup(List<Group> list){
		//TODO 删除list中列出的所有组别
		int[] count=new int[groups.size()];    //标记groups中含有list中元素的位置
		for(int i=0;i<groups.size();i++){
			count[i]=0;
		}
		int j=0;
		for(int i=0;i<list.size();i++){
				if(groups.contains(list.get(i))){
					count[j]=1;
			}
				j++;
		}
		for(int i=0;i<groups.size();i++){
			if(count[i]==1){
				Group delete=groups.get(i);
				for (Person person : delete.getList()) {
					person.removeGroupString(delete.getListName());
				}
				groups.remove(i);
		}
	}
	}
	//根据每个联系人的组别记录表生成各个组别
	public void generateAllGroup() {
		for (Person person : groups.get(0).getList()) {
			for (int i=1;i<person.getGroups().size();i++) {
				String groupName=person.getGroups().get(i);
				newGroup(groupName).addPerson(person);
			}
		}
	}
	//彻底删除联系人
	public void deletPersonCompletly(Person person) {
		for (Group group : groups) {
			group.deletePerson(person);
		}
	}
	public void deletPersonCompletly(ArrayList<Person> persons){
		for (Group group : groups) {
			group.deleteListOfPeople(persons);
		}
	}
	@Override
	public void saveInfor() {
		// TODO 把所有的组别信息保存到文本中
	}
}