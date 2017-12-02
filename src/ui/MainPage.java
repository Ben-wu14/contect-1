package ui;
import ui.PersonInfor;
//1.��ҳ
//2.����ͨѶ¼��ѡ����ַ�ʽ����
//3.����ͨѶ¼��ѡ���Ժ��ַ�ʽ����
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
	Button Import =new Button("����ͨѶ¼");
	Button Export =new Button("����ͨѶ¼");
	//Button mix	  =new Button("�ں�ͨѶ¼");
	
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
    
	//����ϵͳ
	Button save= new Button("�����޸�");
	save.setOnAction(e->{
		if(core!=null&&filePath!=null)
			core.exportTovCard(filePath);
	});
	Button exit= new Button("�˳�ϵͳ");
	exit.setOnAction(e->{
		if(core!=null)
			core.exportTovCard("original.vcf");
		System.exit(0);
	});
	Button enter=new Button("����ϵͳ");
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
    primaryStage.setTitle("ͨѶ¼����ϵͳ");
    primaryStage.setScene(scene);
    primaryStage.show();
	}
	//��ת�����ҷ����ҳ��
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
	//�ںϷ�ʽ
	void MixType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("����");
    	back.setOnAction(e->start(primaryStage));
		Text text=new Text("�����������ļ���ʽ�ںϣ�");
		Button btcsv=new Button("CSV�ļ�");
		Button btvcard=new Button("vCard�ļ�");
		
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
				}   //��vCard����
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
			}   //��vCard����
			}
		});
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("ѡ���ںϷ�ʽ");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//ѡ���뷽ʽ
	void ImportType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("����");
    	back.setOnAction(e->start(primaryStage));
		Text text=new Text("�����������ļ���ʽ���룿");
		Button btcsv=new Button("CSV�ļ�");
		Button btvcard=new Button("vCard�ļ�");
		
		btcsv.setOnAction(new Importcsv());
		btvcard.setOnAction(new ImportvCard());
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("ѡ���뷽ʽ");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	//ѡ�񵼳���ʽ
	void ExportType(Stage primaryStage){
		FlowPane pane=new FlowPane();
		pane.setAlignment(Pos.CENTER);
    	pane.setOrientation(Orientation.VERTICAL);
    	pane.setVgap(20);
    	
    	Button back=new Button("����");
    	back.setOnAction(e->start(primaryStage));
    	
		Text text=new Text("�����������ļ���ʽ������");
		Button btcsv=new Button("CSV�ļ�");
		Button btvcard=new Button("vCard�ļ�");
		
		btcsv.setOnAction(new Exportcsv());
		btvcard.setOnAction(new ExportvCard());
		
		Scene scene=new Scene(pane,600,300);
        primaryStage.setTitle("ѡ�񵼳���ʽ");
        pane.getChildren().addAll(text,btcsv,btvcard,back);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	
   public static void main(String[] args){
		Application.launch(args);
	}
}
