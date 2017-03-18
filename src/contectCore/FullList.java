package contectCore;

import java.util.ArrayList;
import java.util.List;

public class FullList implements Save{
	GroupList groupList;
	int[] header=new int[27];
	ArrayList<Person> personList = new ArrayList<>();
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
	}
	public void removePerson(Person person){
		//TODO 删除联系人
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
		int i;
		for(i=header[index];i<endPos;i++){
			if(person.getPhoneticize().compareTo(personList.get(i).getPhoneticize())>0){
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
