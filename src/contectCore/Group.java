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
	//getter �� setter
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
	
	//����һ���µ���ϵ�˵�������棬�Զ�����Ƿ��Ѿ����ڣ���������ֱ���˳���������뵽���ﲢ�Ұ�Person���������б����
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
		
		//TODO ������ϵ�˵������
	}
	//ɾ�����ڵ���ϵ��ͬʱ��person���������б����
	public void deletePerson(Person deletePerson){
		//TODO �������ɾ����ϵ��
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
	//����listOfperson��������˼��뵽���ڵ�����
	public void addListOfPeople(ArrayList<Person> listOfperson){
		for (Person person : listOfperson)
			addPerson(person);
		//TODO ���б��е���ȫ�����뵽����
	}
	//��һ������ڵ��������˼��뵽���ڵ�����
	public void addGroupOfPeople(Group group) {
		for (Person person : group.list)
			addPerson(person);
		//TODO ����������ڵ���ϵ����ӵ����ڵ��������
	}
	//ɾ��listOfpeople�����������
	public void deleteListOfPeople(ArrayList<Person> listOfpeople){
		//TODO ���б������ȫ��������ɾ��
		for (int i = 0; i < listOfpeople.size(); i++) {
			deletePerson(listOfpeople.get(i));
		}
	}
	//�ҳ�������ϵ�˵��±�λ��
	public int insertPlace(Person person){
		//���ز���λ�õĺ�һ��λ
		//����pointer����������ͷ
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
		//TODO ͨ���绰��������
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
		//TODO ͨ��������������
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
		//TODO ͨ��ƴ�����У�ͬʱ֧��ͨ����ĸ����
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
		ArrayList<Person> temp=new ArrayList<>(list1);//�����������߹�ͬ�е�����
		temp.retainAll(list2);//temp��ֻ�������߹�ͬ������
		list1.removeAll(temp);//l1��ȥ�����߹�ͬ�е�����
		ArrayList<Person> l3=new ArrayList<>();
		l3.addAll(list1);
		l3.addAll(list2);
		return l3;
	}
	
	//ģ������
	public ArrayList<Person> searchByString(String string) {
		ArrayList<Person> list1=searchByName(string);
		ArrayList<Person> list2=searchByPhone(string);
		ArrayList<Person> list3=searchByPhoneticize(string);
		ArrayList<Person> temp=mergeList(list1, list2);
		ArrayList<Person> result= mergeList(temp,list3);
		return result;
	}
	//�ҳ���Ӧ���ֵ���ϵ��
	public Person findPersonByName(String Name){
		for (Person person : list) {
			if(person.getName().equals(Name)){
				return person;
			}
		}
		return null;
	}
	//������ϣ����������ϵ�ˡ��޼���Ƿ��ظ���ϵ�ˡ���������Person������¼��
	public void addNewPersonWithPointer(Person person){
		//TODO ����µ���ϵ��
		int pos = insertPlace(person);
		for (int i = pointer+1; i < header.length; i++) {
			header[i]++;
			//�ƶ����в���λ�������ָ��
		}
		list.add(pos, person);
	}
	//ɾ����ϵ�˲����ڹ�ϣ��������Person������¼��
	public void removePersonWithPointer(Person person){
		//TODO ɾ����ϵ��
		int pos = list.indexOf(person);
		if(pos>=0){//������ڶ���
			list.remove(pos);
			for (int i = 0; i < header.length; i++) {
				if(header[i]>pos)header[i]--;//����ɾ������λ�������ָ��
			}
		}
		
	}
	@Override
	public void saveInfor() {
		// TODO �����ݱ������ı���
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return listName;
	}
}
