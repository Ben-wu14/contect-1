package contectCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	public FullList fullList = new FullList();
	String path;
	public Core() {
		// TODO Auto-generated constructor stub
	}
	public void importFromCSV(){
		//TODO 通过CSV文件导入数据
	}
	public void importFromvCard(String path) throws IOException{
		//TODO 通过vCard文件导入数据
		File file = new File(path);
		System.out.println(file.getAbsolutePath());
		VCardReader reader = new VCardReader(file);
		FullList list = new FullList();
		try {
		  VCard vcard;
		  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		  while ((vcard = reader.readNext()) != null) {
			
			  
		    FormattedName fn = vcard.getFormattedName();
		    String name = (fn == null) ? null : fn.getValue();
		    
		    Person person=null;
		    if(name!=null){
		    	System.out.print(fn.getValue());
		    	person = new Person(name);
		    }
		    
		    //get the name 
		    
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
		    		person.setCompany(String.valueOf(organization.getValues()));
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
		    	    OutputStream out = new FileOutputStream(new File(folder, filename));
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
		    	
		    	
		    
		    	Kind kind = vcard.getKind();
		    	
		    	if (kind != null){
		    	  if (kind.isGroup()){
		    	    System.out.println("The group's members are:");
		    	    for (Member member : vcard.getMembers()){
		    	      System.out.println(member.getUri());
		    	    }
		    	  }
		    	}
		    	
		    List<Member> members=vcard.getMembers();
		    if(members!=null)
		    for (Member member : members) {
		    	System.out.print(member.getValue());
		    	list.groupList.newGroup(new Group(member.getValue()));
			}
		    
		    fullList.addNewPerson(person);
		  }
		} finally {
		  reader.close();
		}
	}
	public void importFromFile(){
		//TODO 通过文本文件导入数据
	}
	public void exportToCSV(){
		//TODO 以CSV文件输出
	}
	public void exportTovCard(String path){
		//TODO 以vCard文件输出
		File file = new File(path);
		VCardWriter writer=null;
		try {
			 writer = new VCardWriter(file, VCardVersion.V3_0);
		for (Person person : fullList.personList) {
			VCard vCard=  new VCard();
			//name
			vCard.setNickname(person.getName());
			vCard.setFormattedName(person.getName());
			
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
				telephone.getTypes().add(TelephoneType.HOME);
				vCard.addTelephoneNumber(telephone);
			}
			
			//address&&post code
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
			if(person.getCompany()!=null){
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
				Path path2 = Paths.get(person.getPicture());
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
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			  try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void saveInfor() {
		// TODO 把内容保存至文本中
		
	}
	public static<E> void print(E e){
		System.out.print(e);
	}
	public static void priret(){
		System.out.println();
	}
}
