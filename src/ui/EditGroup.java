package ui;
import ui.SearchGroup;
//删除分组
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.awt.event.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class EditGroup extends Application{
       void EditGruop(){   
       }
	
	@Override
	public void start(Stage primaryStage){
		// TODO Auto-ge 3+nerated method stub

	    //放置删除框
	    FlowPane pane1=new FlowPane();
	    pane1.setAlignment(Pos.TOP_CENTER);
	    pane1.setVgap(20);
	    pane1.setOrientation(Orientation.VERTICAL);
	    //取出groupList
	    //循环CheckBox   加入pane1
	    VBox vsmall= new VBox(20);
	    int size=MainPage.core.groupList.getGroups().size()-1;
	    CheckBox[] checkBoxs = new CheckBox[size];
	    
	    for(int i=0;i<size;i++){
		    //取出groupList
	    	  checkBoxs[i]=new CheckBox(MainPage.core.groupList.getGroups().get(i+1).getListName());	    
	  		
		 
	    }
	    vsmall.getChildren().addAll(checkBoxs);
	    ScrollPane scrollPane = new ScrollPane(vsmall);
	    
	    
	    Button sure=new Button("确定删除？");
  		sure.setOnAction(e->{    
  			for (CheckBox checkBox : checkBoxs) {
				if(checkBox.isSelected()){
					 MainPage.core.groupList.removeGroup(checkBox.getText());
					 System.out.println("删除成功！");
					 Search(primaryStage);
			}
			}
		}); 
  		pane1.getChildren().addAll(getHBox(primaryStage),scrollPane,sure);
	    Scene scene=new Scene(pane1,500,600);
        primaryStage.setTitle("通讯录管理系统 > 查看分组 > 删除组别");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	//void removeAllGroup(String group)
	private HBox getHBox(Stage primaryStage){
    	HBox hBox=new HBox(15);
    	hBox.setPadding(new Insets(50,100,50,80));
    	Button back=new Button("返回");
    	back.setOnAction(e->Search(primaryStage));
    	hBox.getChildren().add(back);
    	hBox.setSpacing(380);
    	return hBox;
    }
	
	void Search(Stage primaryStage){
		SearchGroup group=new SearchGroup();
		group.start(primaryStage);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      Application.launch(args);
	}

}
