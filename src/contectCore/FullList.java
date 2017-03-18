package contectCore;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.sparta.xpath.PositionEqualsExpr;

public class FullList implements Save{
	GroupList groupList;
	int[] header=new int[27];
	int pointer=0;
	ArrayList<Person> personList = new ArrayList<>();
	public GroupList getGroupList() {
		return groupList;
	}
	public void setGroupList(GroupList groupList) {
		this.groupList = groupList;
	}
	public int[] getHeader() {
		return header;
	}
	public void setHeader(int[] header) {
		this.header = header;
	}
	public ArrayList<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(ArrayList<Person> personList) {
		this.personList = personList;
	}
	public FullList() {
		// TODO Auto-generated constructor stub
	}
	public List<Person> searchByPhone(String phoneNumber){
		//TODO 通过电话号码排列
		return null;
	}
	public List<Person> searchByTel(String tel){
		//TODO 通过座机号码排列
		return null;
	}
	public List<Person> searchByName(String Name){
		//TODO 通过姓名进行排列
		return null;
	}
	public List<Person> searchByPhoneticize(String phoneticize){
		//TODO 通过拼音排列，同时支持通过声母排列
		return null;
	}
	public void addNewPerson(Person person){
		//TODO 添加新的联系人
		int pos = insertPlace(person);
		for (int i = pointer+1; i < header.length; i++) {
			header[i]++;
		}
		personList.add(pos, person);
	}
	public void removePerson(Person person){
		//TODO 删除联系人
		int pos = personList.indexOf(person);
		personList.remove(pos);
		for (int i = pointer+1; i < header.length; i++) {
			header[i]--;
		}
	}
	public int insertPlace(Person person){
		char first=person.getPhoneticize().charAt(0);
		String phoneticize=person.getPhoneticize();
		boolean isLetter = Character.isLetter(first);
		int index=26;
		int endPos=personList.size();
		if(isLetter){
			first=Character.toLowerCase(first);
			index=first-'a';
			endPos=header[index+1];
		}
		pointer=index;
		int i;
		for(i=header[index];i<endPos;i++){
			if(person.getPhoneticize().compareTo(personList.get(i).getPhoneticize())<0){
				break;
			}
		}
		return i;
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		
	}
}
