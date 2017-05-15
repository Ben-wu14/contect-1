package test_Core;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import contectCore.Core;
import contectCore.Core.*;
import contectCore.FullList;
import contectCore.Person;
public class TestCore {
	public static void main(String[] args) {
		Core core = new Core();
		try {
			core.importFromvCard("vcards.vcf");
			FullList fullList=core.fullList;
			ArrayList<Person> list=fullList.getPersonList();
			for (int i=0;i<list.size();i++) {
				System.out.print("Name:"+list.get(i).getName()+" ");
				System.out.print("Tel:"+list.get(i).getTel()+" ");
				System.out.println("Phone:"+list.get(i).getPhoneNumber());
				
			}
		core.exportTovCard("outvcards.vcf");
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File("00001.vcf");
		System.out.print(file.getAbsolutePath());
	}
}
