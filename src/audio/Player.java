package audio;
import javax.media.*;

import java.net.*;
import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.*;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.event.*;

public class Player{
	private MediaPlayer p;
	private File files[];
	private Media song;
	private boolean songSelected=false;
	private File songPath;
	
	public Player()
	{
		this.songPath=null;
	}
	public MediaPlayer makeNewPlayer()
	{
		MediaPlayer p=null;
		if(this.songPath!=null)
		{
			System.out.println("creating p");
			try
			{
				//File f=new File("C:/Users/Prometeus/Music/Slow Magic - Girls.mp3");
				//File f=new File("C:/Users/Prometeus/Music/rabaul.mp3");
				String path=this.songPath.toURI().toASCIIString();
				this.song=new Media(path);
				p=new MediaPlayer(this.song);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("fatal error song path not specified");
			System.exit(1);
		}
		return p;
	}
	//actions
	public void playSample()
	{
		System.out.println("status: " + this.p.getStatus());
		
		if(this.p.getStatus()==MediaPlayer.Status.READY 
				|| this.p.getStatus()==MediaPlayer.Status.UNKNOWN
				|| this.p.getStatus()==MediaPlayer.Status.STOPPED)
		{
			System.out.println("play podm 1" + this.p);
			try
			{
				this.p.play();
				this.p.setCycleCount(MediaPlayer.INDEFINITE);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(this.p.getStatus()==MediaPlayer.Status.PAUSED)
		{
			try
			{
				this.p.play();//resume?
				this.p.setCycleCount(MediaPlayer.INDEFINITE);
			}
			catch(Exception e)
			{
				System.out.println("resuming/play 2 exception: " + e);
			}
		}
		else if(this.p.getStatus()==MediaPlayer.Status.PLAYING)
		{
			if(this.p.isMute())
			{
				this.p.setOnEndOfMedia(new repeater());
			}
		}
	}
	public void pauseSample()
	{
		System.out.println("pausing: " + this.p.getStatus());
		if(this.p.getStatus()==MediaPlayer.Status.PLAYING)
		{
			System.out.println("inside of condition");
			try
			{
				this.p.pause();
				System.out.println("pausing: " + this.p.getStatus());
			}
			catch(Exception e)
			{
				System.out.println("pause exception: " + e);
			}
		}
	}
	public void chooserDialogue(Stage stage, Win1 w)
	{
		DirectoryChooser dc=new DirectoryChooser();
		File selected=dc.showDialog(stage);
		
		if(selected==null)
		{
			w.getLabel1().setText("No music folder selected." );
		}
		else
		{
			w.getLabel1().setText(selected.getAbsolutePath());
		}
	}
	public void listDirContents(String dir, Win1 w)
	{
		File d=new File(dir);
		this.files=d.listFiles();
		Text tmp;
		int i;
		for(i=0;i<this.files.length;i++)
		{
			tmp=new Text(this.files[i].getName());
			tmp.addEventHandler(MouseEvent.MOUSE_PRESSED, new itemSelector());
			w.getPlaylist().getChildren().add(tmp);
			w.setSPane(w.getPlaylist());
		}
	}
	public void newSong(String name)
	{
		int i;
		for(i=0;i<this.files.length;i++)
		{
			if(this.files[i].getAbsolutePath().contains(name))
			{
				this.songPath=this.files[i];
				makeNewPlayer();
			}
		}
	}
	public void changeVolume(double val)
	{
		this.p.setVolume(val);
	}
	public void setMediaPlayerInstance(MediaPlayer p)
	{
		this.p=p;
	}
	//insider classes and handlers
	class repeater implements Runnable
	{
		public void run()
		{
			p.seek(Duration.ZERO);
		}
	}
	class itemSelector implements EventHandler<Event>
	{
		public void handle(Event e)
		{
			Text src;
			if(e.getEventType()==MouseEvent.MOUSE_PRESSED)
			{
				if(e.getSource() instanceof Text)
				{
					src=(Text)e.getSource();
					System.out.println("you have chosen: " + src.getText());
					songSelected=true;
					newSong(src.getText());
				}
				else
				{
					System.out.println("Fatal error source is not text: " + e.getSource());
					System.exit(1);
				}
			}
				
		}
	}
	public boolean isSongSelected()
	{
		return this.songSelected;
	}
}
