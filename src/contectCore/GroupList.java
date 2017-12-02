package contectCore;

import java.util.List;
import java.util.ArrayList;

public class GroupList implements Save{
	private ArrayList<Group> groups=new ArrayList<>();
	//�Զ����ɡ�������ϵ�ˡ����
	public GroupList() {
		// TODO ����ʱ�Զ�����һ��������ϵ�˵����
		newGroup("������ϵ��");
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	//����һ���������Ѵ��ڣ����������ʾ���ҷ��ظ��飬���򣬴����µ�����ҷ��ظ������
	public Group newGroup(String group){
	 // TODO �����µ��鵽groups��,����Ѵ��ھͷ��ؾɵ�group
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
	//���ظ�����±꣬��δ�ҵ�������-1
	public int getIndexOfGroup(String group) {
		//found return index ,if not found return -1
		for (int i=0;i<groups.size();i++) {
			if(groups.get(i).getListName().equals(group)){
				return i;
			}
		}
		return -1;
	}
	//ɾ����Ӧ���ͬʱ�����ڵ�����Person������¼�����
	public void removeGroup(String group){
		//TODO ��groups��ɾ��delete���
		Group delete=groups.get(getIndexOfGroup(group));
		for (Person person : delete.getList()) {
			person.removeGroupString(delete.getListName());
		}
		groups.remove(getIndexOfGroup(group));
	}
	public void removeGroup(Group delete){
		//TODO ��groups��ɾ��delete���
		for (Person person : delete.getList()) {
			person.removeGroupString(delete.getListName());
		}
		groups.remove(delete);
	}
	//ɾ��list���г����������,����person�ڵ�����¼��
	public void removeListOfGroup(List<Group> list){
		//TODO ɾ��list���г����������
		int[] count=new int[groups.size()];    //���groups�к���list��Ԫ�ص�λ��
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
	//����ÿ����ϵ�˵�����¼�����ɸ������
	public void generateAllGroup() {
		for (Person person : groups.get(0).getList()) {
			for (int i=1;i<person.getGroups().size();i++) {
				String groupName=person.getGroups().get(i);
				newGroup(groupName).addPerson(person);
			}
		}
	}
	//����ɾ����ϵ��
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
		// TODO �����е������Ϣ���浽�ı���
	}
}