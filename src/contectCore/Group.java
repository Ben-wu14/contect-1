package contectCore;

import java.util.List;
import java.util.ArrayList;

public class Group implements Save{
	private ArrayList<Person>list;
	private String listName;
	int[] header=new int[27];
	int pointer=0;
	//getter and setter
	public int[] getHeader() {
		return header;
	}
	public void setHeader(int[] header) {
		this.header = header;
	}
	public int getPointer() {
		return pointer;
	}
	public void setPointer(int pointer) {
		this.pointer = pointer;
	}
	
	public Group(String listName) {
		// TODO Auto-generated constructor stub
		this.listName=listName;
		list=new ArrayList<>();
	}
	public Group(String listName,ArrayList<Person>list) {
		// TODO Auto-generated constructor stub
		this.list=list;
		this.listName=listName;
	}
	//getter 和 setter
	public ArrayList<Person> getList() {
		return list;
	}
	public void setList(ArrayList<Person> list) {
		this.list = list;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	//getter and setter
	
	//加入一个新的联系人到组别里面，自动检测是否已经存在，若存在则直接退出，否则加入到组里并且把Person里面的组别列表更新
	public void addPerson(Person newPerson){
		
		boolean same=false;
		for (Person person : list) {
			if(person.getName().equals(newPerson.getName())){
				same=true;
			}
		}
		//check if the person exit in the group
		if(!same){
			addNewPersonWithPointer(newPerson);
			newPerson.addGroupString(this.listName);//update the person's group_list
		}
		
		//TODO 加入联系人到组别中
	}
	//删除组内的联系人同时把person里面的组别列表更新
	public void deletePerson(Person deletePerson){
		//TODO 从组别中删除联系人
		for (int i = 0; i < list.size(); i++) {
			int isSame=list.get(i).getName().compareTo(deletePerson.getName());
			if(isSame==0){
				//remove from the person list
				list.get(i).removeGroupString(listName);
				//remove from the group list
				removePersonWithPointer(list.get(i));
			}
		}
	}
	//加入listOfperson里的所有人加入到现在的组中
	public void addListOfPeople(ArrayList<Person> listOfperson){
		for (Person person : listOfperson)
			addPerson(person);
		//TODO 把列表中的人全部加入到组里
	}
	//把一个组别内的所有人人加入到现在的组中
	public void addGroupOfPeople(Group group) {
		for (Person person : group.list)
			addPerson(person);
		//TODO 把已有组别内的联系人添加到现在的组别里面
	}
	//删除listOfpeople里面的所有人
	public void deleteListOfPeople(ArrayList<Person> listOfpeople){
		//TODO 把列表里的人全部从组里删除
		for (int i = 0; i < listOfpeople.size(); i++) {
			deletePerson(listOfpeople.get(i));
		}
	}
	//找出插入联系人的下标位置
	public int insertPlace(Person person){
		//返回插入位置的后一个位
		//更新pointer到插入的组别开头
		char first=person.getPhoneticize().charAt(0);
		String phoneticize=person.getPhoneticize();
		boolean isLetter = Character.isLetter(first);
		int index=26;
		int endPos=list.size();
		if(isLetter){
			first=Character.toLowerCase(first);
			index=first-'a';
			endPos=header[index+1];
		}
		pointer=index;
		int i;
		for(i=header[index];i<endPos;i++){
			if(person.getPhoneticize().compareTo(list.get(i).getPhoneticize())<0){
				break;
			}
		}
		return i;
	}
	public ArrayList<Person> searchByPhone(String phoneNumber){
		//TODO 通过电话号码排列
		ArrayList<Person> resultlist = new ArrayList<>();
		for (Person person : list) {
			int index=0;
			if(person.getPhoneNumber()!=null){
				index+=person.getPhoneNumber().indexOf(phoneNumber);
			}else index+=-1;
			if(person.getTel()!=null){
				index+=person.getTel().indexOf(phoneNumber);
			}else index+=-1;
			if(index!=-2){
				resultlist.add(person);
			}
		}
		return resultlist;
	}
	public ArrayList<Person> searchByName(String Name){
		//TODO 通过姓名进行排列
		ArrayList<Person> resultlist = new ArrayList<>();
		for (Person person : list) {
			int index=person.getName().indexOf(Name);
			if(index!=-1){
				resultlist.add(person);
			}
		}
		return resultlist;
	}
	public ArrayList<Person> searchByPhoneticize(String phoneticize){
		//TODO 通过拼音排列，同时支持通过声母排列
		ArrayList<Person> resultlist = new ArrayList<>();
		for (Person person : list) {
			int index=person.getPhoneticize().indexOf(phoneticize);
			index+=person.getShortPontic().indexOf(phoneticize);
			index+=person.getMidfPontic().indexOf(phoneticize);
			if(index!=-3){
				resultlist.add(person);
			}
		}
		return resultlist;
	}
	public ArrayList<Person> mergeList(ArrayList<Person>list1,ArrayList<Person>list2){
		ArrayList<Person> temp=new ArrayList<>(list1);//用来保存两者共同有的数据
		temp.retainAll(list2);//temp中只保留两者共同的数据
		list1.removeAll(temp);//l1中去掉两者共同有的数据
		ArrayList<Person> l3=new ArrayList<>();
		l3.addAll(list1);
		l3.addAll(list2);
		return l3;
	}
	
	//模糊搜索
	public ArrayList<Person> searchByString(String string) {
		ArrayList<Person> list1=searchByName(string);
		ArrayList<Person> list2=searchByPhone(string);
		ArrayList<Person> list3=searchByPhoneticize(string);
		ArrayList<Person> temp=mergeList(list1, list2);
		ArrayList<Person> result= mergeList(temp,list3);
		return result;
	}
	//找出对应名字的联系人
	public Person findPersonByName(String Name){
		for (Person person : list) {
			if(person.getName().equals(Name)){
				return person;
			}
		}
		return null;
	}
	//调整哈希表后加入新联系人【无检测是否重复联系人】【不更新Person的组别记录表】
	public void addNewPersonWithPointer(Person person){
		//TODO 添加新的联系人
		int pos = insertPlace(person);
		for (int i = pointer+1; i < header.length; i++) {
			header[i]++;
			//移动所有插入位置往后的指针
		}
		list.add(pos, person);
	}
	//删除联系人并调节哈希表【不更新Person的组别记录表】
	public void removePersonWithPointer(Person person){
		//TODO 删除联系人
		int pos = list.indexOf(person);
		if(pos>=0){//如果存在对象
			list.remove(pos);
			for (int i = 0; i < header.length; i++) {
				if(header[i]>pos)header[i]--;//修正删除插入位置往后的指针
			}
		}
		
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return listName;
	}
}
