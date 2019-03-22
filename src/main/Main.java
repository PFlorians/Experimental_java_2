package main;
import java.io.*;
import java.net.*;
import java.util.*;

import audio.*;
import localNetworkTools.*;

public class Main {
	public static void main(String[] args)
	{
		LocalInterfaceAddressStorage lias=new LocalInterfaceAddressStorage();
		Recon r=new Recon(lias);
		/*PortTest pt=new PortTest();
		pt.start();*/
		//r.initStorageAddresses(r.listLocalOpenedInterfaces());
		//LocalPortScan s=new LocalPortScan(2550, 2560, lias);
		//new LocalPortScan(lias);
		try
		{
			//new Player().s2(args);
			new Win1().init(args);//audio
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//r.showOpenedInterfaces(r.listLocalInterfaces());
		//r.getOpenedInterfaceLANDetails(r.listLocalOpenedInterfaces());
	}
}
