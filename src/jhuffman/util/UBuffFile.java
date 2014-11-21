package jhuffman.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class UBuffFile
{
	private BufferedInputStream bis = null;
	private BufferedOutputStream bos = null;
	
	private String sBuffer="";
	private int bitNo=0;

	public UBuffFile(BufferedInputStream bis)
	{
		this.bis=bis;
	}
	
	public UBuffFile(BufferedOutputStream bos)
	{
		this.bos=bos;
	}
	
	public int readBit()
	{
		try
		{
			if( sBuffer.length()==0 || bitNo==8 )
			{
				int b=bis.read();
				
				if( b>=0 )
				{
					sBuffer = Integer.toBinaryString(b);
					sBuffer=UString.lpad(sBuffer, 8, '0');
					bitNo=0;
				}
				else
				{
					return -1;
				}
			}
			
			return sBuffer.charAt(bitNo++)-'0';
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public void writeBit(int b)
	{
		try
		{
			// concateno el bit
			sBuffer+=(b==1)?'1':'0';
			
			if( sBuffer.length()==8 )
			{
				int x = Integer.parseInt(sBuffer, 2);
				bos.write(x);
				sBuffer="";
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	public void reset()
	{
		bitNo=0;
		sBuffer="";
	}
	
	public void flush()
	{
		try
		{
			if( bos==null )
			{
				return;
			}
			

			// completo con ceros
			if( sBuffer.length()>0 )
			{
				sBuffer=UString.rpad(sBuffer, 8, '0');
			}
			
			// grabo los bits que no completaron 1 byte
			if( sBuffer.length()>0 )
			{
				int x = Integer.parseInt(sBuffer, 2);
				bos.write(x);
			}
			
			bitNo=0;
			sBuffer="";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void writeLength(long n)
	{
		String b=Long.toBinaryString(n);
		b=UString.lpad(b, 8*8, '0');
		for(int i=0; i<b.length(); i++)
		{
			writeBit(b.charAt(i)-'0');
		}
	}
	
	public long readLength()
	{
		String bin="";
		int sizeLong=8*8;
		for(int i=0; i<sizeLong; i++)
		{
			bin+=readBit();
		}
		
		return Long.parseLong(bin, 2);
	}
}

