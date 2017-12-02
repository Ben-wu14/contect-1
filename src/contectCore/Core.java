package contectCore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import ezvcard.*;
import ezvcard.io.text.VCardReader;
import ezvcard.io.text.VCardWriter;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.Encoding;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Birthday;
import ezvcard.property.Email;
import ezvcard.property.FormattedName;
import ezvcard.property.Gender;
import ezvcard.property.Impp;
import ezvcard.property.Kind;
import ezvcard.property.Member;
import ezvcard.property.Note;
import ezvcard.property.Organization;
import ezvcard.property.Photo;
import ezvcard.property.RawProperty;
import ezvcard.property.Telephone;
import ezvcard.util.*;

public class Core implements Save{
	public GroupList groupList = new GroupList();
	public Group fulllist= groupList.getGroups().get(0);
	String path;
	public Core() {
		// TODO Auto-generated constructor stub
	}
	//导入导出地址path均为相对地址
	public void importFromCSV(String path) throws IOException{
		//TODO 通过CSV文件导入数据
		File file = new File(path);
		CSVReader csvReader = null;
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		csvReader = new CSVReader(new InputStreamReader(in,"gbk"));
		String [] nextLine;
	     while ((nextLine = csvReader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	 Person person = new Person(nextLine[0]);
	    	 
	    	 try {
	    		 person.setSex(nextLine[1]);
	    		 person.setPhoneNumber(nextLine[3]);
		    	 person.setTel(nextLine[4]);
		    	 person.setCompany(nextLine[5]);
		    	 person.setPostCode(nextLine[6]);
		    	 person.setNote(nextLine[7]);
		    	 person.setAddress(nextLine[8]);
		    	 String birth = nextLine[9];
		    	 String[]birtime= birth.split(",");
		    	 if(!birth.equals("")&&birth!=null){
		    		 person.setBirthday(new Time(Integer.parseInt(birtime[2]), Integer.parseInt(birtime[1]), Integer.parseInt(birtime[0])));
		    	 }
		    	 person.setEmail(nextLine[10]);
		    	 person.setInstantContect(nextLine[11]);
		    	 String[] strings=nextLine[12].split("/");
		    	 ArrayList<String> liString=new ArrayList<>(Arrays.asList(nextLine[12].split("/")));
		    	 if(liString.get(0).equals("")){
		    		 liString.remove(0);
		    	 }
		    	 liString.add(0,"所有联系人");
		    	 person.setGroups(liString);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
	    	 fulllist.addPerson(person);
	     }
	     csvReader.close();
	     groupList.generateAllGroup();
	     System.out.println("Finished inport csv in path: "+path);
	}
	public void importFromvCard(String path) throws IOException{
		//TODO 通过vCard文件导入数据
		File file = new File(path);
		System.out.println(file.getAbsolutePath());
		VCardReader reader = new VCardReader(file);
		try {
		  ArrayList<Object> group=new ArrayList<>();
		  VCard vcard;
		  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		  while ((vcard = reader.readNext()) != null) {
			
			 ArrayList<String> strings=new ArrayList<>();
			 FormattedName fn = vcard.getFormattedName();
			 String name = (fn == null) ? null : fn.getValue();
			 Kind kind = vcard.getKind();
	    	
	    	if (kind != null){
	    	  if (kind.isGroup()){
	    		 
	  			System.out.println(kind.getGroup());
	    	    System.out.println(name+"'s members are:");
	    	   for (Member member : vcard.getMembers()){
	    	     strings.add(member.getUri());
	    	     System.out.print(" "+member.getUri());
	    	     
	    	    }
	    	   group.add(strings);
	    	   groupList.newGroup(name);
		       continue;
	    	  }
	    	}
	    	
		    
		    if(name!=null){
		    	System.out.print(fn.getValue());
		    	Person person = new Person(name);
		    	//get the name 
		    	Gender gender=vcard.getGender();
		    	if(gender!=null){
		    		if(gender.isMale()){
		    			person.setSex("male");
		    		}else if(gender.isFemale()){
		    			person.setSex("female");
		    		}
		    	}
		    	//get gender
		    
		    
		    Birthday bday = vcard.getBirthday();
		    Date date = (bday == null) ? null : bday.getDate();
		    if(date!=null){
		    	Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    String birthday = (date == null) ? null : df.format(date);
			    System.out.print(person.getName()  +" "+person.getPhoneticize()+" ");
			    
			    String[] liStrings=birthday.split("/");
			    
			    if (liStrings != null) {
			      //property value is a partial date
			      int year = Integer.parseInt(liStrings[2]);
			      int month = Integer.parseInt(liStrings[0]);
			      int day = Integer.parseInt(liStrings[1]);
			      Time time= new Time(year, month, day);
			      person.setBirthday(time);
			      System.out.print(person.getBirthday().toString());
			    }
		    }
		    
		    
		    
		    
		    
		    
		    //get birthday
		    
		    
		    List<Telephone> tel=vcard.getTelephoneNumbers();
		    if(tel!=null)
		    for (Telephone telephone : tel) {
		    	List<TelephoneType> types=telephone.getTypes();
		    	String text=telephone.getText();
				System.out.print(types+" ");
				
				if(types.get(0)==TelephoneType.CELL){
					person.setPhoneNumber(text);
					System.out.print(person.getPhoneNumber());
				}else if(types.get(0)==TelephoneType.HOME){
					person.setTel(text);
					System.out.print(person.getTel());
				}
			}
		    
		    
		    //get phone number
		    
		    List<Address> addresses= vcard.getAddresses();
		    if(addresses!=null)
		    for (Address address1 : addresses) {
		    	for (Address address : addresses) {
					if(address.getTypes().get(0)==AddressType.HOME){
		    		person.setAddress(address.getStreetAddressFull());
		    		person.setPostCode(address.getPostalCode());
					}
					print(address.getTypes()+person.getAddress()+"邮编"+person.getPostCode());
				}
			}
		    //get the address
		    List<Organization> organizations = vcard.getOrganizations();
		    if(organizations!=null)
		    for (Organization organization : organizations) {
		    	if(organization!=null){
		    		List<String> strings2=organization.getValues();
		    		String result="";
		    		for (String string : strings2) {
						String temp=string.replace("[", "");
						result+=temp.replace("]", "");
					}
		    		if(!result.equals(""))
		    		person.setCompany(result);
		    		
		    	}
				print(person.getCompany());
			}
		    //get the company
		    List<Email> emails = vcard.getEmails();
		    if(emails!=null)
		    for (Email email : emails) {
				print(email.getTypes());
				if(email!=null){
					person.setEmail(email.getValue());
					print(person.getEmail());
				}
			}
		    //get email
		    List<Note> notes = vcard.getNotes();
		    if(notes!=null)
		    for (Note note : notes) {
				if(note!=null){
					person.setNote(note.getValue());
					print(person.getNote());
				}
			}
		    System.out.println();
		    //get notes
		    List<Photo>photos=vcard.getPhotos();
		    int fileCount = 0;
		    for (Photo photo : photos) {
				//get the photo
		    	 //the photo will have either a URL or a binary data
		    	if (photo.getData() == null){
		    	    System.out.println("Photo URL: " + photo.getUrl());
		    	  } else {
		    	    ImageType type = photo.getContentType();
		    	     
		    	    if (type == null) {
		    	      //the vCard may not have any content type data associated with the photo
		    	      System.out.println("Saving a photo file...");
		    	    } else {
		    	      System.out.println("Saving a \"" + type.getMediaType() + "\" file...");
		    	    }
		    	    String bin="bin/";
		    	    String folder;
		    	    if (type == ImageType.JPEG){ //it is safe to use "==" instead of "equals()"
		    	      folder = "photos";
		    	    } else {
		    	      folder = "images";
		    	    }
		    	    
		    	    byte data[] = photo.getData();
		    	    String filename = name + fileCount;
		    	    if (type != null && type.getExtension() != null){
		    	       filename += "." + type.getExtension();
		    	    }
		    	    OutputStream out = new FileOutputStream(new File(bin+folder, filename));
		    	    out.write(data);
		    	    out.close();
		    	    fileCount++;
		    	    person.setPicture(folder+"/"+filename);
		    	  }
		    }
		    System.out.println();
		    //not complete
		    RawProperty prop = vcard.getExtendedProperty("X-QQ");
		    if(prop!=null){
			    person.setInstantContect(prop.getValue());
			    System.out.println("QQ: " + person.getInstantContect());
		    }
		    	System.out.println();
		    	
		    	
		    fulllist.addPerson(person);
		    }
		  }
		  if(group!=null){
			  for (int i=0;i<group.size();i++) {
				  ArrayList<String> nameList=(ArrayList<String>)group.get(i);
				  for (String string : nameList) {
					groupList.getGroups().get(i+1).addPerson(fulllist.findPersonByName(string));
					//在fullist中找到对应的person加入到group中
				}
			}
		  }
		} finally {
		  reader.close();
		  System.out.println("Finished import vcard in path: "+path);
		}
	}
	public void importFromFile() throws IOException{
		//TODO 通过文本文件导入数据
		importFromvCard("original.vcf");
	}
	public void exportToCSV(String path) throws IOException{
		//TODO 以CSV文件输出
		File file = new File(path);  
		  
	        
	        DataOutputStream in = new DataOutputStream(new FileOutputStream(file));
	        CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(in,"gbk"), ',');  
	        for (Person person : fulllist.getList()) {
	        String[] strs = person.stringlist();
	        csvWriter.writeNext(strs);
	        }
	        csvWriter.close();
	        System.out.println("Finished export csv in path: "+path);
	}
	public void exportTovCard(String path){
		//TODO 以vCard文件输出
		File file = new File(path);
		VCardWriter writer=null;
		try {
			 writer = new VCardWriter(file, VCardVersion.V3_0);
		for (Person person : fulllist.getList()) {
			VCard vCard=  new VCard();
			//name
			vCard.setNickname(person.getName());
			vCard.setFormattedName(person.getName());
			//gender
			if(person.getSex()!=null){
				String sex=Gender.UNKNOWN;
				if(person.getSex().equals("male")){
					sex=Gender.MALE;
				}else if(person.getSex().equals("female")){
					sex=Gender.FEMALE;
				}
				RawProperty rawProperty = new RawProperty("GENDER",sex);
				vCard.addProperty(rawProperty);
			}
			
			//birthday
			if(person.getBirthday()!=null){
				Calendar c = Calendar.getInstance();
				c.clear();
				c.set(Calendar.YEAR, person.getBirthday().getYear());
				c.set(Calendar.MONTH, person.getBirthday().getMonth());
				c.set(Calendar.DAY_OF_MONTH, person.getBirthday().getDay());
				Birthday bday = new Birthday(c.getTime());
				vCard.setBirthday(bday);
			}
			
			//number
			if (person.getTel()!=null) {
				Telephone telephone = new Telephone(person.getTel());
				telephone.getTypes().add(TelephoneType.HOME);
				vCard.addTelephoneNumber(telephone);
			}
			if(person.getPhoneNumber()!=null){
				Telephone telephone = new Telephone(person.getPhoneNumber());
				telephone.getTypes().add(TelephoneType.CELL);
				vCard.addTelephoneNumber(telephone);
			}
			
			//address&&post code
			//-------------------------------
			//test
			//person.setPostCode("123432");
			
			//------------------------------
			
			if((person.getPostCode()!=null)||(person.getAddress()!=null)){
				Address adr = new Address();
				if((person.getAddress()!=null))
				adr.setStreetAddress(person.getAddress());
				if((person.getPostCode()!=null))
				adr.setPostalCode(person.getPostCode());
				adr.getTypes().add(AddressType.HOME);
				vCard.addAddress(adr);
			}
			
			//company
			if(person.getCompany()!=null&&!person.getCompany().equals("")){
				Organization org = new Organization();
				org.getValues().add(person.getCompany());
				vCard.setOrganization(org);
			}
			
			
			//email
			if(person.getEmail()!=null){
				Email email = new Email(person.getEmail());
				email.getTypes().add(EmailType.HOME);
				vCard.addEmail(email);
			}
			//note
			if(person.getNote()!=null){
				Note note = new Note(person.getNote());
				vCard.addNote(note);
			}
			//photo
			if(person.getPicture()!=null){
				Path path2 = Paths.get("bin/"+person.getPicture());
				byte[] data;
				try {
					data = Files.readAllBytes(path2);
					Photo photo = new Photo(data, ImageType.JPEG);
					vCard.addPhoto(photo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//imps
			if(person.getInstantContect()!=null){
				RawProperty rawProperty = new RawProperty("X-QQ",person.getInstantContect());
				vCard.addProperty(rawProperty);
			}
			//group
			
			writer.write(vCard);
		}
		for (int i=0;i<groupList.getGroups().size()-1;i++) {
			VCard vCard=  new VCard();
			RawProperty rawProperty2 = new RawProperty("KIND","group");
			vCard.addProperty(rawProperty2);
			Group group=groupList.getGroups().get(i+1);
			vCard.setFormattedName(group.getListName());
			for (Person person : group.getList()) {
				RawProperty rawProperty = new RawProperty("MEMBER",person.getName());
				vCard.addProperty(rawProperty);
			}
			
			writer.write(vCard);
		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			  try {
				writer.close();
				System.out.println("Finished export vcard in path: "+path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		exportTovCard("original.vcf");
	}
	public static<E> void print(E e){
		System.out.print(e);
	}
	public static void priret(){
		System.out.println();
	}
}
