package localNetworkTools;
import java.io.*;
import java.net.*;


public class LocalPortScan{
	private int start, end;
	private LocalInterfaceAddressStorage lias;
	private int singlePort;
	
	public LocalPortScan()//default range vsetky porty bez zoznamu
	{
		this.start=0;
		this.end=65535;
		this.scan();
	}
	public LocalPortScan(int port)//pre jediny port, jedno aky interface
	{
		this.singlePort=port;
		this.scanSinglePort();
	}
	public LocalPortScan(int start, int end)//viacero portov jedno ake interfacy
	{
		this.start=start;
		this.end=end;
		this.scan();
	}
	public LocalPortScan(LocalInterfaceAddressStorage lias)//zname interfacy ale vsetky porty
	{
		this.lias=lias;
		this.start=0;
		this.end=65535;
		this.scanAll();
	}
	public LocalPortScan(int start, int end, LocalInterfaceAddressStorage lias)//zname interfacy aj porty
	{
		this.lias=lias;
		this.start=start;
		this.end=end;
		this.scanAll();
	}
	
	public void scanAll()//scan all interfaces
	{
		int j=0;
		int i;
		if(this.lias.getIpv4List().isEmpty()==false)
		{
			while(j<this.lias.getIpv4List().size())
			{
				Thread t=new Thread(new scanInterface(this.lias.getIpv4List().get(j), this.start, this.end));
				t.setName(new String("interface vlakno: " + j));
				t.start();
				/*System.out.println("Interface: " + this.lias.getIpv4List().get(j));
				for(i=this.start;i<this.end;i++)
				{

					if(availableOnInterface(this.lias.getIpv4List().get(j), i))
					{
						System.out.println("[*] Port: " + i + " OPEN FOR USE");
					}
					else
					{
						System.out.println("[*] Port: " + i + " IN USE");
					}
				}*/
				j++;
			}
		}
		else
		{
			System.out.println("IPv4 list not initialized please use recon first.");
		}
	}
	private void scan()//scan ports without interface
	{
		int i;
		for(i=this.start;i<this.end;i++)
		{
			if(available(i))
			{
				System.out.println("[*] Port: " + i + " OPEN FOR USE");
			}
		}
	}
	private void scanSinglePort()
	{
		if(available(this.singlePort))
		{
			System.out.println("[*] Port: " + this.singlePort + " OPEN FOR USE");
		}
		else
		{
			System.out.println("[*] Port: " + this.singlePort + " IN USE");
		}
	}
	public static boolean available(int port) 
	{
	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try 
	    {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } 
	    catch(Exception e) 
	    {
	    	System.out.println("port: " + port + " " +e);
	    } 
	    finally 
	    {
	        if(ds != null) 
	        {
	            ds.close();
	        }
	        if(ss != null) 
	        {
	            try 
	            {
	                ss.close();
	            } 
	            catch(IOException e) 
	            {
	                /* should not be thrown */
	            }
	        }
	    }
	    return false;
	}
	public static boolean availableOnInterface(InetAddress iaddr, int port)
	{
		ServerSocket s=null;
		DatagramSocket ds=null;
		Socket connectTst=null;
		try
		{
			s=new ServerSocket(port, 0, iaddr);
			connectTst=new Socket(iaddr, port, iaddr, 55555);
			s.setReuseAddress(true);
			ds=new DatagramSocket(port, iaddr);
			ds.setReuseAddress(true);
			return true;
		}
		catch(Exception e)
		{
			System.out.println(iaddr + " port: " + port + " " + e);
		}
		finally
		{
			if(ds!=null)
			{
				ds.close();
			}
			if(s!=null)
			{
				try
				{
					s.close();
				}
				catch(Exception e)
				{
					System.out.println("Error closing socket on interface: " + iaddr);
					System.out.println(e);
				}
			}
			if(connectTst!=null)
			{
				try
				{
					connectTst.close();
				}
				catch(Exception e)
				{
					System.out.println("closing socket error: " + e);
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	class scanInterface implements Runnable
	{
		private InetAddress iaddr;//adresa interface
		private int singlePort;
		private int start, end;
		scanInterface(InetAddress iaddr, int port)
		{
			this.iaddr=iaddr;
			this.singlePort=port;
			this.start=this.end=-1;
		}
		scanInterface(InetAddress iaddr, int start, int end)
		{
			this.iaddr=iaddr;
			this.start=start;
			this.end=end;
			this.singlePort=-1;
		}
		public void run()
		{
			if(this.singlePort>=0 && this.start<0 && this.end<0)
			{
				System.out.println("Interface: " + this.iaddr);
				checkPort();
			}
			else if(this.singlePort<0 && this.start>=0 && this.end>=0)
			{
				System.out.println("Interface: " + this.iaddr);
				checkPortRange();
			}
			else//should not happen
			{
				System.out.println("error: " + this.singlePort + " " + this.start + " " + this.end);
			}
		}
		void checkPort()//kontrola jedneho specifickeho portu
		{
			if(availableOnInterface(this.iaddr, this.singlePort))
			{
				System.out.println("[*] Port: " + this.singlePort + " OPEN FOR USE");
			}
			else
			{
				System.out.println("[*] Port: " + this.singlePort + " IN USE");
			}
		}
		void checkPortRange()
		{
			int i;
			for(i=this.start;i<this.end;i++)
			{
				if(availableOnInterface(this.iaddr, i))
				{
					System.out.println("[*] Port: " + i + " OPEN FOR USE");
				}
				else
				{
					System.out.println("[*] Port: " + i + " IN USE");
				}
			}
		}
	}
}
