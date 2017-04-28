package test_Core;
import java.io.File;
import java.io.IOException;

import contectCore.Core;
import contectCore.Core.*;
public class TestCore {
	public static void main(String[] args) {
		Core core = new Core();
		try {
			core.importFromvCard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("00001.vcf");
		System.out.print(file.getAbsolutePath());
	}
}
