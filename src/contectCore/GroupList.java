package contectCore;

import java.awt.List;
import java.util.ArrayList;

public class GroupList implements Save{
	ArrayList<Group> groups=new ArrayList<>();
	public GroupList() {
		// TODO Auto-generated constructor stub
	}
	public void newGroup(){
	 // TODO 创建新的组到groups中	
	}
	public void removeGroup(Group delete){
		//TODO 从groups中删除delete组别
	}
	public void removeListOfGroup(List list){
		//TODO 删除list中列出的所有组别
	}
	@Override
	public void saveInfor() {
		// TODO 把所有的组别信息保存到文本中
		
	}
}
