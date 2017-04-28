package contectCore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ezvcard.*;
import ezvcard.io.text.VCardReader;
import ezvcard.parameter.Encoding;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.Birthday;
import ezvcard.property.Email;
import ezvcard.property.FormattedName;
import ezvcard.property.Impp;
import ezvcard.property.Member;
import ezvcard.property.Note;
import ezvcard.property.Organization;
import ezvcard.property.Photo;
import ezvcard.property.Telephone;
import ezvcard.util.*;

public class Core implements Save{
	FullList fullList;
	String path;
	public Core() {
		// TODO Auto-generated constructor stub
	}
	public void importFromCSV(){
		//TODO 通过CSV文件导入数据
	}
	public void importFromvCard() throws IOException{
		//TODO 通过vCard文件导入数据
		File file = new File("00005.vcf");
		System.out.println(file.getAbsolutePath());
		VCardReader reader = new VCardReader(file);
		try {
		  VCard vcard;
		  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		  while ((vcard = reader.readNext()) != null) {
		    FormattedName fn = vcard.getFormattedName();
		    String name = (fn == null) ? null : fn.getValue();
		    //get the name 
		    
		    Birthday bday = vcard.getBirthday();
		    Date date = (bday == null) ? null : bday.getDate();
		    String birthday = (date == null) ? null : df.format(date);
		    System.out.print(name + " " + birthday+" ");
		    //get birthday
		    
		    
		    List<Telephone> tel=vcard.getTelephoneNumbers();
		    for (Telephone telephone : tel) {
				System.out.print(telephone.getTypes()+telephone.getText()+" ");
			}
		    //get phone number
		    
		    List<Address> addresses= vcard.getAddresses();
		    for (Address address : addresses) {
		    	print(address.getTypes()+address.getStreetAddressFull()+"邮编"+address.getPostalCode());
			}
		    //get the address
		    List<Organization> organizations = vcard.getOrganizations();
		    for (Organization organization : organizations) {
				print(organization.getValues());
			}
		    //get the company
		    List<Email> emails = vcard.getEmails();
		    for (Email email : emails) {
				print(email.getTypes()+email.getValue());
			}
		    //get email
		    List<Note> notes = vcard.getNotes();
		    for (Note note : notes) {
				print(note.getValue());
			}
		    System.out.println();
		    //get notes
		    List<Photo>photos=vcard.getPhotos();
		    for (Photo photo : photos) {
				//get the photo
			}
		    
		    System.out.println();
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
	public void exportTovCard(){
		//TODO 以vCard文件输出
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
