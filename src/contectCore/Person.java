package contectCore;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Person implements Save{
	private String name;
	private String phoneticize;
	private String phoneNumber;
	private String tel;
	private String company;
	private String postCode;
	private String note;
	private String address;
	private String picture;
	private Time birthday;
	private String email;
	private String instantContect;
	//Constructor
	public Person(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		setPhoneticize(operatePhoneticize(name));
	}
	//各项内容的get和set方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Time getBirthday() {
		return birthday;
	}
	public void setBirthday(Time birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInstantContect() {
		return instantContect;
	}
	public void setInstantContect(String instantContect) {
		this.instantContect = instantContect;
	}
	public String getPhoneticize() {
		return phoneticize;
	}
	public void setPhoneticize(String phoneticize) {
		this.phoneticize = phoneticize;
	}
	//把联系人加入列表ListOfGroup中所有组别中
	public void addToGroups(List<Group> list){
				for (Group group : list){
					group.addPerson(this);
				}
	}
	public String operatePhoneticize(String name){
		StringBuilder pyBuilder=new StringBuilder();
		String temp=null;
		for (int i = 0; i < name.length(); i++) {
			temp=operateChar(name.charAt(i));
			if(temp!=null){
				pyBuilder.append(temp);
			}else{
				pyBuilder.append(name.charAt(i));
			}
		}
		return pyBuilder.toString();
	}
	public String operateChar(char c){
		String[] pStrings=PinyinHelper.toHanyuPinyinStringArray(c);
		if(pStrings==null)return null;
		else return pStrings[0];
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		
	}
}
