package ui;
//�����·���
import contectCore.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.awt.event.*;
import java.util.ArrayList;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
public class NewTeam extends Application{
	public void NewTeam(){
		
	}
    
	TextField teamname=new TextField();
	TextField oldperson=new TextField();            //������ϵ������
	TextField oldTeam=new TextField();     //���еķ���
	@Override
	public void start(Stage primaryStage){
		BorderPane pane=new BorderPane();
		//���ؼ�
    	pane.setTop(getHBox(primaryStage));
    	
    	FlowPane allPane=new FlowPane();
    	allPane.setAlignment(Pos.TOP_CENTER);
    	allPane.setOrientation(Orientation.VERTICAL);
    	allPane.setVgap(20);

    	//��������
    	FlowPane pane1=new FlowPane();
    	pane1.setHgap(30);
    	pane1.setAlignment(Pos.TOP_CENTER);
    	Text text=new Text("�������·�����:");
    
    	//ȷ�����
    	Button sure1Button =new Button("ȷ�����");
    	sure1Button.setOnAction(e->newGroup(primaryStage));
    	pane1.getChildren().addAll(text,teamname,sure1Button);
    	
    	allPane.getChildren().add(pane1);
    	pane.setCenter(allPane);
    
		// TODO Auto-generated 
    	Scene scene=new Scene(pane,600,380);
	    primaryStage.setTitle("ͨѶ¼����ϵͳ > �鿴����  >����·���");
	    primaryStage.setScene(scene);
	    primaryStage.show(); 
		
	}
	
	
	//����µ���
	void newGroup(Stage primaryStage){
    	String newTeam=teamname.getText();
    	GroupList groupList=MainPage.core.groupList;
    	 //ArrayList<Group> groups=MainPage.core.groupList.getGroups();
    	 if(groupList.getIndexOfGroup(newTeam)==-1)
    	 {
    		 add(primaryStage,groupList.newGroup(newTeam));
    		 System.out.println("������ӳɹ���");
    	}
    	 else {
		   System.out.println("�÷����Ѵ��ڣ����������������");
		}
    	 
	}
	//��������ӳ�Ա
	void add(Stage primaryStage,Group group){
		//������������������ϵ��
    	Button back=new Button("����");
    	back.setOnAction(e->{
    		MainPage.core.groupList.removeGroup(group);
    		start(primaryStage);
    	});
    	GridPane pane2=new GridPane();
    	pane2.setVgap(15);
    	pane2.setPadding(new Insets(10,0,0,20));
    	//pane2.setAlignment(Pos.TOP_CENTER);
   
		Button sure2=new Button("���������������ϵ��");
    	sure2.setOnAction(e->addOldperson(primaryStage,group));

       pane2.add(back, 4,2);
       pane2.add(sure2, 30,4);
    	
       Button sure3=new Button("������������еķ���");
       sure3.setOnAction(e->addOldTeam(primaryStage,group));
       pane2.add(sure3, 30,6);
       
       Button finish = new Button("����");
       finish.setOnAction(e->{
    	   try {
    		new SearchGroup().start(primaryStage);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	   
       });
        pane2.add(finish, 30,8);
    	
        Scene scene=new Scene(pane2,600,380);
        primaryStage.setTitle("ͨѶ¼����ϵͳ > ����·���  > ��������ӳ�Ա");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//�����������������ϵ��
	void addOldperson(Stage primaryStage,Group group){
		
		VBox outvbox= new VBox(15);
		VBox pane1=new VBox(15);
		outvbox.setPadding(new Insets(15,5,5,5));
		
		Button back=new Button("����");
		back.setOnAction(e->add(primaryStage,group));
		   
		outvbox.getChildren().add(back);
		
		int size=MainPage.core.groupList.getGroups().get(0).getList().size();
		CheckBox[] person = new CheckBox[size];
		
		for(int i=0;i<size;i++){
		    	 //ȡ��groupList
		GridPane pane2=new GridPane();
		pane2.setVgap(5.5);
		pane2.setHgap(5.5);
		
		person[i]=new CheckBox(MainPage.core.groupList.getGroups().get(0).getList().get(i).getName());	    
		
			pane2.add(person[i], 6, 0);
			pane1.getChildren().add(pane2);
			}
		
		Button sure=new Button("ȷ����ӣ�");
		sure.setOnAction(e->{    
			for (CheckBox checkBox : person) {
				if(checkBox.isSelected()){
					Person oldPerson=MainPage.core.groupList.getGroups().get(0).findPersonByName(checkBox.getText());
					group.addPerson(oldPerson);
					System.out.println(oldPerson.getName()+" ��ӳɹ���");
					add(primaryStage,group);
					}
				}
			}
  			);
    	ScrollPane scrollPane=new ScrollPane(pane1);
    	outvbox.getChildren().add(scrollPane);
    	outvbox.getChildren().add(sure);
    	sure.setAlignment(Pos.CENTER);
    	Scene scene=new Scene(outvbox,600,380);
        primaryStage.setTitle("ͨѶ¼����ϵͳ > ����·���  > ���������������ϵ��");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//��������������з���
	void addOldTeam(Stage primaryStage,Group group){
		VBox outvbox= new VBox(15);
		VBox pane1=new VBox(15);
		outvbox.setPadding(new Insets(15,5,5,5));
		
		Button back=new Button("����");
		back.setOnAction(e->add(primaryStage,group));
		   
		outvbox.getChildren().add(back);
		int size=MainPage.core.groupList.getGroups().size()-2;
		CheckBox[] checkBoxs = new CheckBox[size];
		
		for(int i=0;i<size;i++){
		    	
		GridPane pane2=new GridPane();
		pane2.setVgap(5.5);
		pane2.setHgap(5.5);

		checkBoxs[i]=new CheckBox(MainPage.core.groupList.getGroups().get(i+1).getListName());	    
		
		pane2.add(checkBoxs[i], 6, 0);
		
		  		 pane1.getChildren().add(pane2);
			}
		Button sure=new Button("ȷ����ӣ�");
		GridPane.setHalignment(sure, HPos.RIGHT);
  		sure.setOnAction(e->{    
  			for (CheckBox checkBox : checkBoxs) {
				if(checkBox.isSelected()){
					int index=MainPage.core.groupList.getIndexOfGroup(checkBox.getText());
					Group aGroup=MainPage.core.groupList.getGroups().get(index);
					group.addGroupOfPeople(aGroup);
					System.out.println(aGroup.getListName()+" ��ӳɹ���");
					add(primaryStage,group);
					}
				}
			}
  			
		);
  		ScrollPane scrollPane=new ScrollPane(pane1);
    	outvbox.getChildren().add(scrollPane);
    	outvbox.getChildren().add(sure);
    	sure.setAlignment(Pos.CENTER);
    	Scene scene=new Scene(outvbox,600,380);
        primaryStage.setTitle("ͨѶ¼����ϵͳ > ����·���  > ���������������ϵ��");
        primaryStage.setScene(scene);
        primaryStage.show();
    	
    	
	}
	

   //���ذ�ť
	private HBox getHBox(Stage primaryStage){
    	HBox hBox=new HBox(15);
    	hBox.setPadding(new Insets(50,100,50,50));
    	Button back=new Button("����");
    	back.setOnAction(e->Search(primaryStage));
    	hBox.getChildren().add(back);
    	hBox.setSpacing(380);
    	return hBox;
    }

	//��ת��SearchGroup��ҳ��
	void Search(Stage primaryStage){
		SearchGroup group=new SearchGroup();
		group.start(primaryStage);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Application.launch(args);
	}

}
