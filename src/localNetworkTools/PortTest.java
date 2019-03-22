package localNetworkTools;
import java.net.*;

public class PortTest {
	public void start()
	{
		int i=0;
		try
		{
			InetAddress iaddr=InetAddress.getByName("127.0.0.1");
			ServerSocket ss=new ServerSocket(1234, 1, iaddr);//tento len caka na spojenia (1)
			Socket s=new Socket(iaddr, 1234, iaddr, 1235);//tento sa pripaja na cakajucich na porte1234
															//odozvu caka na porte 1235
			//s.setReuseAddress(true);
			DatagramSocket ds=new DatagramSocket(1234, iaddr);
			while(true)
			{
				if(i==-1)
				{
					break;
				}
				i++;
			}
			System.out.println("port1 " + s.getPort() + " " + s.getLocalPort()+ " port2 " + ss.getLocalPort());
			ss.close();
			s.close();	
			ds.close();
		}
		catch(Exception e)
		{
			System.out.println("exception: " + e);
			e.printStackTrace();
			
		}
	}
}
