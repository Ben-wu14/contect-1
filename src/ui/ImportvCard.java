package ui;
import java.io.File;
import java.io.IOException;

//以vCard方式导入
import contectCore.Core;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

 class ImportvCard implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		File file2=new File("bin/photos");
		String[]pictures= file2.list();
		for (String string : pictures) {
			new File("bin/photos/"+string).delete();
		}
		file2=new File("bin/images");
		String[]pictures2= file2.list();
		for (String string : pictures2) {
			new File("bin/images/"+string).delete();
		}
		Stage stage = new Stage();
		MainPage.core=new Core();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("vCard files", "*.vcf"));
		fileChooser.setInitialDirectory(new File("./"));
	    fileChooser.setTitle("Open Resource File");
	    File file =fileChooser.showOpenDialog(stage);
	    MainPage.filePath=file.getPath();
		try {
			MainPage.core.importFromvCard(file.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   //从vCard导入
	}

}
