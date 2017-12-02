package ui;


import java.util.ArrayList;

import com.sun.scenario.effect.impl.prism.PrImage;

import contectCore.Group;
import contectCore.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import sun.applet.Main;

public class Ingroup extends Application{
	public static Group group;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(500, 300);
		TableView<Person> tableView = new TableView<>();
		ObservableList<Person> data =FXCollections.observableArrayList(group.getList());
		TableColumn<Person, String> first=new TableColumn<>("Name");
		first.setMinWidth(100);
		first.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Person,String> second = new TableColumn<>("手机");
		second.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		TableColumn<Person,String> third = new TableColumn<>("家庭电话");
		third.setCellValueFactory(new PropertyValueFactory<>("tel"));
		
		TableColumn<Person,String> four = new TableColumn<>("email");
		four.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Person,String> five = new TableColumn<>("公司");
		five.setCellValueFactory(new PropertyValueFactory<>("company"));
		
		TableColumn<Person,String> six = new TableColumn<>("邮编");
		six.setCellValueFactory(new PropertyValueFactory<>("postCode"));
		
		TableColumn<Person,String> seven = new TableColumn<>("地址");
		seven.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		TableColumn<Person,String> eight = new TableColumn<>("生日");
		eight.setCellValueFactory(new PropertyValueFactory<>("birthday"));
		
		TableColumn<Person,String> nine = new TableColumn<>("QQ");
		nine.setCellValueFactory(new PropertyValueFactory<>("instantContect"));
		
		TableColumn<Person,String> ten = new TableColumn<>("备注");
		ten.setCellValueFactory(new PropertyValueFactory<>("note"));
		
		/// don't understand!!!!!--------------------------------------------
		//----------------------------------------------------------------
		tableView.setItems(FXCollections.observableArrayList(data));
		tableView.getColumns().addAll(first,second,third,four,five,six,seven,eight,nine,ten);
		tableView.setOnMouseClicked(e->{
			if(e.getClickCount()==2){
				Person seleted = tableView.getSelectionModel().getSelectedItem();
				PersonInfor.person=seleted;
				System.out.println("Seleted person :"+seleted.getName());
				new PersonInfor().showPersonData(primaryStage);
				
			}
			
		});
		VBox vBox = new VBox(20.0);
		HBox hBox = new HBox(10.0);
		CheckBox check1= new CheckBox("手机");
		check1.setSelected(true);
		check1.selectedProperty().addListener(e->{
				second.setVisible(check1.isSelected());
		});
		CheckBox check2= new CheckBox("家庭电话");
		check2.setSelected(true);
		check2.selectedProperty().addListener(e->{
				third.setVisible(check2.isSelected());
		});
		CheckBox check3= new CheckBox("email");
		check3.setSelected(true);
		check3.selectedProperty().addListener(e->{
				four.setVisible(check3.isSelected());
		});
		CheckBox check4= new CheckBox("公司");
		check4.setSelected(true);
		check4.selectedProperty().addListener(e->{
				five.setVisible(check4.isSelected());
		});
		CheckBox check5= new CheckBox("邮编");
		check5.setSelected(true);
		check5.selectedProperty().addListener(e->{
				six.setVisible(check5.isSelected());
		});
		CheckBox check6= new CheckBox("地址");
		check6.setSelected(true);
		check6.selectedProperty().addListener(e->{
				seven.setVisible(check6.isSelected());
		});
		CheckBox check7= new CheckBox("生日");
		check7.setSelected(true);
		check7.selectedProperty().addListener(e->{
				eight.setVisible(check7.isSelected());
		});
		CheckBox check8= new CheckBox("QQ");
		check8.setSelected(true);
		check8.selectedProperty().addListener(e->{
				nine.setVisible(check8.isSelected());
		});
		CheckBox check9= new CheckBox("备注");
		check9.setSelected(true);
		check9.selectedProperty().addListener(e->{
				ten.setVisible(check9.isSelected());
		});
		hBox.getChildren().addAll(check1,check2,check3,check4,check5,check6,check7,check8,check9);
		Button back = new Button("返回");
		back.setOnAction(e->{
			new SearchGroup().start(primaryStage);
		});
		Button newperson = new Button("新建联系人");
		newperson.setOnAction(e->{
			PersonInfor.fromgroup=group;
			new PersonInfor().start(primaryStage);
		});
		Button delete = new Button("删除联系人");
		delete.setOnAction(e->{
			deletshow(primaryStage);
		});
		Button btnaddPerson = new Button("添加联系人");
		if(group==MainPage.core.groupList.getGroups().get(0)){
			btnaddPerson.setVisible(false);
		}
			
		btnaddPerson.setOnAction(e->{
			if(group!=MainPage.core.groupList.getGroups().get(0)){
				addShow(primaryStage);
			}
		});
		HBox hBox2 = new HBox(20);
		hBox2.getChildren().addAll(back,newperson,delete,btnaddPerson);
		vBox.getChildren().addAll(hBox2,hBox);
		borderPane.setCenter(tableView);
		borderPane.setTop(vBox);
		borderPane.setPadding(new Insets(50));
		//borderPane.getChildren().add(tableView);
		
		
		primaryStage.setScene(new Scene(borderPane,1000,600));
		primaryStage.setTitle("联系人管理");
		primaryStage.show();
	}
	public void deletshow(Stage primaryStage) {
		Stage stage= primaryStage;
		ScrollPane scrollPane = new ScrollPane();
		int size = group.getList().size();
		System.out.println("size "+size);
		CheckBox[] checkBoxs = new CheckBox[size];
		for (int i=0;i<size;i++) {
			System.out.println("i:"+i);
			String temp=group.getList().get(i).getName();
			checkBoxs[i]=new CheckBox(temp);
			//System.out.println("checkbox"+i+"= "+);
		}
		VBox vsmall = new VBox(20);
		vsmall.getChildren().addAll(checkBoxs);
		scrollPane.setContent(vsmall);
		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(scrollPane);
		ArrayList<Person> deletList= new ArrayList<>();
		Button addbtn = new Button("删除");
		addbtn.setOnAction(e->{
			for (int i=0;i<size;i++) {
				if(checkBoxs[i].isSelected()){
					deletList.add(group.getList().get(i));
				}
			}
			if(group==MainPage.core.groupList.getGroups().get(0)){
				MainPage.core.groupList.deletPersonCompletly(deletList);
			}else{
				group.deleteListOfPeople(deletList);
			}
			try {
				start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button close= new Button("取消");
		close.setOnAction(e->{
			try {
				start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(addbtn,close);
		hBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(30));
		vBox.getChildren().add(hBox);
		vBox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(vBox,300,200));
		stage.setTitle("删除联系人");
		stage.show();
	}
	public void addShow(Stage primaryStage ) {
		Stage stage= primaryStage;
		ScrollPane scrollPane = new ScrollPane();
		ArrayList<Person> newList = new ArrayList<>(MainPage.core.groupList.getGroups().get(0).getList());
		newList.removeAll(group.getList());
		int size = newList.size();
		System.out.println("size "+size);
		CheckBox[] checkBoxs = new CheckBox[size];
		for (int i=0;i<size;i++) {
			System.out.println("i:"+i);
			String temp=newList.get(i).getName();
			checkBoxs[i]=new CheckBox(temp);
			//System.out.println("checkbox"+i+"= "+);
		}
		VBox vsmall = new VBox(20);
		vsmall.getChildren().addAll(checkBoxs);
		scrollPane.setContent(vsmall);
		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(scrollPane);
		ArrayList<Person> recordtList= new ArrayList<>();
		Button addbtn = new Button("添加");
		addbtn.setOnAction(e->{
			for (int i=0;i<size;i++) {
				if(checkBoxs[i].isSelected()){
					recordtList.add(newList.get(i));
				}
				group.addListOfPeople(recordtList);
			}
			try {
				start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button close= new Button("取消");
		close.setOnAction(e->{
			try {
				start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(addbtn,close);
		hBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(30));
		vBox.getChildren().add(hBox);
		vBox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(vBox,300,200));
		stage.setTitle("添加联系人");
		stage.show();
	}
}
