package generalTools;

import java.math.*;

public class Framework {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	public static void printBinary(long num) {
        long remainder;

        if (num <= 1) {
            System.out.print(num);
            return;   // KICK OUT OF THE RECURSION
        }

        remainder = num %2; 
        printBinary(num >> 1);
        System.out.print(remainder);
    }
	public static void detectSubnet(short bits)
	{
		long mask=2147483648L;
		long result=0L;
		short i;
		System.out.println("maska" + Long.toBinaryString(mask));
		for(i=0;i<bits;i++)
		{
			result=result | mask;
			mask=mask >> 1;
		}
		
		
		System.out.println(Long.toBinaryString(result));
	}
	public static double round(double value, int places) {//zaokruhlenie double na dve desatinne miesta
	    
		BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
