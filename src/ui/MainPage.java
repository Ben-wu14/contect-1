package ui;
import ui.PersonInfor;
//1.首页
//2.导入通讯录可选择何种方式导入
//3.导出通讯录可选择以何种方式导出
//import java.awt.*;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import contectCore.Core;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class MainPage extends Application{ 
	public static Core core=null;
	public static String filePath=null;
	void MainPage(){
		
	}
	public void start(Stage primaryStage){
	  Circle circle=new Circle();
		circle.setCenterX(600);
		circle.setCenterY(260);
		circle.setRadius(110);
		circle.setStroke(Color.BLUE);
		circle.setFill(Color.CADETBLUE);

    Label label=new Label("Your Address List",circle);
    label.setFont(Font.font("Times New Roman", FontWeight.BOLD , FontPosture.ITALIC,18));
    label.setContentDisplay(ContentDisplay.CENTER);



    
    HBox paneForButtons=new HBox(20);
	Button Import =new Button("导入通讯录");
	Button Export =new Button("导出通讯录");
	//Button mix	  =new Button("融合通讯录");
	
	Import.setOnAction(e->ImportType(primaryStage));
	Export.setOnAction(e->ExportType(primaryStage));
	/*mix.setOnAction(e->{
		if(MainPage.core!=null){
			MixType(primaryStage);
		}
	});*/
	paneForButtons.getChildren().addAll(Import,Export);
	paneForButtons.setAlignment(Pos.CENTER);
	
	FlowPane pane=new FlowPane();
    pane.setAlignment(Pos.CENTER);
    pane.setOrientation(Orientation.VERTICAL);
    pane.getChildren().addAll(label,paneForButtons);
	pane.setVgap(30);
    
	//进入系统
	Button save= new Button("保存修改");
	save.setOnAction(e->{
		if(core!=null&&filePath!=null)
			core.exportTovCard(filePath);
	});
	Button exit= new Button("退出系统");
	exit.setOnAction(e->{
		if(core!=null)
			core.exportTovCard("original.vcf");
		System.exit(0);
	});
	Button enter=new Button("进入系统");
	enter.setOnAction(e->Search(primaryStage));
	HBox hbox=new HBox(20);
	hbox.setAlignment(Pos.CENTER);
	hbox.getChildren().addAll(enter,save,exit);
	pane.getChildren().add(hbox);
    Scene scene=new Scene(pane,1200,600);
    primaryStage.setOnCloseRequest(e->{
    	if(core!=null)
			core.exportTovCard("original.vcf");
		System.exit(0);
    });
    primaryStage.setTitle("通讯录管理系统");
    primaryStage.setScene(scene);
    primaryStage.show();
	}
	//跳转到查找分组的页面
	void Search(Stage primaryStage){
		if(core==null){
			try {
				core=new Core();
				core.importFromvCard("original.vcf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SearchGroup group=new SearchGroup();
		group.start(primaryStage);
	}
	//融合方式
	void MixType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("返回");
    	back.setOnAction(e->start(primaryStage));
		Text text=new Text("以下列哪种文件方式融合？");
		Button btcsv=new Button("CSV文件");
		Button btvcard=new Button("vCard文件");
		
		btvcard.setOnAction(e->{
			if(MainPage.core!=null){
				Stage stage = new Stage();
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new ExtensionFilter("vCard files", "*.vcf"));
				fileChooser.setInitialDirectory(new File("./"));
			    fileChooser.setTitle("Open Resource File");
			    File file =fileChooser.showOpenDialog(stage);
				try {
					MainPage.core.importFromvCard(file.getPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   //从vCard导入
			}
		});
		btcsv.setOnAction(e->{
			if(MainPage.core!=null){
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("CSV files", "*.csv"));
			fileChooser.setInitialDirectory(new File("./"));
		    fileChooser.setTitle("Open Resource File");
		    File file =fileChooser.showOpenDialog(stage);
			try {
				MainPage.core.importFromCSV(file.getPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}   //从vCard导入
			}
		});
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("选择融合方式");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//选择导入方式
	void ImportType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("返回");
    	back.setOnAction(e->start(primaryStage));
		Text text=new Text("以下列哪种文件方式导入？");
		Button btcsv=new Button("CSV文件");
		Button btvcard=new Button("vCard文件");
		
		btcsv.setOnAction(new Importcsv());
		btvcard.setOnAction(new ImportvCard());
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("选择导入方式");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	//选择导出方式
	void ExportType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("返回");
    	back.setOnAction(e->start(primaryStage));
    	
		Text text=new Text("以下列哪种文件方式导出？");
		Button btcsv=new Button("CSV文件");
		Button btvcard=new Button("vCard文件");
		
		btcsv.setOnAction(new Exportcsv());
		btvcard.setOnAction(new ExportvCard());
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("选择导出方式");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
   public static void main(String[] args){
		Application.launch(args);
	}
}
