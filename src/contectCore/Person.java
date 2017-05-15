package contectCore;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

public class Person implements Save{
	private String name;
	private String phoneticize;
	private String midfPontic;
	private String shortPontic;
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
		operatePhoneticize(name);
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
		String[] number=phoneNumber.split(" ");
		String result="";
		for (String string : number) {
			result+=string;
		}
		this.phoneNumber = result;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		String[] number=tel.split(" ");
		String result="";
		for (String string : number) {
			result+=string;
		}
		this.tel = result;
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
	public String getShortPontic() {
		return shortPontic;
	}
	public void setShortPontic(String shortPontic) {
		this.shortPontic = shortPontic;
	}
	public String getMidfPontic() {
		return midfPontic;
	}
	public void setMidfPontic(String midfPontic) {
		this.midfPontic = midfPontic;
	}
	//把联系人加入列表ListOfGroup中所有组别中
	public void addToGroups(List<Group> list){
				for (Group group : list){
					group.addPerson(this);
				}
	}
	public void operatePhoneticize(String name){
		//输入用户名字，输出汉语拼音（吴---wu2）,如果不是汉语，则不改变字符
		StringBuilder pyBuilder=new StringBuilder();
		StringBuilder stBuilder=new StringBuilder();
		StringBuilder mdBuilder=new StringBuilder();
		String temp=null;
		for (int i = 0; i < name.length(); i++) {
			temp=operateChar(name.charAt(i));
			if(temp!=null){
				pyBuilder.append(temp);
				stBuilder.append(temp.charAt(0));
				mdBuilder.append(temp.substring(0, temp.length()-1));
			}else{
				if (Character.isUpperCase(name.charAt(i))) {
					pyBuilder.append(Character.toLowerCase(name.charAt(i)));
					stBuilder.append(Character.toLowerCase(name.charAt(i)));
					mdBuilder.append(Character.toLowerCase(name.charAt(i)));
				} else {
					pyBuilder.append(name.charAt(i));
					stBuilder.append(name.charAt(i));
					mdBuilder.append(name.charAt(i));
				}
				
			}
		}
		this.shortPontic=stBuilder.toString();
		this.phoneticize=pyBuilder.toString();
		this.midfPontic=mdBuilder.toString();
	}
	private String operateChar(char c){
		//单个字符转换，辅助operatePhoneticize的实现
		String[] pStrings=PinyinHelper.toHanyuPinyinStringArray(c);
		if(pStrings==null)return null;
		else return pStrings[0];
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		
	}
}
