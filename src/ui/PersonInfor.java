package ui;
import contectCore.*;
//6.编辑或添加联系人
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
		//返回
		
		pane.setCenter(getGridPane());
    	//pane.setTop(getHBox1(primaryStage));getHBox1(primaryStage)
		//pane.setCenter(getCenterPane());
		//头像
		pane.setLeft(getphoto(primaryStage));
	
		//2个按钮
	  Button giveup1=new Button("放弃");
	   giveup1.setOnAction(e->Search(primaryStage));
		
		Button save1 =new Button("保存");
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
        primaryStage.setTitle("通讯录管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
       
	}
	//控制中间一整块的面板
	
	//姓名
	//Description descriptionPane=new Description();
	public  FlowPane getFlowPane(){
		FlowPane pane1=new FlowPane();

		pane1.setHgap(10);  //节点间垂直间隔
		//TextField tf=new TextField();
		//tf.setPrefColumnCount(1);
	   // TextField name=new TextField();
    	pane1.getChildren().addAll(new Label("联系人姓名： "),name );
    	
    	Button button = new Button("选择分组");
    	button.setOnAction(e->{
    		groupChoser();
    	});
    	//cbo.setValue("请选择分组");
    	pane1.getChildren().add(button);
    //	cbo.setOnAction(e->setDisplay(items.indexOf(cbo.getValue())));
    	
		return pane1;
	}
	//组别选择
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
		Button addbtn = new Button("添加");
		addbtn.setOnAction(e->{
			for (int i=0;i<size;i++) {
				if(checkBoxs[i].isSelected()){
					listOfGroups.add(MainPage.core.groupList.getGroups().get(i+1));
				}
			}
			stage.close();
		});
		Button close= new Button("取消");
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
		stage.setTitle("添加到分组");
		stage.show();
		
	}
	
	
	
	//单选性别
	private BorderPane getButton(){
		BorderPane pane2=new BorderPane();
		HBox sex1=new HBox(40);     //两个按钮间距离
		sex1.setStyle("-fx-border-color:blue");
		sex1.setStyle("-fx-border-width:0.5px;-fx-border-color:white");
		RadioButton men=new RadioButton("男");
		RadioButton women=new RadioButton("女");
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
				sex.setText("男");
			}
		});
		women.setOnAction(e->{
			if(women.isSelected()){
				sex.setText("女");
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
		//出生日期，手机，邮箱地址，家庭地址，工作单位、家庭电话，邮编地址
		pane5.add(getButton(), 0, 0);
		pane5.add(new Label("生日："), 0, 1);
    	ComboBox<String> cbo=new ComboBox<>();
    	String[] years={"1980年","1981年","1982年","19"
    			+ "83年","1984年","1985年","1986年","1987年","1988年","1989年","1990年","1991年","1992年","1993年","1994年","1995年","1996年","1997年","1998年","1999年","2000年"};
    	//setDisplay(0);
    	cbo.getItems().addAll(years);
    	if (person==null||person.getBirthday()==null) {
    		cbo.setValue("--年");
		}else {
			cbo.setValue(person.getBirthday().getYear()+"年");
		}
    	pane5.add(cbo,1,1);
    	ObservableList<String> items=FXCollections.observableArrayList(years);
    	cbo.setOnAction(e->{
    		year=items.indexOf(cbo.getValue())+1980;
    	//	setDisplay(items.indexOf(cbo.getValue()));
    		
    	});
    	
    	ComboBox<String> cbo1=new ComboBox<>();
    	String[] months={"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    	//setDisplay(0);
    	cbo1.getItems().addAll(months);
    	if (person==null||person.getBirthday()==null) {
    		cbo1.setValue("--月");
		}else {
			cbo1.setValue(person.getBirthday().getMonth()+"月");
		}
    	pane5.add(cbo1,2,1);
    	ObservableList<String> items1=FXCollections.observableArrayList(months);
    	cbo1.setOnAction(e->{
    		month=items1.indexOf(cbo1.getValue())+1;
    		//setDisplay(items1.indexOf(cbo.getValue()));
    	});
    
    	ComboBox<String> cbo2=new ComboBox<>();
    	String[] days={"1日","2日","3日","4日","5日","6日","7日","8日","9日","10日","11日","12日","13日","14日","15日","16日","17日","18日","19日","20日","21日","22日","23日","24日","25日","26日","27日","28日","29日","30日","31日"};
    	//setDisplay(0);
    	 cbo2.getItems().addAll(days);
    	 if (person==null||person.getBirthday()==null) {
			cbo2.setValue("--日");
		}else{
			cbo2.setValue(person.getBirthday().getDay()+"日");
		}
    	 
    	pane5.add( cbo2,3,1);
    	ObservableList<String> items11=FXCollections.observableArrayList(days);
    	cbo2.setOnAction(e->{
    		day=items11.indexOf(cbo2.getValue())+1;
    	//	setDisplay(items11.indexOf(cbo.getValue()));
    	});
 
    	
		pane5.add(new Label("手机： "), 0,5);
		pane5.add(phoneNumber,1,5);
		pane5.add(new Label("家庭电话： "), 6,5);
		pane5.add(tel,7,5);
		pane5.add(new Label("邮箱地址： "), 0,7);
		pane5.add(email,1,7);
		pane5.add(new Label("家庭地址： "), 6,7);
		pane5.add(address,7,7);
		pane5.add(new Label("邮编地址： "), 6,9);
		pane5.add(postCode,7,9);
		pane5.add(new Label("工作单位： "), 0,9);
		pane5.add(company,1,9);
		pane5.add(new Label("即时通讯： "), 0,11);
		pane5.add(instantContect,1,11); 
		
		pane5.add(new Label("备注内容："),0,13);
		pane5.add(note, 1, 13);
		
		//下缀
		pane5.add(new Label("通讯录管理系统"), 3, 15);
		VBox vBox = new VBox(5.5);
		vBox.getChildren().addAll(getFlowPane(),pane5);
		vBox.setPadding(new Insets(50.5, 12.5, 0, 14.5));
		return vBox;
	}
	
	//左边部分头像 星标等
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
		
		Button star=new Button("选择照片");
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
		    	if (oldfile.exists()) { //文件存在时 
		    	InputStream inStream = new FileInputStream(oldPath); //读入原文件 
		    	FileOutputStream fs = new FileOutputStream(newPath); 
		    	byte[] buffer = new byte[1444]; 
		    	//int length; 
		    	while ( (byteread = inStream.read(buffer)) != -1) { 
		    	//bytesum += byteread; //字节数 文件大小 
		    	//System.out.println(bytesum); 
		    	fs.write(buffer, 0, byteread); 
		    	} 
		    	inStream.close(); 
		    	fs.close();
		    	} 
		    	} 
		    	catch (Exception e1) { 
		    	System.out.println("复制单个文件操作出错"); 
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
	 //返回键和取消键都是为了返回查找分组的页面
	  void Search(Stage primaryStage){
		SearchGroup  searchgroup=new SearchGroup();
		searchgroup.start(primaryStage);
	  }
	  
	//保存数据  
	void SaveData(Stage primaryStage){
		String name1=name.getText();
		if (name1==null||name1.equals("")) {
			Stage stage= new Stage();
			stage.setScene(new Scene(new Label("联系人名称不能为空"),200,100));
			stage.setTitle("Warning");
			stage.show();
			return;
		}
		if (person==null) {
			Person person=new Person(name1);
			PersonInfor.person=person;
		}
		person.setName(name.getText());
		if(sex.getText()!=null&&sex.getText().equals("男")){
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
	
	//展示个人资料
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
		
		Button back=new Button("退出");    //该处是返回查找
		back.setOnAction(e->{
			MainPage.core.fulllist.addPerson(person);
			if(fromgroup!=null){
				fromgroup.addPerson(person);
			}
			new SearchGroup().start(primaryStage);
		});
		
		Button edit=new Button("编辑");     //返回编辑
		edit.setOnAction(e->start(primaryStage));
		ImageView photo=null;   //头像
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
		paneforShow.add(new Label("名字："), 5,10);
		paneforShow.add(name, 7, 10);
		paneforShow.add(new Label("性别："), 5,11);
		paneforShow.add(sex, 7, 11);
		paneforShow.add(new Label("手机："),5,12);
		paneforShow.add(phoneNumber, 7, 12);
		paneforShow.add(new Label("家庭电话："),5,13);
		paneforShow.add(tel, 7, 13);
		paneforShow.add(new Label("邮箱地址："),5,14);
		paneforShow.add(email, 7, 14);
		paneforShow.add(new Label("家庭地址："),5,15);
		paneforShow.add(address, 7, 15);
		paneforShow.add(new Label("邮编："),5,16);
		paneforShow.add(postCode, 7, 16);
		paneforShow.add(new Label("工作单位："),5,17);
		paneforShow.add(company, 7, 17);
		paneforShow.add(new Label("备注："),5,18);
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
        primaryStage.setTitle("通讯录管理系统");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
      public static void main(String args[]){
    	  Application.launch(args);
 
	}
}
