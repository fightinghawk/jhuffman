package jhuffman.util;

public class UString
{
	public static String replicate(int n, char c)
	{
		String x="";
		for(int i=0; i<n; i++)
		{
			x+=c;
		}
		
		return x;
	}
	
	public static String lpad(String s, int n, char c)
	{
		String ret=replicate(n-s.length(),c)+s;
		return ret.substring(0,n);
	}
	
	public static String rpad(String s, int n, char c)
	{
		String ret=s+replicate(n-s.length(),c);
		return ret.substring(0,n);
	}
}
