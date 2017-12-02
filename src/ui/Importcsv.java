package ui;
import java.io.File;
import java.io.IOException;

import contectCore.Core;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

 class Importcsv implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		File file2=new File("bin/photos");
		String[]pictures= file2.list();
		for (String string : pictures) {
			
			boolean su=new File("bin/photos/"+string).delete();
			System.out.println(su);
		}
		file2=new File("bin/images");
		String[]pictures2= file2.list();
		for (String string : pictures2) {
			new File("bin/images/"+string).delete();
		}
		Stage stage = new Stage();
		MainPage.core=new Core();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
		fileChooser.setInitialDirectory(new File("./"));
	    fileChooser.setTitle("Open Resource File");
	    File file =fileChooser.showOpenDialog(stage);
	    MainPage.filePath=file.getPath();
		try {
			MainPage.core.importFromCSV(file.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   //¥”vCardµº»Î
	}

}
