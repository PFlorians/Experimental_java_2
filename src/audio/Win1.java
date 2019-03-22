package audio;
import java.io.File;

import javafx.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.*;
import javafx.scene.input.*;
import generalTools.*;

public class Win1 extends Application{
	private Stage stage;
	private MediaPlayer p;
	private Player one;
	private Text volumeValue;
	private Label label1;
	private Win1 me;
	private VBox playlist;
	private ScrollPane sp;
	public Win1()
	{
		this.one=new Player();
		this.volumeValue=new Text("");
		this.me=this;
		this.playlist=new VBox();
		
	}
	public void init(String args[])
	{
		try
		{
			this.p=null;
			launch(args);
		}
		catch(Exception e)
		{
			System.out.println("konstruktor: " + e);
			e.printStackTrace();
		}
	}
	public void start(Stage stage)
	{
		this.stage=stage;
		createPlayerWin();
	}
	private void createPlayerWin()
	{
		Scene w1;
		Scene s1;
		BorderPane bp=new BorderPane();
		
		this.stage.setTitle("player");
		
		bp.setBottom(initHBox());
		bp.setTop(initMenu(this.stage));
		bp.setRight(initControls());
		bp.setLeft(initPlaylist());
		
		s1=new Scene(bp, 500, 300);
		
		this.stage.setScene(s1);
		this.stage.show();
	}
	private MenuBar initMenu(Stage stage)
	{
		MenuBar menu1=new MenuBar();
		menu1.prefWidthProperty().bind(stage.widthProperty());
		
		Menu file=new Menu("File");
		MenuItem openFolder=new MenuItem("Open folder");
		
		file.getItems().add(openFolder);
		menu1.getMenus().add(file);
		
		openFolder.setOnAction(new dirChoosingDialogue());
		return menu1;
	}
	private HBox initHBox()
	{
		HBox hb=new HBox();
		Button play=new Button("play");
		Button pause=new Button("pause");
		Button stop=new Button("stop");
		
		hb.setPadding(new Insets(15, 10, 15, 10));
		hb.setSpacing(15);
		hb.setStyle("-fx-background-color: #cc3399");
		hb.setAlignment(Pos.CENTER);
		
		play.setPrefSize(80, 20);
		pause.setPrefSize(80, 20);
		stop.setPrefSize(80, 20);
		
		hb.getChildren().add(play);
		hb.getChildren().add(pause);
		hb.getChildren().add(stop);
		
		play.addEventHandler(MouseEvent.MOUSE_PRESSED, new playAction());
		pause.addEventHandler(MouseEvent.MOUSE_PRESSED, new pauseAction());
		stop.addEventHandler(MouseEvent.MOUSE_PRESSED, new stopAction());
		
		return hb;
	}
	private VBox initControls()
	{
		VBox v=new VBox();
		Text writing1=new Text("Volume");
		Slider volume=new Slider(0, 100, 50);
		this.label1=new Label("No music folder selected.");
		
		v.setPadding(new Insets(10));
		v.setSpacing(10);
		
		writing1.setFont(Font.font("Console", FontWeight.BOLD, 14));
		this.label1.setFont(Font.font("Console", 14));
		volume.setMaxSize(125, 15);
		volume.setMinSize(80, 15);
		volume.setPrefSize(100, 15);
		
		this.volumeValue.setText(String.valueOf(volume.getValue()));
		v.getChildren().add(this.label1);
		v.getChildren().add(writing1);
		v.getChildren().add(volume);
		v.getChildren().add(this.volumeValue);
		
		volume.addEventHandler(MouseEvent.MOUSE_DRAGGED, new adjustVolume());
		return v;
	}
	private ScrollPane initPlaylist()
	{
		this.sp=new ScrollPane();
		Text txt=new Text("Songs: ");
		
		txt.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
		//this.playlist.setMaxSize(1000, 800);
		//this.playlist.setMinSize(80, 80);
		//this.playlist.setPrefSize(150, 100);
		this.playlist.getChildren().add(txt);
		this.sp.setPrefSize(300, 120);
		
		return this.sp;
	}
	class playAction implements EventHandler<Event>
	{
		public void handle(Event e)
		{
			if(e.getEventType()==MouseEvent.MOUSE_PRESSED)
			{
				if(p!=null)
				{
					one.playSample();
				}
				else if(one.isSongSelected())
				{
					p=one.makeNewPlayer();
					one.setMediaPlayerInstance(p);
					one.playSample();
				}
			}
		}
	}
	class pauseAction implements EventHandler<Event>
	{
		public void handle(Event e)
		{
			System.out.println("attemtpting to pause");
			if(e.getEventType()==MouseEvent.MOUSE_PRESSED)
			{
				one.pauseSample();
			}
		}
	}
	class stopAction implements EventHandler<Event>
	{
		public void handle(Event e)
		{
			System.out.println("stopping play");
			if(e.getEventType()==MouseEvent.MOUSE_PRESSED)
			{
				if(p.getStatus()==MediaPlayer.Status.PLAYING || 
					p.getStatus()==MediaPlayer.Status.PAUSED)
				{
					p.stop();
					p.dispose();
					p=null;
				}
			}
		}
	}
	class adjustVolume implements EventHandler<Event>
	{
		public void handle(Event e)
		{
			Slider s=null;
			if(e.getEventType()==MouseEvent.MOUSE_DRAGGED 
			|| e.getEventType()==MouseEvent.MOUSE_PRESSED)
			{
				if(e.getSource() instanceof Slider)
				{
					s=(Slider)e.getSource();
				}
				System.out.println("value: " + s.getValue());
				volumeValue.setText(String.valueOf(Framework.round(s.getValue(), 2)));
				one.changeVolume(Framework.round(s.getValue(), 2)/100);
			}
		}
	}
	class dirChoosingDialogue implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			one.chooserDialogue(stage, me);
			if(!label1.getText().contains("No music folder selected."))
			{
				one.listDirContents(label1.getText(), me);
			}
		}
	}
	
	//getters-setters
	public Label getLabel1()
	{
		return this.label1;
	}
	public VBox getPlaylist()
	{
		return this.playlist;
	}
	public void setSPane(VBox v)
	{
		this.sp.setFitToWidth(true);
		this.sp.setContent(v);
	}
}
