package ui;
import contectCore.*;
//6.�༭�������ϵ��
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import sun.applet.Main;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.collections.*;
import javafx.scene.image.*;
import javafx.stage.Stage;


public class PersonInfor extends Application{
	public static Person person;
	public static Group fromgroup;
	TextField name=new TextField();
   	TextField phoneNumber=new TextField();
	TextField tel=new TextField();
	TextField email=new TextField();
	TextField address=new TextField();
	TextField postCode=new TextField();
	TextField company=new TextField();
	TextField note=new TextField();
	TextField sex=new TextField();
	TextField instantContect=new TextField();
	ImageView photp;
	String pathperson;
	int year=0;
	int month=0;
	int day=0;
	List<Group> listOfGroups=new ArrayList<>();
	public PersonInfor(){
		year=0;
		month=0;
		day=0;
		photp=null;
		pathperson=null;
	}
	
	public void start(Stage primaryStage){
		name.setEditable(true);
		phoneNumber.setEditable(true);
		tel.setEditable(true);
		email.setEditable(true);
		address.setEditable(true);
		postCode.setEditable(true);
		company.setEditable(true);
		note.setEditable(true);
		sex.setEditable(true);
		instantContect.setEditable(true);
		
		year=month=day=0;
		
		BorderPane pane=new BorderPane();
		//����
		
		pane.setCenter(getGridPane());
    	//pane.setTop(getHBox1(primaryStage));getHBox1(primaryStage)
		//pane.setCenter(getCenterPane());
		//ͷ��
		pane.setLeft(getphoto(primaryStage));
	
		//2����ť
	  Button giveup1=new Button("����");
	   giveup1.setOnAction(e->Search(primaryStage));
		
		Button save1 =new Button("����");
	    save1.setOnAction(e->SaveData(primaryStage));
	    
	    Button backTeam=new Button("back");
    	backTeam.setOnAction(e->Search(primaryStage));
	    
		FlowPane paneforbutton=new FlowPane();
		paneforbutton.setAlignment(Pos.CENTER);
		paneforbutton.setHgap(20);
		paneforbutton.setOrientation(Orientation.HORIZONTAL);
		paneforbutton.getChildren().addAll(giveup1,save1,backTeam);
		paneforbutton.setPadding(new Insets(50, 0, 50, 0));
		
		
		pane.setBottom(paneforbutton);
		Scene scene=new Scene(pane,1200,600);
        primaryStage.setTitle("ͨѶ¼����ϵͳ");
        primaryStage.setScene(scene);
        primaryStage.show();
       
	}
	//�����м�һ��������
	
	//����
	//Description descriptionPane=new Description();
	public  FlowPane getFlowPane(){
		FlowPane pane1=new FlowPane();

		pane1.setHgap(10);  //�ڵ�䴹ֱ���
		//TextField tf=new TextField();
		//tf.setPrefColumnCount(1);
	   // TextField name=new TextField();
    	pane1.getChildren().addAll(new Label("��ϵ�������� "),name );
    	
    	Button button = new Button("ѡ�����");
    	button.setOnAction(e->{
    		groupChoser();
    	});
    	//cbo.setValue("��ѡ�����");
    	pane1.getChildren().add(button);
    //	cbo.setOnAction(e->setDisplay(items.indexOf(cbo.getValue())));
    	
		return pane1;
	}
	//���ѡ��
	public void groupChoser(){
		Stage stage= new Stage();
		int size = MainPage.core.groupList.getGroups().size()-1;
		System.out.println("size "+size);
		CheckBox[] checkBoxs = new CheckBox[size];
		for (int i=0;i<size;i++) {
			System.out.println("i:"+i);
			String temp=MainPage.core.groupList.getGroups().get(i+1).getListName();
			checkBoxs[i]=new CheckBox(temp);
			//System.out.println("checkbox"+i+"= "+);
		}
		VBox vBox = new VBox(20);
		vBox.getChildren().addAll(checkBoxs);
		Button addbtn = new Button("���");
		addbtn.setOnAction(e->{
			for (int i=0;i<size;i++) {
				if(checkBoxs[i].isSelected()){
					listOfGroups.add(MainPage.core.groupList.getGroups().get(i+1));
				}
			}
			stage.close();
		});
		Button close= new Button("ȡ��");
		close.setOnAction(e->{
			listOfGroups.clear();
			stage.close();
		});
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(addbtn,close);
		hBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(30));
		vBox.getChildren().add(hBox);
		vBox.setAlignment(Pos.CENTER);
		stage.setScene(new Scene(vBox,300,200));
		stage.setTitle("��ӵ�����");
		stage.show();
		
	}
	
	
	
	//��ѡ�Ա�
	private BorderPane getButton(){
		BorderPane pane2=new BorderPane();
		HBox sex1=new HBox(40);     //������ť�����
		sex1.setStyle("-fx-border-color:blue");
		sex1.setStyle("-fx-border-width:0.5px;-fx-border-color:white");
		RadioButton men=new RadioButton("��");
		RadioButton women=new RadioButton("Ů");
		sex1.getChildren().addAll(men,women);
		
		if (person!=null&&person.getSex()!=null) {
			if(person.getSex()=="male"){
				men.setSelected(true);
			}else{
				women.setSelected(true);
			}
		}
		men.setOnAction(e->{
			if(men.isSelected()){
				sex.setText("��");
			}
		});
		women.setOnAction(e->{
			if(women.isSelected()){
				sex.setText("Ů");
			}
		});
		pane2.setCenter(sex1);
		
		ToggleGroup group=new ToggleGroup();
		men.setToggleGroup(group);
		women.setToggleGroup(group);
		return pane2;
	}
	
	private VBox getGridPane(){
		GridPane pane5=new GridPane();
		pane5.setHgap(5.5);
		pane5.setVgap(5);
		
		TextField tfMi=new TextField();
		tfMi.setPrefColumnCount(1);
		//�������ڣ��ֻ��������ַ����ͥ��ַ��������λ����ͥ�绰���ʱ��ַ
		pane5.add(getButton(), 0, 0);
		pane5.add(new Label("���գ�"), 0, 1);
    	ComboBox<String> cbo=new ComboBox<>();
    	String[] years={"1980��","1981��","1982��","19"
    			+ "83��","1984��","1985��","1986��","1987��","1988��","1989��","1990��","1991��","1992��","1993��","1994��","1995��","1996��","1997��","1998��","1999��","2000��"};
    	//setDisplay(0);
    	cbo.getItems().addAll(years);
    	if (person==null||person.getBirthday()==null) {
    		cbo.setValue("--��");
		}else {
			cbo.setValue(person.getBirthday().getYear()+"��");
		}
    	pane5.add(cbo,1,1);
    	ObservableList<String> items=FXCollections.observableArrayList(years);
    	cbo.setOnAction(e->{
    		year=items.indexOf(cbo.getValue())+1980;
    	//	setDisplay(items.indexOf(cbo.getValue()));
    		
    	});
    	
    	ComboBox<String> cbo1=new ComboBox<>();
    	String[] months={"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��"};
    	//setDisplay(0);
    	cbo1.getItems().addAll(months);
    	if (person==null||person.getBirthday()==null) {
    		cbo1.setValue("--��");
		}else {
			cbo1.setValue(person.getBirthday().getMonth()+"��");
		}
    	pane5.add(cbo1,2,1);
    	ObservableList<String> items1=FXCollections.observableArrayList(months);
    	cbo1.setOnAction(e->{
    		month=items1.indexOf(cbo1.getValue())+1;
    		//setDisplay(items1.indexOf(cbo.getValue()));
    	});
    
    	ComboBox<String> cbo2=new ComboBox<>();
    	String[] days={"1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��","13��","14��","15��","16��","17��","18��","19��","20��","21��","22��","23��","24��","25��","26��","27��","28��","29��","30��","31��"};
    	//setDisplay(0);
    	 cbo2.getItems().addAll(days);
    	 if (person==null||person.getBirthday()==null) {
			cbo2.setValue("--��");
		}else{
			cbo2.setValue(person.getBirthday().getDay()+"��");
		}
    	 
    	pane5.add( cbo2,3,1);
    	ObservableList<String> items11=FXCollections.observableArrayList(days);
    	cbo2.setOnAction(e->{
    		day=items11.indexOf(cbo2.getValue())+1;
    	//	setDisplay(items11.indexOf(cbo.getValue()));
    	});
 
    	
		pane5.add(new Label("�ֻ��� "), 0,5);
		pane5.add(phoneNumber,1,5);
		pane5.add(new Label("��ͥ�绰�� "), 6,5);
		pane5.add(tel,7,5);
		pane5.add(new Label("�����ַ�� "), 0,7);
		pane5.add(email,1,7);
		pane5.add(new Label("��ͥ��ַ�� "), 6,7);
		pane5.add(address,7,7);
		pane5.add(new Label("�ʱ��ַ�� "), 6,9);
		pane5.add(postCode,7,9);
		pane5.add(new Label("������λ�� "), 0,9);
		pane5.add(company,1,9);
		pane5.add(new Label("��ʱͨѶ�� "), 0,11);
		pane5.add(instantContect,1,11); 
		
		pane5.add(new Label("��ע���ݣ�"),0,13);
		pane5.add(note, 1, 13);
		
		//��׺
		pane5.add(new Label("ͨѶ¼����ϵͳ"), 3, 15);
		VBox vBox = new VBox(5.5);
		vBox.getChildren().addAll(getFlowPane(),pane5);
		vBox.setPadding(new Insets(50.5, 12.5, 0, 14.5));
		return vBox;
	}
	
	//��߲���ͷ�� �Ǳ��
	private FlowPane getphoto(Stage primaryStage){
		FlowPane photo=new FlowPane();
		photo.setAlignment(Pos.CENTER);
		photo.setOrientation(Orientation.VERTICAL);
		photo.setPadding(new Insets(0,10,180,120));
		photo.setHgap(5);
		photo.setVgap(8);
		
		if (person!=null&&person.getPicture()!=null) {
			photp=new ImageView(person.getPicture());
		}else{
			if(pathperson==null)
			photp=new ImageView("pe.png");
			else
				photp=new ImageView(pathperson);
		}
		
		photp.setFitHeight(50);
		photp.setFitWidth(50);
		
		//tfMi.setPrefColumnCount(1);
		photo.getChildren().add(photp);
		
		Button star=new Button("ѡ����Ƭ");
		star.setOnAction(e->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new ExtensionFilter("photos","*.jpg"));
			fileChooser.setInitialDirectory(new File("./"));
		    fileChooser.setTitle("Choose Picture");
		    File file =fileChooser.showOpenDialog(stage);
		    String path=file.getAbsoluteFile().getPath();
		    
		    
		    String oldPath=path;
		    String newPath="bin/photos/"+name.getText()+".jpg";
		    try { 
		    	int byteread = 0; 
		    	File oldfile = new File(oldPath); 
		    	if (oldfile.exists()) { //�ļ�����ʱ 
		    	InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
		    	FileOutputStream fs = new FileOutputStream(newPath); 
		    	byte[] buffer = new byte[1444]; 
		    	//int length; 
		    	while ( (byteread = inStream.read(buffer)) != -1) { 
		    	//bytesum += byteread; //�ֽ��� �ļ���С 
		    	//System.out.println(bytesum); 
		    	fs.write(buffer, 0, byteread); 
		    	} 
		    	inStream.close(); 
		    	fs.close();
		    	} 
		    	} 
		    	catch (Exception e1) { 
		    	System.out.println("���Ƶ����ļ���������"); 
		    	e1.printStackTrace(); 

		    	} 
		    	photo.getChildren().add(new ImageView("photos/"+name.getText()+".jpg"));
		    	pathperson="photos/"+name.getText()+".jpg";
		    	start(primaryStage);
		});
		photo.getChildren().add(star);
		
		return photo;
	}
	 // private HBox getHBox1(Stage primaryStage){
	  //  	HBox hBox=new HBox(15);
	  //  	hBox.setPadding(new Insets(50,100,50,100));
	  //  	Button backTeam=new Button("back");
	  //  	backTeam.setOnAction(e->Search(primaryStage));
	  //  	hBox.getChildren().add(backTeam);
	   // 	hBox.setSpacing(380);
	 //   	return hBox;
	//    }
	 //���ؼ���ȡ��������Ϊ�˷��ز��ҷ����ҳ��
	  void Search(Stage primaryStage){
		SearchGroup  searchgroup=new SearchGroup();
		searchgroup.start(primaryStage);
	  }
	  
	//��������  
	void SaveData(Stage primaryStage){
		String name1=name.getText();
		if (name1==null||name1.equals("")) {
			Stage stage= new Stage();
			stage.setScene(new Scene(new Label("��ϵ�����Ʋ���Ϊ��"),200,100));
			stage.setTitle("Warning");
			stage.show();
			return;
		}
		if (person==null) {
			Person person=new Person(name1);
			PersonInfor.person=person;
		}
		person.setName(name.getText());
		if(sex.getText()!=null&&sex.getText().equals("��")){
			person.setSex("male");
		}else{
			person.setSex("female");
		}
		if(year*month*day!=0)
		person.setBirthday(new Time(year, month, day));
		if(pathperson!=null)
		person.setPicture(pathperson);
		if(phoneNumber.getText()!=null&&!phoneNumber.getText().equals(""))
		person.setPhoneNumber(phoneNumber.getText());
		if(tel.getText()!=null&&!tel.getText().equals(""))
		person.setTel(tel.getText());
		if(email.getText()!=null&&!email.getText().equals(""))
		person.setEmail(email.getText());
		if(address.getText()!=null&&!address.getText().equals(""))
		person.setAddress(address.getText());
		if(postCode.getText()!=null&&!postCode.getText().equals(""))
		person.setPostCode(postCode.getText());
		if(company.getText()!=null&&!company.getText().equals(""))
		person.setCompany(company.getText());
		if(note.getText()!=null&&!note.getText().equals(""))
		person.setNote(note.getText());
		if(instantContect.getText()!=null&&!instantContect.getText().equals(""))
		person.setInstantContect(instantContect.getText());
		person.addToGroups(listOfGroups);
		PersonInfor.person=person;
		showPersonData(primaryStage);
	}
	
	//չʾ��������
	void showPersonData(Stage primaryStage){
		GridPane paneforShow=new GridPane();

		name.setText(person.getName());
		sex.setText(person.getSex());
		phoneNumber.setText(person.getPhoneNumber());
		tel.setText(person.getTel());
		email.setText(person.getEmail());
		address.setText(person.getAddress());
		postCode.setText(person.getPostCode());
		company.setText(person.getCompany());
		note.setText(person.getNote());
		instantContect.setText(person.getInstantContect());
		
		Button back=new Button("�˳�");    //�ô��Ƿ��ز���
		back.setOnAction(e->{
			MainPage.core.fulllist.addPerson(person);
			if(fromgroup!=null){
				fromgroup.addPerson(person);
			}
			new SearchGroup().start(primaryStage);
		});
		
		Button edit=new Button("�༭");     //���ر༭
		edit.setOnAction(e->start(primaryStage));
		ImageView photo=null;   //ͷ��
		if(person.getPicture()!=null){
			photo=new ImageView(person.getPicture());
			
		}else{
			photo=new ImageView("pe.png");
		}
		photo.setFitHeight(50);
		photo.setFitWidth(50);
		
		paneforShow.setHgap(5);
		paneforShow.setVgap(5);
		paneforShow.add(back, 1, 1);
		paneforShow.add(edit, 3, 1);
		if(photo!=null)
	    paneforShow.add(photo, 7,3);
		paneforShow.add(new Label("���֣�"), 5,10);
		paneforShow.add(name, 7, 10);
		paneforShow.add(new Label("�Ա�"), 5,11);
		paneforShow.add(sex, 7, 11);
		paneforShow.add(new Label("�ֻ���"),5,12);
		paneforShow.add(phoneNumber, 7, 12);
		paneforShow.add(new Label("��ͥ�绰��"),5,13);
		paneforShow.add(tel, 7, 13);
		paneforShow.add(new Label("�����ַ��"),5,14);
		paneforShow.add(email, 7, 14);
		paneforShow.add(new Label("��ͥ��ַ��"),5,15);
		paneforShow.add(address, 7, 15);
		paneforShow.add(new Label("�ʱࣺ"),5,16);
		paneforShow.add(postCode, 7, 16);
		paneforShow.add(new Label("������λ��"),5,17);
		paneforShow.add(company, 7, 17);
		paneforShow.add(new Label("��ע��"),5,18);
		paneforShow.add(note, 7, 18);
		
		name.setEditable(false);
		sex.setEditable(false);
		phoneNumber.setEditable(false);
		tel.setEditable(false);
		email.setEditable(false);
		address.setEditable(false);
		postCode.setEditable(false);
		company.setEditable(false);
		note.setEditable(false);
		instantContect.setEditable(false);
		
		Scene scene=new Scene(paneforShow,500,600);
        primaryStage.setTitle("ͨѶ¼����ϵͳ");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
      public static void main(String args[]){
    	  Application.launch(args);
 
	}
}
