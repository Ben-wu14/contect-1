package contectCore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import ezvcard.parameter.ImageType;
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
		//TODO ͨ��CSV�ļ���������
	}
	public void importFromvCard() throws IOException{
		//TODO ͨ��vCard�ļ���������
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
		    	print(address.getTypes()+address.getStreetAddressFull()+"�ʱ�"+address.getPostalCode());
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
			}
		    }
		    System.out.println();
		  }
		} finally {
		  reader.close();
		}
	}
	public void importFromFile(){
		//TODO ͨ���ı��ļ���������
	}
	public void exportToCSV(){
		//TODO ��CSV�ļ����
	}
	public void exportTovCard(){
		//TODO ��vCard�ļ����
	}
	@Override
	public void saveInfor() {
		// TODO �����ݱ������ı���
		
	}
	public static<E> void print(E e){
		System.out.print(e);
	}
	public static void priret(){
		System.out.println();
	}
}
