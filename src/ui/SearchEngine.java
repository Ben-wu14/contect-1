package ui;

import java.util.ArrayList;
import java.util.List;

import contectCore.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.applet.Main;

public class SearchEngine extends Application{
	private static ArrayList<Person> list=new ArrayList<>();
	public static String text="search";
	private static ObservableList<Person> data ;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(500, 300);
		HBox hBox=new HBox(30);
     	hBox.setSpacing(20);
       ImageView search=new ImageView("image.jpg");
     	search.setFitHeight(20);
     	search.setFitWidth(20);
    	hBox.getChildren().add(search);
    	
    	
    	TextField message=new TextField(text);
    	message.setEditable(true);
    	message.setStyle("-fx-text-fill:GREY");
    	message.setAlignment(Pos.BASELINE_LEFT);
    	
    	data =FXCollections.observableArrayList(list);
    	

    	
    	TableView<Person> tableView = new TableView<>();
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
		tableView.getColumns().addAll(first,second,third,four,five,six,seven,eight,nine,ten);
		list=MainPage.core.groupList.getGroups().get(0).searchByString(message.getText());
		data =FXCollections.observableArrayList(list);
		tableView.setItems(FXCollections.observableArrayList(data));
    	Button searchbt = new Button("搜索");
    	searchbt.setOnAction(e->{
    		list=MainPage.core.groupList.getGroups().get(0).searchByString(message.getText());
    		data =FXCollections.observableArrayList(list);
    		tableView.setItems(FXCollections.observableArrayList(data));
    	});
    	tableView.setOnMouseClicked(e->{
			if(e.getClickCount()==2){
				Person seleted = tableView.getSelectionModel().getSelectedItem();
				PersonInfor.person=seleted;
				System.out.println("Seleted person :"+seleted.getName());
				new PersonInfor().showPersonData(primaryStage);
				
			}
		});
    	
    	Button back =new Button("返回");
    	back.setOnAction(e->{
    		new SearchGroup().start(primaryStage);
    	});
    	
    	hBox.getChildren().addAll(back,message,searchbt);
    	hBox.setPadding(new Insets(0,50,30,50));
		
		borderPane.setTop(hBox);
		borderPane.setCenter(tableView);
		borderPane.setPadding(new Insets(50));
		
    	primaryStage.setScene(new Scene(borderPane,700,450));
    	primaryStage.setTitle("Search");
    	primaryStage.show();
	}

}
