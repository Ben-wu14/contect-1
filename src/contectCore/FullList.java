package contectCore;

import java.util.ArrayList;
import java.util.List;

public class FullList {
	GroupList groupList;
	ArrayList<Person> personList;
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
}
