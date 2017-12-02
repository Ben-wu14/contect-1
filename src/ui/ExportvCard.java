package ui;
import java.io.File;
import java.io.IOException;

//点击按钮以CSV文件格式导出
import contectCore.Core;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

 class ExportvCard implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("vCard files", "*.vcf"));
		fileChooser.setInitialDirectory(new File("./"));
	    fileChooser.setTitle("Open Resource File");
	    File file =fileChooser.showSaveDialog(stage);
		MainPage.core.exportTovCard(file.getPath());
	}

}
