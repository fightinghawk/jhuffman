package jhuffman.util;

public class UTime
{
	public static String toString(long millis)
	{
		int sec=(int)(millis/1000);
		int min=(int)(sec/60);
		String ret=""+min+" minuto"+((min!=1)?"s":"");
		ret+=", "+sec+" segundo"+((sec!=1)?"s":"");
		return ret;
	}
}
