package contectCore;

import java.awt.List;
import java.util.ArrayList;

public class Group {
	private ArrayList<Person>list;
	private String listName;
	public Group(String listName) {
		// TODO Auto-generated constructor stub
		this.listName=listName;
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
	
	
	public void addPerson(Person newPerson){
		//TODO 加入联系人到组别中
	}
	public void deletePerson(Person deletePerson){
		//TODO 从组别中删除联系人
	}
	public void addListOfPeople(List listOfperson){
		//TODO 把列表中的人全部加入到组里
	}
	public void deleteListOfPeople(List listOfpeople){
		//TODO 把列表里的人全部从组里删除
	}
}
