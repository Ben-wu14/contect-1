package contectCore;

import java.util.List;
import java.util.ArrayList;

public class GroupList implements Save{
	private ArrayList<Group> groups=new ArrayList<>();
	public GroupList() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	public void newGroup(Group group){
	 // TODO �����µ��鵽groups��
		for (Group group1 : groups) {
			if(group1.getListName().equals(group.getListName())){
				groups.add(group);
			}else{
				System.out.println(" /n No Group or with Conflict name");
			}
		}
	    
	}
	public void removeGroup(Group delete){
		//TODO ��groups��ɾ��delete���
		groups.remove(delete);
	}
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
				groups.remove(i);
		}
	}
	}
	@Override
	public void saveInfor() {
		// TODO �����е������Ϣ���浽�ı���
	}
}