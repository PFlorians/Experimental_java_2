package localNetworkTools;

import java.net.*;
import java.util.*;

public class LocalInterfaceAddressStorage {
	private ArrayList<InetAddress> ipv4;
	private ArrayList<InetAddress> ipv6;
	
	public LocalInterfaceAddressStorage()
	{
		this.ipv4=new ArrayList<InetAddress>(5);
		this.ipv6=new ArrayList<InetAddress>(5);
	}
	public void updateIPv4(InetAddress iaddr)
	{
		if(this.ipv4!=null && this.ipv4.contains(iaddr)==false)
		{
			this.ipv4.add(iaddr);
		}
	}
	public void updateIPv6(InetAddress iaddr)
	{
		if(this.ipv6!=null && this.ipv6.contains(iaddr)==false)
		{
			this.ipv6.add(iaddr);
		}
	}
	public ArrayList<InetAddress> getIpv4List()
	{
		if(this.ipv4.isEmpty())
		{
			System.out.println("list is empty");
			return null;
		}
		else
		{
			return this.ipv4;
		}
	}
	public ArrayList<InetAddress> getIpv6List()
	{
		if(this.ipv6.isEmpty())
		{
			System.out.println("list is empty");
			return null;
		}
		else
		{
			return this.ipv6;
		}
	}
	public boolean storageIpv4Exists()
	{
		return (this.ipv4.size()>0)?true:false;
	}
	public boolean storageIpv6Exists()
	{
		return (this.ipv6.size()>0)?true:false;
	}
}
