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
		//TODO ͨ���绰��������
		List<Person> list = new ArrayList<>();
		for (Person person : personList) {
			int index=0;
			if(person.getPhoneNumber()!=null){
				index+=person.getPhoneNumber().indexOf(phoneNumber);
			}else index+=-1;
			if(person.getTel()!=null){
				index+=person.getTel().indexOf(phoneNumber);
			}else index+=-1;
			if(index!=-2){
				list.add(person);
			}
		}
		return list;
	}
	public List<Person> searchByName(String Name){
		//TODO ͨ��������������
		List<Person> list = new ArrayList<>();
		for (Person person : personList) {
			int index=person.getName().indexOf(Name);
			if(index!=-1){
				list.add(person);
			}
		}
		return list;
	}
	public List<Person> searchByPhoneticize(String phoneticize){
		//TODO ͨ��ƴ�����У�ͬʱ֧��ͨ����ĸ����
		List<Person> list = new ArrayList<>();
		for (Person person : personList) {
			int index=person.getPhoneticize().indexOf(phoneticize);
			index+=person.getShortPontic().indexOf(phoneticize);
			index+=person.getMidfPontic().indexOf(phoneticize);
			if(index!=-3){
				list.add(person);
			}
		}
		return list;
	}
	public void addNewPerson(Person person){
		//TODO ����µ���ϵ��
		int pos = insertPlace(person);
		for (int i = pointer+1; i < header.length; i++) {
			header[i]++;
			//�ƶ����в���λ�������ָ��
		}
		personList.add(pos, person);
	}
	public void removePerson(Person person){
		//TODO ɾ����ϵ��
		int pos = personList.indexOf(person);
		if(pos>=0){//������ڶ���
			personList.remove(pos);
			for (int i = 0; i < header.length; i++) {
				if(header[i]>pos)header[i]--;//����ɾ������λ�������ָ��
			}
		}
		
	}
	public int insertPlace(Person person){
		//���ز���λ�õĺ�һ��λ
		//����pointer����������ͷ
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
		// TODO �����ݱ������ı���
		
	}
}
