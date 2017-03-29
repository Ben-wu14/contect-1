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
	 // TODO 创建新的组到groups中
	      groups.add(group);
	}
	public void removeGroup(Group delete){
		//TODO 从groups中删除delete组别
		groups.remove(delete);
	}
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
				groups.remove(i);
		}
	}
	}
	@Override
	public void saveInfor() {
		// TODO 把所有的组别信息保存到文本中
	}
}