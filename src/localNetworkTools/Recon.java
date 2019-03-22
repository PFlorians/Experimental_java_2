package localNetworkTools;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import generalTools.*;

public class Recon {
	private LocalInterfaceAddressStorage lias;
	
	public Recon(LocalInterfaceAddressStorage lias)
	{
		this.lias=lias;
	}
	public Recon()
	{
		this.lias=null;
	}
	//localne rozhrania nemusia byt otvorene
	public ArrayList<NetworkInterface> listLocalInterfaces()
	{
		Enumeration<NetworkInterface> netint;
		ArrayList<NetworkInterface> list=new ArrayList<NetworkInterface>(5);
		try
		{
			netint=NetworkInterface.getNetworkInterfaces();
			while(netint.hasMoreElements())
			{
				list.add(netint.nextElement());
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception caught: " + e);
			e.printStackTrace();
		}
		return list;
			
	}
	//metoda vrati list otvorenych rozhrani
	public ArrayList<NetworkInterface> listLocalOpenedInterfaces()
	{
		NetworkInterface tmp;
		Enumeration<NetworkInterface> netint;
		ArrayList<NetworkInterface> list=new ArrayList<NetworkInterface>(5);
		try
		{
			netint=NetworkInterface.getNetworkInterfaces();
			while(netint.hasMoreElements())
			{
				tmp=netint.nextElement();
				if(tmp.isUp())
				{
					list.add(tmp);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception caught: " + e);
			e.printStackTrace();
		}
		return list;
	}
	//tlacenie informacii
	//detaily o otvorenych rozhraniach
	public void getOpenedInterfaceLANDetails(ArrayList<NetworkInterface> list)
	{
		int i, j;
		ArrayList<InterfaceAddress> interaddr;
		try
		{
			for(i=0;i<list.size();i++)
			{
				if(list.get(i).isUp())
				{
					interaddr=(ArrayList<InterfaceAddress>)list.get(i).getInterfaceAddresses();
					for(j=0;j<interaddr.size();j++)
					{
						System.out.println("Adresa: " + interaddr.get(j).getAddress().getHostAddress());
						System.out.println("Reachable: " + interaddr.get(j).getAddress().isReachable(5000));
						if(!interaddr.get(j).getAddress().isReachable(5000))
						{
							System.out.println("destination host unreachable using ICMP and TCP on port 7");
							System.out.println("continuing analysis");
						}
						System.out.println("Hostname: " + interaddr.get(j).getAddress().getHostName());
						System.out.println("subnet: " + interaddr.get(j).getNetworkPrefixLength());
						System.out.println("cislo: " + (int)Math.pow(2, interaddr.get(j).getNetworkPrefixLength()));
						Framework.detectSubnet(interaddr.get(j).getNetworkPrefixLength());//((long)Math.pow(2, interaddr.get(j).getNetworkPrefixLength()));
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Lan Details error: " + e);
			e.printStackTrace();
		}
	}
	//metoda vytlaci vsetky otvorene interfacy na stroji
	public void showOpenedInterfaces(ArrayList<NetworkInterface> list)
	{
		int i;
		Enumeration<InetAddress> iaddr;
		InetAddress addr;
		try
		{
			System.out.println("Opened interfaces: ");
			for(i=0;i<list.size();i++)
			{
				if(list.get(i).isUp())
				{
					System.out.println("###########################################");
					System.out.println("Interface no: " + list.get(i).getIndex());
					System.out.println("Name: " + list.get(i).getName());
					System.out.println("Display name: " + list.get(i).getDisplayName());
					if(list.get(i).getHardwareAddress()!=null)
					{
						System.out.println("HW(MAC) address: " + Framework.bytesToHex(list.get(i).getHardwareAddress()));
					}
					System.out.println("Loopback(true/false): " + list.get(i).isLoopback());
					System.out.println("Online(true/false): " + list.get(i).isUp());
					System.out.println("Virtual(true/false): " + list.get(i).isVirtual());
					System.out.println("Point to point(true/false): " + list.get(i).isPointToPoint());
					System.out.println("Multicast(true/false): " + list.get(i).supportsMulticast());
					System.out.println("Maximum Transition Unit: " + list.get(i).getMTU());
					iaddr=list.get(i).getInetAddresses();
					System.out.println("Address(es)");
					while(iaddr.hasMoreElements())
					{
						addr=iaddr.nextElement();
						if(addr instanceof Inet4Address && this.lias!=null)
						{
							this.lias.updateIPv4(addr);
						}
						else if(addr instanceof Inet6Address && this.lias!=null)
						{
							this.lias.updateIPv6(addr);
						}
						System.out.println(addr);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception handled: " + e);
			e.printStackTrace();
		}
	}
	//pre informacie o vsetkych rozhraniach otvorenych aj zatvorenych
	public void showAllInterfaces(ArrayList<NetworkInterface> list)
	{
		int i;
		Enumeration<InetAddress> iaddr;
		InetAddress addr;
		System.out.println("Printing information about all interfaces");
		try
		{
			for(i=0;i<list.size();i++)
			{
				System.out.println("###########################################");
				System.out.println("Interface no: " + list.get(i).getIndex());
				System.out.println("Name: " + list.get(i).getName());
				System.out.println("Display name: " + list.get(i).getDisplayName());
				if(list.get(i).getHardwareAddress()!=null)
				{
					System.out.println("HW(MAC) address: " + Framework.bytesToHex(list.get(i).getHardwareAddress()));
				}
				System.out.println("Loopback(true/false): " + list.get(i).isLoopback());
				System.out.println("Online(true/false): " + list.get(i).isUp());
				System.out.println("Virtual(true/false): " + list.get(i).isVirtual());
				System.out.println("Point to point(true/false): " + list.get(i).isPointToPoint());
				System.out.println("Multicast(true/false): " + list.get(i).supportsMulticast());
				System.out.println("Maximum Transition Unit: " + list.get(i).getMTU());
				
				iaddr=list.get(i).getInetAddresses();
				System.out.println("Address(es):");
				while(iaddr.hasMoreElements())
				{
					addr=iaddr.nextElement();
					System.out.println(addr);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception thrown: " + e);
			e.printStackTrace();
		}
	}
	//rozhrania aktivne aj neaktivne
	public void initStorageAddresses(ArrayList<NetworkInterface> list)
	{
		Enumeration<InetAddress> iaddr;
		InetAddress addr;
		int i;
		for(i=0;i<list.size();i++)
		{
			iaddr=list.get(i).getInetAddresses();
			
			while(iaddr.hasMoreElements())
			{
				addr=iaddr.nextElement();
				if(addr instanceof Inet4Address && this.lias!=null)
				{
					this.lias.updateIPv4(addr);
				}
				else if(addr instanceof Inet6Address && this.lias!=null)
				{
					this.lias.updateIPv6(addr);
				}
			}
		}
	}
	//gets and sets
	public void setStorage(LocalInterfaceAddressStorage lias)
	{
		this.lias=lias;
	}
	public LocalInterfaceAddressStorage getStorage()
	{
		return this.lias;
	}
}
