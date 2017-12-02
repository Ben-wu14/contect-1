package test_Core;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import contectCore.Core;
import contectCore.Group;
import contectCore.Core.*;
import contectCore.Person;
public class TestCore {
	public static void main(String[] args) {
		Core core = new Core();
		try {
			//ʹ��vcard����
			core.importFromvCard("vcards.vcf");
			//��ʾ������ϵ�˲�����Ϣ�������Ƿ���¼��
			ArrayList<Person> list=core.fulllist.getList();
			for (int i=0;i<list.size();i++) {
				System.out.print("Name:"+list.get(i).getName()+" ");
				System.out.print("Tel:"+list.get(i).getTel()+" "+"Sex:"+list.get(i).getSex()+" ");
				System.out.println("Phone:"+list.get(i).getPhoneNumber());
			}
			//�����ʽ��ʾ���ݣ�����Ƿ���ɹ�
			for (Group group : core.groupList.getGroups()) {
				System.out.println(group.getListName());
				for (Person person : group.getList()) {
					System.out.println("	"+person.getName());
				}
			}
		core.exportTovCard("seoutvcards.vcf");
		//ʹ��CSV����͵���
		/*
		core.importFromCSV("read.csv");
		core.exportToCSV("write.csv");
		*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file = new File("00001.vcf");
		System.out.print(file.getAbsolutePath());
	}
}
