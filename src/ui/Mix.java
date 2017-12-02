package ui;

import java.io.File;
import java.io.IOException;

import contectCore.Core;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import sun.applet.Main;

public class Mix implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(MainPage.core!=null){
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("vCard files", "*.vcf"));
			fileChooser.setInitialDirectory(new File("./"));
		    fileChooser.setTitle("Open Resource File");
		    File file =fileChooser.showOpenDialog(stage);
			try {
				MainPage.core.importFromvCard(file.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   //¥”vCardµº»Î
		}
	}
		
}
