package ui;
//2.查看分组
import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sun.applet.Main;

import java.awt.*;
import java.util.List;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.control.ScrollPane;
import javax.swing.*;
import javax.swing.event.*;

import contectCore.Group;
import contectCore.Person;


public class SearchGroup extends Application{
	void SearchGroup(){
		
	}
    public void start(Stage primaryStage){
    	BorderPane pane=new BorderPane();
    	pane.setPadding(new Insets(30,30,30,30));
    	Button back=new Button("返回首页");
    	back.setOnAction(e->mainPage(primaryStage));
    	pane.setTop(back);
    	FlowPane pane1=new FlowPane();
    	pane1.setAlignment(Pos.CENTER);
        pane1.setOrientation(Orientation.VERTICAL);
        //分组情况
        ObservableList<Group> TeamName=FXCollections.observableArrayList(MainPage.core.groupList.getGroups());
    	ListView<Group> lv=new ListView<>(FXCollections.observableArrayList(TeamName));
    	lv.setPrefSize(500,200);
    	lv.setOnMouseClicked(e->{
    		if (e.getClickCount()==2) {
				Group seleted =lv.getSelectionModel().getSelectedItem();
				try {
					Ingroup.group=seleted;
					new Ingroup().start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("seleted group is"+seleted.getListName());
			}
    		
    	});
    	
    	lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	/*if(lv==TeamName){
    		
    	}*/
    	
    	ImageView addd=new ImageView("pe.png");
        addd.setFitHeight(20);
     	addd.setFitWidth(20);
        Button add=new Button("",addd);
        add.setOnAction(e->addNewPerson(primaryStage));
        
    	Button addnewteam=new Button("添加新分组");
    	addnewteam.setOnAction(e->{
    		new NewTeam().start(primaryStage);
    	});
    	Button delete=new Button("删除组别");
    	delete.setOnAction(e->{
    		new EditGroup().start(primaryStage);
    	});
       
    	GridPane pane2=new GridPane();
    	pane2.add(add, 0,2);
    	GridPane.setHalignment(add, HPos.RIGHT);
    	pane2.setAlignment(Pos.TOP_CENTER);
    	pane2.setPadding(new Insets(11.5,12.5,13.5,14.5));
    	pane2.setHgap(2);
    	pane2.setVgap(5.5);
    	pane2.add(getSearch(primaryStage), 0, 1);
    	pane2.add(lv, 0, 3);
    	/*pane2.add(add, 0,10);
    	GridPane.setHalignment(add, HPos.RIGHT);*/
    	pane2.add(addnewteam, 0,11);
    	GridPane.setHalignment(addnewteam, HPos.RIGHT);
    	pane2.add(delete, 0,12);
    	GridPane.setHalignment(delete, HPos.RIGHT);
    	pane.setCenter(pane2);
    
        pane1.setVgap(30);
    	Scene scene=new Scene(pane,700,450);
        primaryStage.setTitle("通讯录管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //返回首页
    void mainPage(Stage primaryStage){
    	MainPage page=new MainPage();
    	page.start(primaryStage);
    }
    
    private HBox getSearch(Stage primaryStage){
     	HBox hBox=new HBox(15);
     	hBox.setPadding(new Insets(30,50,30,50));
     	hBox.setSpacing(20);
       ImageView search=new ImageView("timg.png");
     	search.setFitHeight(20);
     	search.setFitWidth(20);
    	hBox.getChildren().add(search);
    	
    	
    	 
    	//ObservableList<String> items11=FXCollections.observableArrayList(days);
    	
    	TextField message=new TextField("Search");
    	message.setEditable(true);
    	message.setStyle("-fx-text-fill:GREY");
    	message.setAlignment(Pos.BASELINE_LEFT);
        //hBox.getChildren().add(cbo);
    	Button searchbt = new Button("搜索");
    	searchbt.setOnAction(e->{
    		String text=(message.getText());
    		SearchEngine.text=text;
    		try {
				new SearchEngine().start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    	hBox.getChildren().addAll(message,searchbt);
    	return hBox;
    }
  
     //添加新联系人  跳转到PersonInfor的页面
    void  addNewPerson(Stage primaryStage){
    	PersonInfor newPerson=new PersonInfor();
    	PersonInfor.person=null;
    	newPerson.start(primaryStage);
    	
    }
	public static void main(String args[]){
				Application.launch(args);
			}
	
}
